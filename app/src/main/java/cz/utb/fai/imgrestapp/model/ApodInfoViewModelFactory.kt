package cz.utb.fai.imgrestapp.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cz.utb.fai.imgrestapp.Repository
import java.lang.IllegalArgumentException

/*
Zajisteni, ze budu vytvaret jednu instanci
 */
class ApodInfoViewModelFactory(
    private val repository: Repository
) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ApodInfoViewModel::class.java)) {
            return ApodInfoViewModel(repository) as T
        }
        throw IllegalArgumentException("Wrong view model class !!")
    }
}
