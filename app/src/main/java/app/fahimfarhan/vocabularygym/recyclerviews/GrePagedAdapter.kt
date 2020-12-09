package app.fahimfarhan.vocabularygym.recyclerviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import app.fahimfarhan.vocabularygym.R
import app.fahimfarhan.vocabularygym.mvvm.database.GreModel
import kotlinx.coroutines.CoroutineDispatcher
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class GrePagedAdapter : PagingDataAdapter<GreModel, GreViewHolder> {
 var randomMeanings: ArrayList<String> = ArrayList();

  constructor(
    diffCallback: DiffUtil.ItemCallback<GreModel>,
    mainDispatcher: CoroutineDispatcher,
    workerDispatcher: CoroutineDispatcher): super(diffCallback, mainDispatcher, workerDispatcher) {

  }

   override fun onBindViewHolder(holder: GreViewHolder, uiPosition: Int) {
     val dataPosition = uiPosition;
     val choices: ArrayList<String> = ArrayList();

     val actualGreMeaning: String = getItem(dataPosition)!!.greMeaning;

     choices.add(actualGreMeaning);

     val r1 = Random.nextInt(randomMeanings.size);
     choices.add(randomMeanings[r1]);

     val r2 = Random.nextInt(randomMeanings.size);
     choices.add(randomMeanings[r2]);

     val r3 = Random.nextInt(randomMeanings.size);
     choices.add(randomMeanings[r3]);

     Collections.shuffle(choices);

     holder.bindView(getItem(dataPosition)!!, choices, actualGreMeaning, ( (dataPosition+1).toString()+" / " + itemCount.toString()) );
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GreViewHolder {
     val view = LayoutInflater.from(parent.context)
         .inflate(R.layout.gre_item_row, parent, false);
     return GreViewHolder(view);
   }

   object GreDiffCallBack: DiffUtil.ItemCallback<GreModel>() {
     override fun areItemsTheSame(oldItem: GreModel, newItem: GreModel): Boolean {
       return oldItem.pk.equals(newItem.pk);
     }

     override fun areContentsTheSame(oldItem: GreModel, newItem: GreModel): Boolean {
       return oldItem.equals(newItem);
     }

   }
 }