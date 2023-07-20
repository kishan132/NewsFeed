package me.kishankumar.newsfeed.db

import android.content.Context
import androidx.room.*
import me.kishankumar.newsfeed.model.entities.ArticleTable
import me.kishankumar.newsfeed.utils.Convertors

@Database(entities = [ArticleTable::class], version = 1)
@TypeConverters(Convertors::class)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

    companion object {

        private var instance: ArticleDatabase? = null

        fun getDatabase(context: Context): ArticleDatabase {

            if (instance == null) {
                instance =
                    Room.databaseBuilder(context, ArticleDatabase::class.java, "articleDB").build()
            }

            return instance!!
        }

    }
}