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


}