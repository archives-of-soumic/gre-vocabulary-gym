package app.fahimfarhan.vocabularygym.utilities

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import app.fahimfarhan.vocabularygym.mvvm.database.GreModel
import java.io.BufferedReader
import java.io.InputStreamReader

@Suppress("RedundantSemicolon")
class Gizmos {
  companion object {
    val TAG: String = Gizmos::class.java.simpleName;

    fun readFromCsv(context: Context, fileName: String, difficultyLevel: Int): ArrayList<GreModel> {
      val result: ArrayList<GreModel> = ArrayList();
      val inputStream = InputStreamReader(context.assets.open(fileName)) // "magoosh_1_common.csv"

      val reader = BufferedReader(inputStream)
      reader.readLine()
      var line: String?
      while (reader.readLine().also { line = it } != null) {
        val s: String = line!!;
        val greModel: GreModel = getGreModel(s, difficultyLevel);
        result.add(greModel);
      }
      return result;
    }

    fun getGreModel(input: String, difficultyLevel: Int): GreModel {
      val words: List<String> = input.split("|"); // input.split("\\|"); this is for java :/
      var outPut: GreModel = GreModel();
      val initCharAsInt: Int = words[0][0] - 'a';
      outPut.greWord = words[0];
      outPut.greMeaning = words[1];
      outPut.grePartOfSpeech = words[2];
      outPut.exampleSentence = words[3];
      outPut.initialChar = initCharAsInt;
      outPut.difficultyLevel = difficultyLevel;
      return outPut;
    }

    fun initFragment(fragmentManager: FragmentManager, baseContainer: Int, fragment: Fragment, tag: String ) {
      fragmentManager.beginTransaction()
        .add(baseContainer, fragment, tag)
        .addToBackStack(tag)
        .commit();
    }

    fun getColor(context: Context, colorResId: Int): Int {
      if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
        return context.resources.getColor(colorResId, null);
      } else {
        return context.resources.getColor(colorResId);
      };
    }
  }
}