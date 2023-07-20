package me.kishankumar.newsfeed.ui.feed

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.kishankumar.newsfeed.data.NewsRepo
import me.kishankumar.newsfeed.model.entities.Article

class FeedViewModel(private val newsRepo: NewsRepo) : ViewModel() {

    private val TAG = "mytest"

    private val _feed_business = MutableLiveData<List<Article>?>()
    val feed_business: LiveData<List<Article>?> = _feed_business

    private val _feed_entertainment = MutableLiveData<List<Article>?>()
    val feed_entertainment: LiveData<List<Article>?> = _feed_entertainment

    private val _feed_general = MutableLiveData<List<Article>?>()
    val feed_general: LiveData<List<Article>?> = _feed_general

    private val _feed_health = MutableLiveData<List<Article>?>()
    val feed_health: LiveData<List<Article>?> = _feed_health

    private val _feed_science = MutableLiveData<List<Article>?>()
    val feed_science: LiveData<List<Article>?> = _feed_science

    private val _feed_sports = MutableLiveData<List<Article>?>()
    val feed_sports: LiveData<List<Article>?> = _feed_sports

    private val _feed_technology = MutableLiveData<List<Article>?>()
    val feed_technology: LiveData<List<Article>?> = _feed_technology


    fun getNews(category: String, country: String, pageSize: Int, page: Int) =
        viewModelScope.launch(Dispatchers.IO) {

            Log.d(TAG, "getNews: viewmodel1 $category")

            newsRepo.getNews(category, country, pageSize, page).let {

                Log.d(TAG, "getNews: viewmodel2 $category, - 0th title- ${it?.get(0)?.title}")

                when (category) {
                    "business" -> _feed_business.postValue(it)
                    "entertainment" -> _feed_entertainment.postValue(it)
                    "general" -> _feed_general.postValue(it)
                    "health" -> _feed_health.postValue(it)
                    "science" -> _feed_science.postValue(it)
                    "sports" -> _feed_sports.postValue(it)
                    "technology" -> _feed_technology.postValue(it)
                }

            }
        }

}