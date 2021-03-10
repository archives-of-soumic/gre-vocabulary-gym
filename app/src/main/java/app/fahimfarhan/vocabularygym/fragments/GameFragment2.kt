package app.fahimfarhan.vocabularygym.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import app.fahimfarhan.vocabularygym.R
import app.fahimfarhan.vocabularygym.activitykt.StartActivity
import app.fahimfarhan.vocabularygym.mvvm.database.GreModel
import app.fahimfarhan.vocabularygym.mvvm.viewmodel.GreViewModel
import app.fahimfarhan.vocabularygym.recyclerviews.GreAdapter
import app.fahimfarhan.vocabularygym.recyclerviews.GrePagedAdapter
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

@Suppress("RedundantSemicolon")
class GameFragment2: Fragment {
  // Consts / Statics
  companion object{
    val TAG: String = GameFragment2::class.java.simpleName;
  }

  private lateinit var greViewModel: GreViewModel

  // Variables
  private lateinit var fragmentRootView: View;
  private lateinit var greAdapter: GreAdapter;
  // Constructors
  constructor() {}

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    //return super.onCreateView(inflater, container, savedInstanceState)
    this.fragmentRootView = inflater.inflate(R.layout.fragment_game, container, false);
    this.fragmentRootView.setOnClickListener{/*Empty click listener to prevent click propagation*/};
    return this.fragmentRootView;
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState);
    initGreWords()
  }

  // Private methods
  private fun initGreWords() {
    greViewModel = (requireActivity() as StartActivity).greViewModel;

    this.greAdapter = GreAdapter();

    this.greAdapter.onSelectingWrongMeaning = {  pk ->
      greViewModel.failedGreWords.add(pk);
    };
    this.greAdapter.randomMeanings = greViewModel.randomMeanings;

    val horizontalLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireActivity(),
      LinearLayoutManager.HORIZONTAL, false);

    recyclerView.layoutManager = horizontalLayoutManager;
    recyclerView.adapter = this.greAdapter;

    val snapHelper: SnapHelper = PagerSnapHelper();
    snapHelper.attachToRecyclerView(recyclerView);

    Executors.newSingleThreadExecutor().execute {
      val greWords: List<GreModel> = greViewModel.getGreWords();
      requireActivity().runOnUiThread {
        greAdapter.submit(greWords)
      }
    }
  }

  // Public methods
}