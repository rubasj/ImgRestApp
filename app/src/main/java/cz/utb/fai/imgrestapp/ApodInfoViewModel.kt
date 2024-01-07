package cz.utb.fai.imgrestapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.utb.fai.imgrestapp.api.ApodRequestDto
import cz.utb.fai.imgrestapp.api.ApodResponseDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ApodInfoViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _apodinfoValue = MutableLiveData<ApodResponseDto>()
    val apodinfoValue: LiveData<ApodResponseDto> = _apodinfoValue
    fun getApodInfo(requestDto: ApodRequestDto) {

        viewModelScope.launch(Dispatchers.IO) {
           val result =  repository.getApodResponse(requestDto)
            _apodinfoValue.postValue(result)
        }
    }


}