package cz.utb.fai.imgrestapp

import android.app.Application
import cz.utb.fai.imgrestapp.service.ApodApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication : Application(){


    // creating instances via lazy loading
    val apiService : ApodApiService by lazy {
        //create client for retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/planetary/apod")
            .addConverterFactory(GsonConverterFactory.create()) // Json converter
            .build()

        retrofit.create(ApodApiService::class.java)
    }

    val repository: Repository by lazy {
        Repository(apiService)
    }

    override fun onCreate() {
        super.onCreate()
    }



}