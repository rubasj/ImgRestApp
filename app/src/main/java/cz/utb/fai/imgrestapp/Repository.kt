package cz.utb.fai.imgrestapp

import cz.utb.fai.imgrestapp.api.ApodRequestDto
import cz.utb.fai.imgrestapp.service.ApodApiService
import android.util.Log
import cz.utb.fai.imgrestapp.api.ApodResponseDto

class Repository (
    private val apiService: ApodApiService
){
    val API_KEY = "DEMO_KEY"
    suspend fun getApodResponse(request: ApodRequestDto): ApodResponseDto? {

            // call REST API service to response
            val response = apiService.getApodDataInfo(
                apiKey = API_KEY,
                date = request.date,
                startDate = request.startDate,
                endDate = request.endDate,
                count = request.count,
                thumbs = request.thumbs
            )

        if (response.isSuccessful){
            return response.body()
        }
        else {
            Log.ERROR
            return null

        }


    }
}