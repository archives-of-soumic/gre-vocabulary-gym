package app.fahimfarhan.vocabularygym.recyclerviews

import android.annotation.SuppressLint
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
  var pageNumer: TextView;

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

    this.pageNumer = itemView.findViewById(R.id.pageNumer);
  }

  fun bindView(greModel: GreModel, choice: ArrayList<String>, actualGreMeaning: String, currPageNumber: String) {
    this.pageNumer.text = currPageNumber;
    this.greWord.text = greModel.greWord;
    this.greMeaning.text = greModel.greMeaning;
    this.grePartOfSpeech.text = greModel.grePartOfSpeech;
    this.greExample.text = greModel.exampleSentence;

    this.option1.text = choice[0];
    this.option2.text = choice[1];
    this.option3.text = choice[2];
    this.option4.text = choice[3];

    this.radioGroup.clearCheck();
    this.solutionContainer.visibility = View.INVISIBLE;

    val radioButtonColor: Int = getColor(R.color.colorRadioButton);
    this.option1.setBackgroundColor(radioButtonColor);
    this.option2.setBackgroundColor(radioButtonColor);
    this.option3.setBackgroundColor(radioButtonColor);
    this.option4.setBackgroundColor(radioButtonColor);

    this.submit.setOnClickListener{  submitTextView ->
      val checkedRadioButtonId = radioGroup.checkedRadioButtonId;
      val radioButton: RadioButton = itemView.findViewById(checkedRadioButtonId);
      val selectedMeaning: String = radioButton.text.toString();

      if( actualGreMeaning.equals(selectedMeaning) ) {
        solutionContainer.visibility = View.VISIBLE;
        val greenColor = getColor(R.color.colorPrimary);
        radioButton.setBackgroundColor(greenColor);
      }else{
        val redColor = getColor(R.color.lightRed);
        radioButton.setBackgroundColor(redColor);
      }
    };
  }

  private fun getColor(colorResId: Int): Int {
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
      return itemView.context.resources.getColor(colorResId, null);
    } else {
      return itemView.context.resources.getColor(colorResId);
    };
  }

}