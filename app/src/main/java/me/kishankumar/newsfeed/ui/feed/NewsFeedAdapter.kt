package me.kishankumar.newsfeed.ui.feed

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.kishankumar.newsfeed.R
import me.kishankumar.newsfeed.databinding.NewsFeedItemBinding
import me.kishankumar.newsfeed.extensions.loadImage
import me.kishankumar.newsfeed.extensions.timestamp
import me.kishankumar.newsfeed.model.entities.Article

class NewsFeedAdapter(private val context: Context) : ListAdapter<Article,NewsFeedAdapter.NewsFeedViewHolder>(object : DiffUtil.ItemCallback<Article>(){
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.toString() == newItem.toString()
    }

}){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsFeedViewHolder {
        val view : View = LayoutInflater.from(context).inflate(R.layout.news_feed_item,parent,false)
        return NewsFeedViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsFeedViewHolder, position: Int) {

        val article = getItem(position)

        NewsFeedItemBinding.bind(holder.itemView).apply {

            imageView.loadImage(article.urlToImage.toString(),false)
            title.text = article.title ?: "Title Not Available"
            auther.text = article.author ?: "No Author"
            date.timestamp = article.publishedAt

        }

    }

    inner class NewsFeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}