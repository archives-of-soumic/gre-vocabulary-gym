package app.fahimfarhan.vocabularygym.recyclerviews

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.fahimfarhan.vocabularygym.R
import app.fahimfarhan.vocabularygym.mvvm.database.PeccableWords
import app.fahimfarhan.vocabularygym.utilities.Accessories

class AnalyticsViewHolder : RecyclerView.ViewHolder{

  var textView: TextView;

  constructor(rootView: View): super(rootView) {
    this.textView = itemView.findViewById(R.id.textView);
  }

  fun onBindView(peccableWords: PeccableWords, baseFrameLayoutId: Int) {
    textView.text = "You got "+peccableWords.wordsList.size +" /  "+ peccableWords.outOfWords +
        " words wrong. See details"
    textView.setOnClickListener{
      // Accessories.initFragment()
    };
  }
}