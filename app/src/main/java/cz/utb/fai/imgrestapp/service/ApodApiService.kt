package cz.utb.fai.imgrestapp.service

import cz.utb.fai.imgrestapp.api.ApodResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApodApiService {

    companion object {
        const val APOD_ENDPOINT_URL = "https://api.nasa.gov/planetary/apod"

    }

    // suspend = async
    @GET(APOD_ENDPOINT_URL)
    suspend fun getApodDataInfo (
        @Query("apiKey") apiKey : String,
        @Query("date") date: String,
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String,
        @Query("count") count: Int,
        @Query("thumbs") thumbs: String
    ): Response<ApodResponseDto>
}