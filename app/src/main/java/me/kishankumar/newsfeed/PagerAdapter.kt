package me.kishankumar.newsfeed

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import me.kishankumar.newsfeed.ui.feed.NewsFeedFragment

class PagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fm, lifecycle) {

    private val TAG = "mytest"
    val list = listOf<String>("business","entertainment","general","sports","technology","health","science")

    override fun getItemCount(): Int = list.size

    override fun createFragment(position: Int): Fragment {
        Log.d(TAG, "createFragment: $position")
        return NewsFeedFragment.newInstance(position)
    }

    fun getPageTitle(position: Int):String {
        //Log.d(TAG, "getPageTitle: $position")
        return list[position]
    }

}