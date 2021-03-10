package app.fahimfarhan.vocabularygym.activitykt

import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import app.fahimfarhan.vocabularygym.R
import app.fahimfarhan.vocabularygym.fragments.AnalyticsFragment
import app.fahimfarhan.vocabularygym.fragments.StartFragment
import app.fahimfarhan.vocabularygym.utilities.Gizmos
import app.fahimfarhan.vocabularygym.mvvm.viewmodel.GreViewModel
import kotlinx.android.synthetic.main.activity_start.*


class StartActivity : AppCompatActivity() {

  lateinit var greViewModel: GreViewModel;

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_start);

    val application: Application = getApplication();

    this.greViewModel = ViewModelProvider(
        this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
    ).get(GreViewModel::class.java);

    btnAnalytics.setOnClickListener {
      val analytivsFragment = AnalyticsFragment();
      Gizmos.initFragment(supportFragmentManager, startFrameLayout.id, analytivsFragment,
          AnalyticsFragment.TAG);
    };

    btnGrePractice.setOnClickListener {
      val startFragment: StartFragment = StartFragment();
      Gizmos.initFragment(supportFragmentManager, startFrameLayout.id, startFragment, StartFragment.TAG);
    }

  }

  override fun onBackPressed() {
    super.onBackPressed();
    // todo: fix this fragment related issue later
    var fragment: Fragment? = supportFragmentManager.findFragmentByTag(StartFragment.TAG);
    if(fragment!=null) {
      var startFragment: StartFragment = fragment as StartFragment;
      startFragment.doOnResume();
    }
  }
}