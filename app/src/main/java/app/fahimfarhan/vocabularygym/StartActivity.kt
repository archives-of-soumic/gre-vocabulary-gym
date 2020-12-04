package app.fahimfarhan.vocabularygym

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import app.fahimfarhan.vocabularygym.viewmodel.GreViewModel

class StartActivity : AppCompatActivity() {

  lateinit var greViewModel: GreViewModel;

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_start);

    val application: Application = getApplication();

    this.greViewModel = ViewModelProvider(
        this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
    ).get(GreViewModel::class.java);

    val tv: TextView = findViewById(R.id.tv);
    val btn: TextView = findViewById(R.id.button);
    btn.setOnClickListener {
      tv.text = greViewModel.greModelsList.toString();
    }

  }
}