package cz.utb.fai.imgrestapp.service

import cz.utb.fai.imgrestapp.api.ApodInfoDomain
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
        @Query("api_key") apiKey : String,
        @Query("date") date: String
    ): Response<ApodInfoNetwork>
}