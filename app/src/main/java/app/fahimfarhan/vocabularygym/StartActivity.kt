package app.fahimfarhan.vocabularygym

import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import app.fahimfarhan.vocabularygym.fragments.StartFragment
import app.fahimfarhan.vocabularygym.utilities.Accessories
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
      // todo: Finish it later
    };

    btnGrePractice.setOnClickListener {
      val startFragment: StartFragment = StartFragment();
      Accessories.initFragment(supportFragmentManager, startFrameLayout.id, startFragment, StartFragment.TAG);
    }

  }
}