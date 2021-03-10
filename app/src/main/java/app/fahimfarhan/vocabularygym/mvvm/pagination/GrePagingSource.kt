package app.fahimfarhan.vocabularygym.mvvm.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import app.fahimfarhan.vocabularygym.mvvm.database.GreDatabase
import app.fahimfarhan.vocabularygym.mvvm.database.GreModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class GrePagingSource : PagingSource<Int, GreModel> {

  private val greDb: GreDatabase;
  private var isEndReached: Boolean;
  var initialChars: List<Int> = emptyList();
  var difficultyLevels: List<Int> = emptyList();
  var start: Int=0;
  var limit: Int=10;

  constructor(greDb: GreDatabase, initialChars: List<Int>, difficultyLevels: List<Int>): super() {
    this.greDb = greDb;
    isEndReached = false;
    this.initialChars = initialChars;
    this.difficultyLevels = difficultyLevels;
  }

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GreModel> {
    val currentKey = params.key;
    if(currentKey == null) {
      val loadResult: LoadResult<Int, GreModel> = LoadResult.Page(
          data = emptyList(), prevKey = null, nextKey = null
      );
      return loadResult;
    }else{
      val loadSize = params.loadSize;
      var previousKey: Int? = 0;
      if(currentKey - loadSize < 0) { previousKey = null; }
      else{ previousKey = currentKey - loadSize; }
      var nextKey: Int? = currentKey + loadSize;

      val someList: List<GreModel> = greDb.greDatabaseDao.selectPagedGreModelsWith(initialChars,
          difficultyLevels, currentKey, loadSize);
      if(someList.size < loadSize) { nextKey = null; }

      val loadResult: LoadResult<Int, GreModel> = LoadResult.Page(
          data = someList, prevKey = previousKey, nextKey = nextKey
      );
      return loadResult;
    }
  }

}