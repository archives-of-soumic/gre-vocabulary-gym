package app.fahimfarhan.vocabularygym.recyclerviews

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.fahimfarhan.vocabularygym.R
import app.fahimfarhan.vocabularygym.mvvm.database.GreModel
import kotlinx.android.synthetic.main.practice_item_row.view.*

class PracticeViewHolder : RecyclerView.ViewHolder {
  lateinit var greWord: TextView;
  lateinit var greMeaning: TextView;
  lateinit var grePartOfSpeech: TextView;
  lateinit var greExample: TextView;

  constructor(rootView: View): super(rootView) {
    this.greWord = itemView.findViewById(R.id.greWord);
    this.greMeaning = itemView.findViewById(R.id.greMeaning);
    this.grePartOfSpeech = itemView.findViewById(R.id.grePartOfSpeech);
    this.greExample = itemView.findViewById(R.id.greExample);
  }

  fun onBind(greModel: GreModel) {
    this.greWord.text = greModel.greWord;
    this.greMeaning.text = greModel.greMeaning;
    this.grePartOfSpeech.text = greModel.grePartOfSpeech;
    this.greExample.text = greModel.exampleSentence;
  }
}