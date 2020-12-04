package app.fahimfarhan.vocabularygym.gadgets

import android.app.Activity
import android.content.Context
import android.util.Log
import app.fahimfarhan.vocabularygym.database.GreModel
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception

class Accessories {
  companion object {
    val TAG: String = Accessories::class.java.simpleName;

    fun readFromCsv(context: Context, fileName: String, difficultyLevel: Int): ArrayList<GreModel> {
      val result: ArrayList<GreModel> = ArrayList();
      val inputStream = InputStreamReader(context.assets.open(fileName)) // "magoosh_1_common.csv"

      val reader = BufferedReader(inputStream)
      reader.readLine()
      var line: String?
      while (reader.readLine().also { line = it } != null) {
        val s: String = line!!;
        val greModel = getGreModel(s, difficultyLevel);
      }
      return result;
    }

    fun getGreModel(input: String, difficultyLevel: Int): GreModel {
      val words: List<String> = input.split("\\|");
      var outPut: GreModel = GreModel();
      try{
        Log.e(TAG, words[0]+" 1 "+words[1]+" 2 "+words[2]+" 3 "+words[3]);
        val initChar: Char = words[0][0];
        outPut.greWord = words[0];
        outPut.greMeaning = words[1];
        outPut.grePartOfSpeech = words[2];
        outPut.exampleSentence = words[3];
        outPut.initialChar = initChar;
        outPut.difficultyLevel = difficultyLevel;

      }catch (x: Exception) {
        Log.e(TAG, "exception "+x.message);
      }

      return outPut;
    }
  }
}