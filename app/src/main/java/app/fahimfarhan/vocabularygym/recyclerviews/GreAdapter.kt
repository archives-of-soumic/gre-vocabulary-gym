package app.fahimfarhan.vocabularygym.recyclerviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.fahimfarhan.vocabularygym.R
import app.fahimfarhan.vocabularygym.mvvm.database.GreModel
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

@Suppress("ConvertSecondaryConstructorToPrimary", "RedundantSemicolon")
class GreAdapter: RecyclerView.Adapter<GreViewHolder> {
  var randomMeanings: ArrayList<String> = ArrayList();
  val allGreWords: ArrayList<GreModel> = ArrayList()
  var onSelectingWrongMeaning: (Int) -> Unit = {};

  val shouldShowHint = false

  constructor(): super() {

  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GreViewHolder {
    val view = LayoutInflater.from(parent.context)
      .inflate(R.layout.gre_item_row, parent, false);
    return GreViewHolder(view);
  }

  override fun onBindViewHolder(holder: GreViewHolder, uiPosition: Int) {
    val dataPosition = uiPosition;
    val choices: ArrayList<String> = ArrayList();

    val actualGreMeaning: String = getItem(dataPosition).greMeaning;

    choices.add(actualGreMeaning);

    if(randomMeanings.size > 0) {
      val r1 = Random.nextInt(randomMeanings.size);
      choices.add(randomMeanings[r1]);

      val r2 = Random.nextInt(randomMeanings.size);
      choices.add(randomMeanings[r2]);

      val r3 = Random.nextInt(randomMeanings.size);
      choices.add(randomMeanings[r3]);
    }else{
      val r1 = Random.nextInt(itemCount);
      choices.add(getItem(r1).greMeaning);

      val r2 = Random.nextInt(itemCount);
      choices.add(getItem(r2).greMeaning);

      val r3 = Random.nextInt(itemCount);
      choices.add(getItem(r3).greMeaning);

    }

    Collections.shuffle(choices);

    val currentPageNumber: String = ( (dataPosition+1).toString()+" / " + itemCount.toString());
    holder.bindView(
      getItem(dataPosition), choices, actualGreMeaning,
      currentPageNumber, onSelectingWrongMeaning, shouldShowHint);
  }

  private fun getItem(dataPosition: Int): GreModel {
    return this.allGreWords[dataPosition]
  }

  override fun getItemCount(): Int {
    return this.allGreWords.size
  }

  fun submit(greWords: List<GreModel>) {
    this.allGreWords.addAll(greWords)
    Collections.shuffle(this.allGreWords)
    notifyDataSetChanged()
  }
}