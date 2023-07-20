package me.kishankumar.newsfeed.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.kishankumar.newsfeed.model.entities.Article
import me.kishankumar.newsfeed.model.entities.ArticleTable

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articleTable: ArticleTable)

    @Query("SELECT * FROM articleTable WHERE category = :category")
    suspend fun getArticles(category:String) : ArticleTable?
}