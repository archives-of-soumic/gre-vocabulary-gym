package app.fahimfarhan.vocabularygym.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import app.fahimfarhan.vocabularygym.R
import app.fahimfarhan.vocabularygym.utilities.Accessories
import app.fahimfarhan.vocabularygym.viewmodel.GreViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_start.*
import java.util.*
import kotlin.collections.ArrayList

class StartFragment: Fragment {
  // Consts / Statics
  companion object {
    val TAG: String = StartFragment::class.java.simpleName;
  }
  // Variables
  private lateinit var fragmentRootView: View;
  private val difficultyLevels: ArrayList<Int> = ArrayList();
  private val initialChars:  ArrayList<Int> = ArrayList();
  var input: String = "";
  // Constructors
  constructor() {}

  // Override methods
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:
  Bundle?): View? {
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

  private val onStartClicked: View.OnClickListener = object : View.OnClickListener {
    override fun onClick(v: View?) {
      input = editText.text.toString();
      if(input.isBlank()) {
        Snackbar.make(fragmentRootView, "Please enter letters you want to practice",
            Snackbar.LENGTH_LONG).show();
        return;
      }

      initDifficultyLevels();
      initInitialCharacters();

      val gameFragment: GameFragment = GameFragment();
      // todo: pass values using setters

      Accessories.initFragment(
        requireActivity().supportFragmentManager, R.id.baseFrameLayout, gameFragment, GameFragment.TAG);
    }
  }

  private fun initInitialCharacters() {
    initialChars.clear();
    input = input.toLowerCase(Locale.getDefault());
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