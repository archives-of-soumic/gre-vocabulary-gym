package app.fahimfarhan.vocabularygym.recyclerviews

import android.app.Activity
import android.graphics.Color.red
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import app.fahimfarhan.vocabularygym.R
import app.fahimfarhan.vocabularygym.database.GreModel
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class GreAdapter : RecyclerView.Adapter<GreViewHolder>{

  private val greModelsList: ArrayList<GreModel> = ArrayList();

  constructor(): super() {}

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GreViewHolder {
    val view = LayoutInflater.from(parent.context)
        .inflate(R.layout.gre_item_row, parent, false);

    return GreViewHolder(view);
  }

  override fun onBindViewHolder(holder: GreViewHolder, uiPosition: Int) {
    val dataPosition = uiPosition;
    val choices: ArrayList<String> = ArrayList();

    val actualGreMeaning: String = greModelsList[dataPosition].greWord;

    choices.add(actualGreMeaning);

    val r1 = Random.nextInt(itemCount);
    choices.add(greModelsList[r1].greWord);

    val r2 = Random.nextInt(itemCount);
    choices.add(greModelsList[r2].greWord);

    val r3 = Random.nextInt(itemCount);
    choices.add(greModelsList[r3].greWord);

    Collections.shuffle(choices);

    holder.bindView(greModelsList[dataPosition], choices, actualGreMeaning);
  }

  override fun getItemCount(): Int {
    return greModelsList.size;
  }

  fun submit(t: List<GreModel>?) {
    if (t != null) {
      this.greModelsList.clear();
      this.greModelsList.addAll(t);
      notifyDataSetChanged();
    };
  }
}