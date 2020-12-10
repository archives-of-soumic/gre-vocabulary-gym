package app.fahimfarhan.vocabularygym.mvvm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import app.fahimfarhan.vocabularygym.Constants
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(entities = [GreModel::class, PeccableWords::class], version = 1, exportSchema = false)
abstract class GreDatabase : RoomDatabase() {
  abstract val greDatabaseDao: GreDatabaseDao;

  companion object {
    @Volatile
    private var INSTANCE: GreDatabase? = null;
    const val NUMBER_OF_THREADS = 4;
    val databaseWriteExecutor: ExecutorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    fun getInstance(context: Context): GreDatabase {
      if(INSTANCE == null){
        synchronized(this) {
          if(INSTANCE == null) {
            this.INSTANCE = Room
                .databaseBuilder(context, GreDatabase::class.java, Constants.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
          }
        }
      }
      return INSTANCE!!;
    }
  }
}