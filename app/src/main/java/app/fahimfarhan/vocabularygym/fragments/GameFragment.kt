package app.fahimfarhan.vocabularygym.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import app.fahimfarhan.vocabularygym.R
import app.fahimfarhan.vocabularygym.StartActivity
import app.fahimfarhan.vocabularygym.database.GreModel
import app.fahimfarhan.vocabularygym.recyclerviews.GreAdapter
import kotlinx.android.synthetic.main.fragment_game.*


class GameFragment: Fragment {
  // Consts / Statics
  companion object{
    val TAG: String = GameFragment::class.java.simpleName;
  }
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
    this.initGui();
  }

  // Private methods
  private fun initGui() {
    val horizontalLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireActivity(),
        LinearLayoutManager.HORIZONTAL, false);
    this.greAdapter = GreAdapter();

    recyclerView.layoutManager = horizontalLayoutManager;
    recyclerView.adapter = this.greAdapter;

    val snapHelper: SnapHelper = PagerSnapHelper();
    snapHelper.attachToRecyclerView(recyclerView);

    val greViewModel = (requireActivity() as StartActivity).greViewModel;
    // greViewModel.greModelsList?.observe(viewLifecycleOwner, { someList -> greAdapter.submit(someList); })
    val onQueryFinished: (somelist: List<GreModel>) -> Unit = { somelist ->
      activity?.runOnUiThread {
        greAdapter.submit(somelist);
      }
    }

    greViewModel.getGreModelsInBackground(onQueryFinished);

  }
  // Public methods
}