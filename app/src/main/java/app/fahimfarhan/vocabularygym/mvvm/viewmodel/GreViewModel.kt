package app.fahimfarhan.vocabularygym.mvvm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import app.fahimfarhan.vocabularygym.mvvm.database.GreModel
import app.fahimfarhan.vocabularygym.mvvm.repository.GreRepository

class GreViewModel: AndroidViewModel {
  var difficultyLevels: ArrayList<Int> = ArrayList();
  var initialChars: ArrayList<Int> = ArrayList();

  private val greRepository: GreRepository?;
  var greModelsList: List<GreModel>?;

  constructor(application: Application):super(application) {
    this.greRepository = GreRepository(context = application);
    this.greModelsList = this.greRepository.greModelsList;
  }

  fun getGreModelsInBackground(onQueryFinished: (somelist: List<GreModel>) -> Unit) {
    this.greRepository?.getAllGreModelsWith(initialChars = initialChars, difficultyLevels = difficultyLevels, onQueryFinished);
  }
}