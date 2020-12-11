package app.fahimfarhan.vocabularygym.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import app.fahimfarhan.vocabularygym.R
import app.fahimfarhan.vocabularygym.activitykt.StartActivity
import app.fahimfarhan.vocabularygym.mvvm.viewmodel.GreViewModel
import app.fahimfarhan.vocabularygym.recyclerviews.PracticeAdapter
import kotlinx.android.synthetic.main.fragment_practice.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PracticeFragment : Fragment{
  companion object{
    val TAG: String = PracticeFragment::class.java.simpleName;
  }

  private lateinit var fragmentRootView: View;
  private lateinit var practiceAdapter: PracticeAdapter;
  var peccableWordsList: ArrayList<Int> = ArrayList()
  lateinit var greViewModel: GreViewModel;

  constructor() {}

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    this.fragmentRootView = inflater.inflate(R.layout.fragment_practice, container, false);
    this.fragmentRootView.setOnClickListener {  }
    return this.fragmentRootView;
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    this.practiceAdapter = PracticeAdapter();
    var linearLayoutManager = LinearLayoutManager(requireActivity());
    practiceRecyclerView.adapter = practiceAdapter;
    practiceRecyclerView.layoutManager = linearLayoutManager;

    this.greViewModel = (requireActivity() as StartActivity).greViewModel;

    GlobalScope.launch(Dispatchers.IO) {
      val list = greViewModel.selectGreModelsIn(peccableWordsList);
      withContext(Dispatchers.Main) {
        practiceAdapter.submit(list);
      }
    }

  }
}