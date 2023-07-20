package me.kishankumar.newsfeed.data

import android.content.Context
import android.util.Log
import me.kishankumar.newsfeed.db.ArticleDatabase
import me.kishankumar.newsfeed.extensions.toPositiveLong
import me.kishankumar.newsfeed.model.entities.Article
import me.kishankumar.newsfeed.model.entities.ArticleTable
import me.kishankumar.newsfeed.services.NewsAPI
import me.kishankumar.newsfeed.utils.NetworkUtils

class NewsRepo(
    private val context: Context,
    private val newsAPI: NewsAPI,
    private val articleDatabase: ArticleDatabase
) {

    private val TAG = "mytest"

    suspend fun getNews(
        category: String,
        country: String = "in",
        pageSize: Int,
        page: Int
    ): List<Article>? {

        if (NetworkUtils.isInternetAvailable(context)) {
            //online
            return try {

                Log.d(TAG, "NewsRepo getNews1: $category")

                val response = newsAPI.getNews(category, country, pageSize, page)

                Log.d(TAG, "NewsRepo getNews2: $response")

                return if (response.isSuccessful) {

                    val articles: List<Article>? = response.body()?.articles

                    val articleTable =
                        articles?.let { ArticleTable(category.toPositiveLong, category, it) }
                    Log.d(TAG, "NewsRepo: articleTable- $articleTable")

                    if (articleTable != null) {
                        articleDatabase.articleDao().insertArticles(articleTable)
                    }

                    articles
                } else null  // in case of missing api key

            } catch (e: Exception) {
                null
            }
        } else {
            // room database
            val articleTable: ArticleTable? = articleDatabase.articleDao().getArticles(category)

            Log.d(TAG, "getNews: room database: ${articleTable?.articles}")

            return articleTable?.articles
            //return null
        }

    }

}
