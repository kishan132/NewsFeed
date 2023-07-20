package me.kishankumar.newsfeed.model.entities


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Source(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?
)