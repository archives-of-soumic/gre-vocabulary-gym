package app.fahimfarhan.vocabularygym.recyclerviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.fahimfarhan.vocabularygym.R
import app.fahimfarhan.vocabularygym.mvvm.database.GreModel

@Suppress("RedundantSemicolon")
class PracticeAdapter : RecyclerView.Adapter<PracticeViewHolder>{
  private var greWordsList: ArrayList<GreModel> = ArrayList();
  constructor(): super() {}

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PracticeViewHolder {
    val view = LayoutInflater.from(parent.context)
        .inflate(R.layout.practice_item_row, parent, false);
    return PracticeViewHolder(view);
  }

  override fun onBindViewHolder(holder: PracticeViewHolder, position: Int) {
    holder.onBind(greModel = greWordsList[position]);
  }

  override fun getItemCount(): Int {
    return this.greWordsList.size;
  }

  fun submit(list: List<GreModel>) {
    this.greWordsList.addAll(list);
    notifyDataSetChanged();
  }

}