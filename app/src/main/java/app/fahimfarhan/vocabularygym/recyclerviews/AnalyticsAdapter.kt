package app.fahimfarhan.vocabularygym.recyclerviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.fahimfarhan.vocabularygym.R
import app.fahimfarhan.vocabularygym.mvvm.database.PeccableWords

@Suppress("RedundantSemicolon")
class AnalyticsAdapter : RecyclerView.Adapter<AnalyticsViewHolder> {
  private var peccableWords: ArrayList<PeccableWords> = ArrayList();
  private var baseFrameLayoutId: Int;

  constructor(baseFrameLayoutId: Int): super() {
    this.baseFrameLayoutId = baseFrameLayoutId;
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnalyticsViewHolder {
    val view = LayoutInflater.from(parent.context)
        .inflate(R.layout.gre_item_peccableword, parent, false);
    return AnalyticsViewHolder(view);
  }

  override fun onBindViewHolder(holder: AnalyticsViewHolder, position: Int) {
    holder.onBindView(peccableWords[position], baseFrameLayoutId);
  }

  override fun getItemCount(): Int {
    return peccableWords.size;
  }

  fun submit(list: List<PeccableWords>) {
    this.peccableWords.addAll(list);
    notifyDataSetChanged();
  }
}