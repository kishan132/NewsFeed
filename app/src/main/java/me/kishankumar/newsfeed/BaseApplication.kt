package me.kishankumar.newsfeed

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import me.kishankumar.newsfeed.data.NewsRepo
import me.kishankumar.newsfeed.db.ArticleDatabase

class BaseApplication : Application() {

    lateinit var newsRepo: NewsRepo

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    fun initialize(){
        val newsAPI = WebClient.newsAPI
        val articleDatabase = ArticleDatabase.getDatabase(applicationContext)
        newsRepo = NewsRepo(applicationContext,newsAPI,articleDatabase)
    }

}