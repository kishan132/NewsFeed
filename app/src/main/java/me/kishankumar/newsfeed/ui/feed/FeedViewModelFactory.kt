package me.kishankumar.newsfeed.ui.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.kishankumar.newsfeed.data.NewsRepo

class FeedViewModelFactory(private val newsRepo: NewsRepo): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FeedViewModel(newsRepo) as T
    }
}