package cz.utb.fai.imgrestapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import cz.utb.fai.imgrestapp.api.ApodInfoDomain


@Entity(tableName = "apod_info")
data class ApodInfoDTO (
    @PrimaryKey
    val date: String,
    val explanation: String,
    val title: String,
    val url: String
)

fun ApodInfoDTO.mapToDomain(): ApodInfoDomain{
    return ApodInfoDomain(
        date = this.date,
        explanation = this.explanation,
        title = this.title,
        url = this.url
    )
}