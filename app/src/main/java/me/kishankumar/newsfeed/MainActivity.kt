package me.kishankumar.newsfeed

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import me.kishankumar.newsfeed.data.NewsRepo
import me.kishankumar.newsfeed.databinding.ActivityMainBinding
import me.kishankumar.newsfeed.ui.auth.LoginActivity
import me.kishankumar.newsfeed.utils.NetworkUtils

class MainActivity : AppCompatActivity() {

    private val TAG = "mytest"
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    val list = listOf<String>(
        "business",
        "entertainment",
        "general",
        "sports",
        "technology",
        "health",
        "science"
    )

    private lateinit var arrayAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setTitleTextColor(resources.getColor(R.color.black))
        binding.toolbar.setSubtitleTextColor(resources.getColor(R.color.black))
        setSupportActionBar(binding.toolbar)

        auth = Firebase.auth

        mainNewsRepo = (application as BaseApplication).newsRepo

        Toast.makeText(
            this,
            "online - ${NetworkUtils.isInternetAvailable(this)}",
            Toast.LENGTH_SHORT
        ).show()

        val pagerAdapter = PagerAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.adapter = pagerAdapter

        arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list)
        binding.listView.adapter = arrayAdapter

        binding.listView.setOnItemClickListener { parent, view, position, id ->

            Log.d(TAG, "onCreate listView: ${view.id} - $position - $id")

            binding.viewPager.currentItem = position
            binding.listView.visibility = View.GONE

        }

        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = pagerAdapter.getPageTitle(position)
        }.attach()

    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var mainNewsRepo: NewsRepo
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.search -> {
                Log.d(TAG, "onOptionsItemSelected: search")
                binding.listView.visibility = View.VISIBLE
                handleSearch(item)
            }

            R.id.logout -> {
                Log.d(TAG, "onOptionsItemSelected: logout")
                auth.signOut()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun handleSearch(item: MenuItem) {

        val searchView: SearchView = item.actionView as SearchView
        searchView.queryHint = "type here to search"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.listView.visibility = View.GONE
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                arrayAdapter.filter.filter(newText)
                return false
            }

        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (binding.viewPager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle
            // the Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            binding.viewPager.currentItem = binding.viewPager.currentItem - 1
        }
    }
}