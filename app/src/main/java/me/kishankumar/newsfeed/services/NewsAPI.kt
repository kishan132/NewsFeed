package me.kishankumar.newsfeed.services

import me.kishankumar.newsfeed.model.responses.NewsListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {
    @GET("top-headlines")
    suspend fun getNews(
        @Query("category") category: String ="sports",
        @Query("country") country: String ="in",
        @Query("pageSize") pageSize: Int =10,
        @Query("page") page: Int =1
    ) : Response<NewsListResponse>
}