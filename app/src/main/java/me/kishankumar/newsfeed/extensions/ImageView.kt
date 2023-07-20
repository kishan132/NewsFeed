package me.kishankumar.newsfeed.extensions

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import me.kishankumar.newsfeed.R

fun ImageView.loadImage(url: String?, isCircleCrop: Boolean) {

    if (isCircleCrop)
        Glide.with(this).load(url).placeholder(R.drawable.placeholder_image).circleCrop().into(this)
    else
        Glide.with(this).load(url).placeholder(R.drawable.placeholder_image).into(this)

}