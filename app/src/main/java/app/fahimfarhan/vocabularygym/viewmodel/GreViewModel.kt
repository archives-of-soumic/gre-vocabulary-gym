package app.fahimfarhan.vocabularygym.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.fahimfarhan.vocabularygym.database.GreModel
import app.fahimfarhan.vocabularygym.repository.GreRepository
import java.util.concurrent.Executors

class GreViewModel: AndroidViewModel {
  var difficultyLevels: ArrayList<Int> = ArrayList();
  var initialChars: ArrayList<Int> = ArrayList();

  private val greRepository: GreRepository?;
  var greModelsList: MutableLiveData<List<GreModel>>? = null;

  constructor(application: Application):super(application) {
    this.greRepository = GreRepository(context = application);
    this.greModelsList = this.greRepository.greModelsList;
  }

  fun getGreModelsInBackground() {
    this.greRepository?.getAllGreModelsWith(initialChars = initialChars, difficultyLevels = difficultyLevels);
  }
}