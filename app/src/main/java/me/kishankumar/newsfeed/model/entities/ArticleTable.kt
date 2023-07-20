package me.kishankumar.newsfeed.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articleTable")
data class ArticleTable(
    @PrimaryKey(autoGenerate = true)
    val id:Long,

    val category:String,
    val articles: List<Article>
)
