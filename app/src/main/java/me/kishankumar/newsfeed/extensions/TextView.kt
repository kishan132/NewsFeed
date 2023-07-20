package me.kishankumar.newsfeed.extensions

import android.annotation.SuppressLint
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("ConstantLocale")
val isoDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())

@SuppressLint("ConstantLocale")
val appDateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())

var TextView.timestamp: String
    set(value) {
        val date = isoDateFormat.parse(value)
        text = date?.let { appDateFormat.format(it) }
    }
    get() {
        val date = appDateFormat.parse(text.toString())
        return isoDateFormat.format(date!!)
    }

// another way to right
/*
fun TextView.showFormattedDate(timestamp: String) {

    val date = isoDateFormat.parse(timestamp)
    text = date?.let { appDateFormat.format(it) }
}*/
