package me.kishankumar.newsfeed.utils

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import me.kishankumar.newsfeed.model.entities.Article

class Convertors {

    private val gson = Gson()

    @TypeConverter
    fun fromListToString(articles:List<Article>):String{
        return gson.toJson(articles)
    }

    @TypeConverter
    fun fromStringToList(dataStr:String):List<Article>{
        val listType = object : TypeToken<List<Article>>() {}.type
        return gson.fromJson(dataStr, listType)
    }

}