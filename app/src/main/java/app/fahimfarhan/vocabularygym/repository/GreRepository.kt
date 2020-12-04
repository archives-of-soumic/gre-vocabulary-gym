package app.fahimfarhan.vocabularygym.repository

import android.content.Context
import app.fahimfarhan.vocabularygym.database.GreDatabase
import app.fahimfarhan.vocabularygym.database.GreDatabaseDao
import app.fahimfarhan.vocabularygym.database.GreModel
import app.fahimfarhan.vocabularygym.gadgets.Accessories
import java.util.concurrent.Executors

class GreRepository {
  // Consts / Statics
  companion object {
    val TAG: String = GreRepository::class.java.simpleName;
  }
  // Variables
  private var greDao: GreDatabaseDao;
  var greModelsList: ArrayList<GreModel> = ArrayList();

  // Constructors
  constructor(context: Context) {
    val db: GreDatabase = GreDatabase.getInstance(context);
    this.greDao = db.greDatabaseDao;

    val commonGreWords = Accessories.readFromCsv(context, "magoosh_1_common.csv", 1);
    val basicGreWords = Accessories.readFromCsv(context, "magoosh_2_basic.csv", 2);
    val advancedGreWords = Accessories.readFromCsv(context, "magoosh_3_advanced.csv", 3);

    this.greModelsList = ArrayList();
    Executors.newSingleThreadExecutor().execute {
      greModelsList.addAll(greDao.selectAllGreModels());
    }

  }

  // Private methods

  // Public methods
  /*fun getGreModelsList() : ArrayList<GreModel> {
      return this.greModelsList;
  }*/

  fun insertAllGreModels(greList: ArrayList<GreModel>) {
    GreDatabase.databaseWriteExecutor.execute{
      greDao.insertAllGreModels(greModelList = greList);
    }
  }
}