package app.fahimfarhan.vocabularygym.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.fahimfarhan.vocabularygym.R
import app.fahimfarhan.vocabularygym.activitykt.StartActivity
import app.fahimfarhan.vocabularygym.mvvm.viewmodel.GreViewModel
import app.fahimfarhan.vocabularygym.recyclerviews.AnalyticsAdapter
import kotlinx.android.synthetic.main.fragment_analytics.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
@Suppress("RedundantSemicolon")
class AnalyticsFragment : Fragment{
  // Consts/statics
  companion object{
    val TAG: String = AnalyticsFragment::class.java.simpleName;
  }
  // Variables
  private lateinit var fragmentRootView: View;
  private lateinit var greViewModel: GreViewModel;
  private lateinit var analyticsAdapter: AnalyticsAdapter;
  // Constructors
  constructor() {}

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    this.fragmentRootView = inflater.inflate(R.layout.fragment_analytics, container, false);
    this.fragmentRootView.setOnClickListener { /* empty click listener to prevent click propagation*/ };
    return this.fragmentRootView;
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState);
    // kotlins databinding work inside onViewCreated -_-
    this.greViewModel = (requireActivity() as StartActivity).greViewModel;
    this.analyticsAdapter = AnalyticsAdapter(baseFrameLayout2.id);
    val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(requireActivity());

    recyclerView.adapter = this.analyticsAdapter;
    recyclerView.layoutManager = linearLayoutManager;

    GlobalScope.launch(Dispatchers.IO) {
      val someList = greViewModel.getPeccableWordsList();
      withContext(Dispatchers.Main) {
        analyticsAdapter.submit(someList);
      }
    }
  }

  //Private methods

  //Public methods
}