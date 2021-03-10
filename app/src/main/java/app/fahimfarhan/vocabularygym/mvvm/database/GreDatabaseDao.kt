package app.fahimfarhan.vocabularygym.mvvm.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Suppress("RedundantSemicolon")
@Dao
interface GreDatabaseDao {
  @Insert
  fun insertAllGreModels(greModelList: ArrayList<GreModel>);

  @Query("SELECT * FROM gre_vocabulary_table")
  fun selectAllGreModels(): List<GreModel>;

  /***
   * @brief: the result is 0 based indexed. So start = 0, limit = 2 means {word[0], word[1]]} will
   *  be returned
   */
  @Query("SELECT * FROM gre_vocabulary_table WHERE initial_character IN (:initialChars) AND difficulty_level IN (:difficultyLevels) LIMIT :start, :limit")
  suspend fun selectPagedGreModelsWith(initialChars: List<Int>, difficultyLevels: List<Int>, start: Int=0,
                             limit: Int=10): List<GreModel>;

  @Query("SELECT COUNT(*) FROM gre_vocabulary_table WHERE initial_character IN (:initialChars) AND difficulty_level IN (:difficultyLevels)")
  suspend fun countAllGreModelsWith(initialChars: List<Int>, difficultyLevels: List<Int>): Int;

  @Query("SELECT gre_meaning FROM gre_vocabulary_table ORDER BY Random() Limit 100")
  fun selectRandomMeanings(): List<String>;

  @Insert
  fun insertPeccableWord(peccableWords: PeccableWords);

  @Query("SELECT * FROM PECCABLE_WORDS_TABLE ORDER BY pk DESC")  // TODO: MAYBE UPGRADE IT LATER, eg, add pagination
  suspend fun getAllPeccableWords(): List<PeccableWords>;


  @Query("SELECT * FROM gre_vocabulary_table WHERE pk IN (:ids)")
  suspend fun selectGreModelsIn(ids: List<Int>): List<GreModel>;

}