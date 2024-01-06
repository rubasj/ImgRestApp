package cz.utb.fai.imgrestapp.api

data class ApodResponseDto (
    val apiKey: String,  // Assuming the API key is a required parameter
    val date: String,
    val startDate: String,
    val endDate: String,
    val count: Int,
    val thumbs: String
)