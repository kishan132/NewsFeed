package me.kishankumar.newsfeed

import me.kishankumar.newsfeed.services.NewsAPI
import retrofit2.converter.moshi.MoshiConverterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object WebClient {

    var apiKey: String = "ae59f1d83dc54790a53525616281972b"

    private val authInterceptor = Interceptor { chain ->
        var req = chain.request()

        apiKey.let {
            req = req.newBuilder()
                .addHeader("Authorization",it)
                .build()
        }
        chain.proceed(req)
    }

    val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(2, TimeUnit.SECONDS)
        .readTimeout(5, TimeUnit.SECONDS)
        .writeTimeout(5, TimeUnit.SECONDS)

    val retrofitBuilder = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .addConverterFactory(MoshiConverterFactory.create())


    val newsAPI: NewsAPI = retrofitBuilder
        .client(okHttpClient.addInterceptor(authInterceptor).build())
        .build()
        .create(NewsAPI::class.java)

}