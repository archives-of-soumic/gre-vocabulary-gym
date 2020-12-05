package app.fahimfarhan.vocabularygym.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GreDatabaseDao {
  @Insert
  fun insertAllGreModels(greModelList: ArrayList<GreModel>);

  @Query("SELECT * FROM gre_vocabulary_table")
  fun selectAllGreModels(): List<GreModel>;

  @Query("SELECT * FROM gre_vocabulary_table WHERE initial_character IN (:initialChars) AND difficulty_level IN (:difficultyLevels)")
  fun selectAllGreModelsWith(initialChars: List<Int>, difficultyLevels: List<Int>): List<GreModel>;

}