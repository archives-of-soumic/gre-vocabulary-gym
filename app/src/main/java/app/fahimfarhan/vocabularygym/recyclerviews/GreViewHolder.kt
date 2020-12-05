package app.fahimfarhan.vocabularygym.recyclerviews

import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import app.fahimfarhan.vocabularygym.R
import app.fahimfarhan.vocabularygym.database.GreModel

class GreViewHolder: RecyclerView.ViewHolder {
  var greWord: TextView;
  var greMeaning: TextView;
  var grePartOfSpeech: TextView;
  var greExample: TextView;

  var questionContainer: ConstraintLayout;
  var solutionContainer: ConstraintLayout;

  var radioGroup: RadioGroup;
  var option1: RadioButton;
  var option2: RadioButton;
  var option3: RadioButton;
  var option4: RadioButton;

  var submit: TextView;

  var actualGreMeaning: String = "";

  constructor(rootView: View):super(rootView) {
    this.greWord = itemView.findViewById(R.id.greWord);
    this.greMeaning = itemView.findViewById(R.id.greMeaning);
    this.greExample = itemView.findViewById(R.id.greExample);
    this.grePartOfSpeech = itemView.findViewById(R.id.grePartOfSpeech);

    this.questionContainer = itemView.findViewById(R.id.questionContainer);
    this.solutionContainer = itemView.findViewById(R.id.solutionContainer);

    this.radioGroup = itemView.findViewById(R.id.radioGroup);
    this.option1 = itemView.findViewById(R.id.option1);
    this.option2 = itemView.findViewById(R.id.option2);
    this.option3 = itemView.findViewById(R.id.option3);
    this.option4 = itemView.findViewById(R.id.option4);

    this.submit = itemView.findViewById(R.id.submit);
  }

  fun bindView(greModel: GreModel, choice: ArrayList<String>, actualGreMeaning: String) {
    this.greWord.text = greModel.greWord;
    this.greMeaning.text = greModel.greMeaning;
    this.grePartOfSpeech.text = greModel.grePartOfSpeech;
    this.greExample.text = greModel.exampleSentence;

    this.option1.text = choice[0];
    this.option2.text = choice[1];
    this.option3.text = choice[2];
    this.option4.text = choice[3];

    this.submit.setOnClickListener{  submitTextView ->
      solutionContainer.visibility = View.VISIBLE;
      val checkedRadioButtonId = radioGroup.checkedRadioButtonId;
      val radioButton: RadioButton = itemView.findViewById(checkedRadioButtonId);
      val selectedMeaning: String = radioButton.text.toString();

      if( actualGreMeaning.equals(selectedMeaning) ) {
        // todo: logic
        val redColor = itemView.context.resources.getColor(R.color.lightRed);
        radioButton.setBackgroundColor(redColor);
      }else{
        // todo: logic
        val greenColor = itemView.context.resources.getColor(R.color.colorPrimary);
        radioButton.setBackgroundColor(greenColor);
      }
    };
  }
}