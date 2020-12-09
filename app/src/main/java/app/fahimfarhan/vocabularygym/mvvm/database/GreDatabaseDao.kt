package app.fahimfarhan.vocabularygym.mvvm.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GreDatabaseDao {
  @Insert
  fun insertAllGreModels(greModelList: ArrayList<GreModel>);

  @Query("SELECT * FROM gre_vocabulary_table")
  fun selectAllGreModels(): List<GreModel>;

  /***
   * @brief: the result is 0 based indexed. So start = 0, limit = 2 means {word[0], word[1]]} will
   *  be returned
   *  todo: use it with pagination. For now, I'll fetch all data at once
   */
  @Query("SELECT * FROM gre_vocabulary_table WHERE initial_character IN (:initialChars) AND difficulty_level IN (:difficultyLevels) LIMIT :start, :limit")
  suspend fun selectPagedGreModelsWith(initialChars: List<Int>, difficultyLevels: List<Int>, start: Int=0,
                             limit: Int=10): List<GreModel>;

  @Query("SELECT * FROM gre_vocabulary_table WHERE initial_character IN (:initialChars) AND difficulty_level IN (:difficultyLevels)")
  fun selectAllGreModelsWith(initialChars: List<Int>, difficultyLevels: List<Int>): List<GreModel>;

  @Query("SELECT gre_meaning FROM gre_vocabulary_table ORDER BY Random() Limit 100")
  fun selectRandomMeanings(): List<String>;
}