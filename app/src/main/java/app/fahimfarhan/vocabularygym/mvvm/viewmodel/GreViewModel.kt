package app.fahimfarhan.vocabularygym.mvvm.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import app.fahimfarhan.vocabularygym.mvvm.database.GreDatabase
import app.fahimfarhan.vocabularygym.mvvm.database.GreModel
import app.fahimfarhan.vocabularygym.mvvm.pagination.GrePagingSource
import app.fahimfarhan.vocabularygym.mvvm.repository.GreRepository
import kotlinx.coroutines.flow.Flow

class GreViewModel: AndroidViewModel {
  var difficultyLevels: ArrayList<Int> = ArrayList();
  var initialChars: ArrayList<Int> = ArrayList();

  lateinit var greModelsFlow: Flow<PagingData<GreModel>>;

  constructor(application: Application):super(application) {}
  /*private val greRepository: GreRepository?;
  var greModelsList: List<GreModel>?;

  constructor(application: Application):super(application) {
    this.greRepository = GreRepository(context = application);
    this.greModelsList = this.greRepository.greModelsList;
  }

  fun getGreModelsInBackground(onQueryFinished: (somelist: List<GreModel>) -> Unit) {
    this.greRepository?.getAllGreModelsWith(initialChars = initialChars, difficultyLevels = difficultyLevels, onQueryFinished);
  }*/

  fun initPagination() {
    this.initPagination(initialChars, difficultyLevels);
  }

  fun initPagination(initialChars: List<Int>, difficultyLevels: List<Int>) {
    /*val greModelFlow: Flow<PagingData<GreModel>> = Pager(PagingConfig(pageSize = 10)) {
      GrePagingSource(greDb = GreDatabase.getInstance(getApplication<Application>()), initialChars, difficultyLevels)
    }.flow.cachedIn(viewModelScope);*/


    val greDatabase: GreDatabase = GreDatabase.getInstance(getApplication<Application>());
    val grePagingSource:GrePagingSource = GrePagingSource(greDatabase,initialChars,difficultyLevels);
    val config: PagingConfig = PagingConfig(pageSize = 10);
    val grePagingSourceFactory: () -> PagingSource<Int, GreModel> = { grePagingSource };

    val pager: Pager<Int, GreModel> = Pager(config = config, initialKey = 0,
                                            pagingSourceFactory = grePagingSourceFactory);

    this.greModelsFlow = pager.flow.cachedIn(viewModelScope);
  }
}