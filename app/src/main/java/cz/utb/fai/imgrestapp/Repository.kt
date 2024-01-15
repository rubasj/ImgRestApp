package cz.utb.fai.imgrestapp

import cz.utb.fai.imgrestapp.api.ApodRequestDto
import cz.utb.fai.imgrestapp.service.ApodApiService
import android.util.Log
import cz.utb.fai.imgrestapp.api.ApodInfoDomain
import cz.utb.fai.imgrestapp.service.mapToDomain

class Repository (
    private val apiService: ApodApiService
){
    val API_KEY = "DEMO_KEY"
    suspend fun getApodResponse(request: ApodRequestDto): ApodInfoDomain? {

            // call REST API service to response
            val response = apiService.getApodDataInfo(
                apiKey = API_KEY,
                date = request.date
            )

        if (response.isSuccessful){
            val apodInfoNetwork = response.body()
            return apodInfoNetwork?.mapToDomain()
        }
        else {
            Log.ERROR
            Log.d(response.code().toString(), response.raw().toString())
            return null
        }


    }
}