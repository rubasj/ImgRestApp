package cz.utb.fai.imgrestapp.service

import cz.utb.fai.imgrestapp.api.ApodInfoDomain
import cz.utb.fai.imgrestapp.database.ApodInfoDTO

data class ApodInfoNetwork (

    val date: String,
    val explanation: String,
    val hdurl: String?,
    val media_type: String,
    val service_version: String,
    val title: String,
    val url: String
)

fun ApodInfoNetwork.mapToDatabase(): ApodInfoDTO {
    return ApodInfoDTO(
        date = this.date,
        explanation = this.explanation,
        title = this.title,
        url = this.url
    )
}