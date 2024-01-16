package cz.utb.fai.imgrestapp.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.utb.fai.imgrestapp.Repository
import cz.utb.fai.imgrestapp.api.ApodRequest
import cz.utb.fai.imgrestapp.api.ApodInfoDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.lang.Exception

class ApodInfoViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _apodinfoValue = MutableLiveData<ApodInfoDomain>()
    val apodinfoValue: LiveData<ApodInfoDomain> = _apodinfoValue
    val showProgressBar = MutableLiveData<Boolean>()
    val dateMutable = MutableLiveData<ApodRequest?>()
    val processToDetail = MutableLiveData<Boolean>()
    val showHint = MutableLiveData<Boolean>()
    val showNotFound = MutableLiveData<Boolean>()
    fun getApodInfo(requestDto: ApodRequest) {

        viewModelScope.launch {
            try {
                _apodinfoValue.value = repository.getApodInfo(requestDto).firstOrNull()
                showProgressBar.value = false
            } catch (e: Exception) {
                Log.v("MYAPP", "Not found: " + e.message)

                // show not found message
                showNotFound.value = true
                showProgressBar.value = false
            }
        }

    }

    fun search () {
        if (dateMutable.value != null && !dateMutable.value!!.date.isEmpty()) {
            // date request was provided by the user
            showProgressBar.value = true
            getApodInfo(dateMutable.value!!)

        } else {
            // date request was not provided, show hint text view
            showHint.value = true
        }
    }
    fun toDetail () {
        processToDetail.value = true
    }

    fun hideHintAndNotFound () {
        showHint.value = false
        showNotFound.value = false
    }

}