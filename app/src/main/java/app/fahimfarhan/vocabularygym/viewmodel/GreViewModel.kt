package app.fahimfarhan.vocabularygym.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import app.fahimfarhan.vocabularygym.database.GreModel
import app.fahimfarhan.vocabularygym.repository.GreRepository

class GreViewModel: AndroidViewModel {
    private val greRepository: GreRepository?;
    val greModelsList: ArrayList<GreModel>;



    public constructor(application: Application):super(application) {
        this.greRepository = GreRepository(context = application);
        this.greModelsList = this.greRepository.greModelsList;
    }
}