package app.fahimfarhan.vocabularygym.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import app.fahimfarhan.vocabularygym.R
import app.fahimfarhan.vocabularygym.activitykt.StartActivity
import app.fahimfarhan.vocabularygym.mvvm.viewmodel.GreViewModel
import app.fahimfarhan.vocabularygym.utilities.Gizmos
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_start.*
import java.lang.StringBuilder
import java.util.*
import kotlin.collections.ArrayList

@Suppress("RedundantSemicolon")
class StartFragment: Fragment {
  // Consts / Statics
  companion object {
    val TAG: String = StartFragment::class.java.simpleName;
  }
  // Variables
  private lateinit var fragmentRootView: View;
  private lateinit var greViewModel: GreViewModel;
  private val difficultyLevels: ArrayList<Int> = ArrayList();
  private val initialChars:  ArrayList<Int> = ArrayList();
  var input: String = "";
  // Constructors
  constructor() {}

  // Override methods
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:
    Bundle?
  ): View? {
    // return super.onCreateView(inflater, container, savedInstanceState)
    this.fragmentRootView = inflater.inflate(R.layout.fragment_start, container, false);
    this.fragmentRootView.setOnClickListener { /* empty click listener to prevent click propagation*/ };
    return this.fragmentRootView;
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState);
    this.initGui();
  }

  // Private methods
  private fun initGui() {
    start.setOnClickListener(onStartClicked);
  }

  override fun onResume() {
    super.onResume()
    Log.e(TAG, "inside onResume");
    doOnResume();
  }

  fun doOnResume() {
    // cz we want this to happen when we return from the gameFragment
    this.greViewModel = (requireActivity() as StartActivity).greViewModel;
    if(greViewModel.failedGreWords.size == 0) {
      summary.visibility = View.GONE;
    }else{
      summary.visibility = View.VISIBLE;
      saveProgress.isEnabled = true;
      saveProgress.setBackgroundColor(Gizmos.getColor(requireActivity(), R.color.colorPrimaryDark));
      summaryTitle.text = "You got "+greViewModel.failedGreWords.size+" words wrong in the last session!";
      saveProgress.setOnClickListener{
        saveProgress.isEnabled = false;
        saveProgress.setBackgroundColor(Gizmos.getColor(requireActivity(), R.color.grey20));
        greViewModel.savePeccableWords();
        Snackbar.make(
          fragmentRootView, "These words will be saved in database!",
          Snackbar.LENGTH_LONG
        ).show();
      }
    }
  }

  private val onStartClicked: View.OnClickListener = object : View.OnClickListener {
    override fun onClick(v: View?) {

      if(editText.hasFocus()) {
        val imm: InputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE)
                as InputMethodManager;
        imm.hideSoftInputFromWindow(editText.windowToken, 0)

      }

      input = editText.text.toString();
      if(input.isBlank()) {
        Snackbar.make(
          fragmentRootView, "Please enter letters you want to practice",
          Snackbar.LENGTH_LONG
        ).show();
        return;
      }

      initDifficultyLevels();
      initInitialCharacters();

      val startActivity: StartActivity = requireActivity() as StartActivity;
      startActivity.greViewModel.initialChars = initialChars;
      startActivity.greViewModel.difficultyLevels = difficultyLevels;
      val gameFragment: GameFragment2 = GameFragment2();

      Gizmos.initFragment(
        requireActivity().supportFragmentManager,
        R.id.baseFrameLayout,
        gameFragment,
        GameFragment2.TAG
      );
    }
  }

  private fun initInitialCharacters() {
    initialChars.clear();
    input = input.toLowerCase(Locale.getDefault());

    var isRangedQuery: Boolean = false
    var ranges = input.split(" ")
    isRangedQuery = (ranges.size > 1);
    if(!isRangedQuery) {
      addAll(input)
    }else{
      for(range in ranges) {
        Log.e(TAG, range);
        val ithRange = range.split("-");
        if(ithRange.size == 1 ) {
          addAll(ithRange[0])
        }else if(ithRange.size == 2){
          val start: Char = ithRange[0][0]
          val end: Char = ithRange[1][0]
          if(end >= start) {
            val rangedInput = StringBuilder()
            for(i in start..end) {
              rangedInput.append(i)
            }
            addAll(rangedInput.toString())
          }
        }
      }
    }

  }

  private fun addAll(input: String) {
    for(ch: Char in input) {
      if( (ch >= 'a') && (ch <= 'z') ) {
        val i: Int = ch - 'a';
        initialChars.add(i);
      }
    }
  }

  private fun initDifficultyLevels() {
    difficultyLevels.clear();
    if(common.isChecked) {  difficultyLevels.add(1);   }
    if(basic.isChecked) {  difficultyLevels.add(2);  }
    if(advanced.isChecked) {  difficultyLevels.add(3);  }
  }

  // Public methods
}