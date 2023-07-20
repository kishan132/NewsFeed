package me.kishankumar.newsfeed.model.responses


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.kishankumar.newsfeed.model.entities.Article

@JsonClass(generateAdapter = true)
data class NewsListResponse(
    @Json(name = "articles")
    val articles: List<Article>?,
    @Json(name = "status")
    val status: String?,
    @Json(name = "totalResults")
    val totalResults: Int?
)