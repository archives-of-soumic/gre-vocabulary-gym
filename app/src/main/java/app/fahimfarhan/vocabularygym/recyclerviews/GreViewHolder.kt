package app.fahimfarhan.vocabularygym.recyclerviews

import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import app.fahimfarhan.vocabularygym.R
import app.fahimfarhan.vocabularygym.mvvm.database.GreModel

@Suppress("RedundantSemicolon")
class GreViewHolder: RecyclerView.ViewHolder {
  private var greWord: TextView;
  private var greMeaning: TextView;
  private var grePartOfSpeech: TextView;
  private var greExample: TextView;
  private var reveal: TextView

  private var questionContainer: ConstraintLayout;
  private var solutionContainer: ConstraintLayout;

  private var radioGroup: RadioGroup;
  private var option1: RadioButton;
  private var option2: RadioButton;
  private var option3: RadioButton;
  private var option4: RadioButton;

  private var submit: TextView;
  private var pageNumber: TextView;

  private var actualGreMeaning: String = "";

  constructor(rootView: View):super(rootView) {
    this.greWord = itemView.findViewById(R.id.greWord);
    this.greMeaning = itemView.findViewById(R.id.greMeaning);
    this.greExample = itemView.findViewById(R.id.greExample);
    this.grePartOfSpeech = itemView.findViewById(R.id.grePartOfSpeech);
    this.reveal = itemView.findViewById(R.id.show)

    this.questionContainer = itemView.findViewById(R.id.questionContainer);
    this.solutionContainer = itemView.findViewById(R.id.solutionContainer);

    this.radioGroup = itemView.findViewById(R.id.radioGroup);
    this.option1 = itemView.findViewById(R.id.option1);
    this.option2 = itemView.findViewById(R.id.option2);
    this.option3 = itemView.findViewById(R.id.option3);
    this.option4 = itemView.findViewById(R.id.option4);

    this.submit = itemView.findViewById(R.id.submit);

    this.pageNumber = itemView.findViewById(R.id.pageNumber);
  }

  fun bindView(greModel: GreModel, choice: ArrayList<String>, actualGreMeaning: String,
               currPageNumber: String, onSelectingWrongMeaning: (Int) -> Unit, showHint: Boolean = false) {
    this.pageNumber.text = currPageNumber;
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

    changeGreWordColor(greModel = greModel);

    this.submit.setOnClickListener{  submitTextView ->
      val checkedRadioButtonId = radioGroup.checkedRadioButtonId;
      val _radioButton: RadioButton? = itemView.findViewById(checkedRadioButtonId);
      if(_radioButton != null) {
        val radioButton: RadioButton = _radioButton!! // itemView.findViewById(checkedRadioButtonId);
        val selectedMeaning: String = radioButton.text.toString();

        if( actualGreMeaning.equals(selectedMeaning) ) {
          if(greModel.isCorrect == null) {
            greModel.isCorrect = true;
            changeGreWordColor(greModel = greModel);
          }
          solutionContainer.visibility = View.VISIBLE;
          val greenColor = getColor(R.color.colorPrimary);
          radioButton.setBackgroundColor(greenColor);
        }else{
          if(greModel.isCorrect == null) {
            greModel.isCorrect = false;
            changeGreWordColor(greModel = greModel);
            onSelectingWrongMeaning.invoke(greModel.pk);
          }
          val redColor = getColor(R.color.lightRed);
          radioButton.setBackgroundColor(redColor);
        }
      }
    };

    if(showHint == false) {
      questionContainer.visibility = View.INVISIBLE
      reveal.setOnClickListener {
        questionContainer.visibility = View.VISIBLE
        radioGroup.visibility = View.VISIBLE
      }
    }
  }

  private fun getColor(colorResId: Int): Int {
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
      return itemView.context.resources.getColor(colorResId, null);
    } else {
      return itemView.context.resources.getColor(colorResId);
    };
  }

  private fun changeGreWordColor(greModel: GreModel) {
    when (greModel.isCorrect) {
      true -> {
        this.greWord.setTextColor(getColor(R.color.colorPrimaryDark));
      }
      false -> {
        this.greWord.setTextColor(getColor(R.color.solidRed));
      }
      else -> {
        this.greWord.setTextColor(getColor(R.color.solidBlack));
      }
    }
  }

}