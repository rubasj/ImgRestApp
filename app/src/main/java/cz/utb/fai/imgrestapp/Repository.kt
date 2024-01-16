package cz.utb.fai.imgrestapp

import cz.utb.fai.imgrestapp.api.ApodRequest
import cz.utb.fai.imgrestapp.service.ApodApiService
import android.util.Log
import cz.utb.fai.imgrestapp.api.ApodInfoDomain
import cz.utb.fai.imgrestapp.database.MyRoomDatabase
import cz.utb.fai.imgrestapp.database.mapToDomain
import cz.utb.fai.imgrestapp.service.ApodInfoNetwork
import cz.utb.fai.imgrestapp.service.mapToDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class Repository (
    private val apiService: ApodApiService,
    private val database: MyRoomDatabase
){
    val API_KEY = "DEMO_KEY"
    suspend fun getApodInfo(request: ApodRequest): Flow<ApodInfoDomain> {

        refreshApodData(request)

        return  database.apodInfoDao.getPictureByDate(request.date).map { it.mapToDomain() }
    }

    suspend fun refreshApodData(request: ApodRequest) {
        try {
            val apiResponse = apiService.getApodDataInfo(API_KEY, request.date)

            val apodInfoNetwork: ApodInfoNetwork? = apiResponse.body()

            if (apodInfoNetwork != null) {
                val apodInfoDTO = apodInfoNetwork.mapToDatabase()

                withContext(Dispatchers.IO) {
                    database.apodInfoDao.insert(apodInfoDTO)
                }
            } else {
                // no data found on REST API
                Log.v("MYAPP", "Not found on REST API")
            }
        } catch (e: Exception) {
            // Handle PI call errors
            Log.e("MYAPP", "Error refreshing subjects " + e.localizedMessage)
        }
    }
}