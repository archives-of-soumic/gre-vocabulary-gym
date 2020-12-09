package app.fahimfarhan.vocabularygym.mvvm.repository

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import app.fahimfarhan.vocabularygym.Constants
import app.fahimfarhan.vocabularygym.mvvm.database.GreDatabase
import app.fahimfarhan.vocabularygym.mvvm.database.GreDatabaseDao
import app.fahimfarhan.vocabularygym.mvvm.database.GreModel
import app.fahimfarhan.vocabularygym.utilities.Accessories
import java.util.concurrent.Executors

class GreRepository {
  // Consts / Statics
  companion object {
    val TAG: String = GreRepository::class.java.simpleName;
  }
  // Variables
  private var greDao: GreDatabaseDao;
  // var greModelsList: ArrayList<GreModel> = ArrayList();
  var greModelsList: List<GreModel>? = null;

  // Constructors
  constructor(context: Context) {
    Log.e(TAG, "1");
    val db: GreDatabase = GreDatabase.getInstance(context);
    this.greDao = db.greDatabaseDao;

    val isFirstTime: Boolean = isFirstTimeFromPreference(context);
    if(isFirstTime) {
      Log.e(TAG, "2");
      val commonGreWords = Accessories.readFromCsv(context, "magoosh_1_common.csv", 1);
      val basicGreWords = Accessories.readFromCsv(context, "magoosh_2_basic.csv", 2);
      val advancedGreWords = Accessories.readFromCsv(context, "magoosh_3_advanced.csv", 3);

      insertAllGreModels(commonGreWords);
      insertAllGreModels(basicGreWords);
      insertAllGreModels(advancedGreWords);

      saveIsFirstTImeInPreference(context, false);
    }
  }

  // Private methods
  private fun isFirstTimeFromPreference(context: Context): Boolean {
    val pref = context.getSharedPreferences(Constants.GRE_SHARED_PREFERENCE, Context.MODE_PRIVATE);
    val isFirstTime = pref.getBoolean(Constants.IS_FIRST_TIME, true);
    return isFirstTime;
  }

  private fun saveIsFirstTImeInPreference(context: Context, isFirstTime: Boolean) {
    val pref = context.getSharedPreferences(Constants.GRE_SHARED_PREFERENCE, Context.MODE_PRIVATE);
    val editor: SharedPreferences.Editor = pref.edit();
    editor.putBoolean(Constants.IS_FIRST_TIME, isFirstTime);
    editor.apply();
  }
  // Public methods
  /*fun getGreModelsList() : ArrayList<GreModel> {
      return this.greModelsList;
  }*/

  fun insertAllGreModels(greList: ArrayList<GreModel>) {
    GreDatabase.databaseWriteExecutor.execute{
      greDao.insertAllGreModels(greModelList = greList);
    }
  }

  fun getAllGreModelsWith(initialChars: List<Int>, difficultyLevels: List<Int>, onQueryFinished: (somelist: List<GreModel>) -> Unit) {
    Executors.newSingleThreadExecutor().execute {
      this.greModelsList = greDao.selectAllGreModelsWith(initialChars = initialChars,
          difficultyLevels = difficultyLevels);
      Log.e(TAG, "this.greModelsList.size "+this.greModelsList?.size);
      onQueryFinished.invoke(this.greModelsList!!);
    }
  }

}