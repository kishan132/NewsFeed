package me.kishankumar.newsfeed.ui.feed

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import me.kishankumar.newsfeed.MainActivity.Companion.mainNewsRepo
import me.kishankumar.newsfeed.databinding.FragmentNewsFeedBinding

class NewsFeedFragment : Fragment() {

    private val TAG = "mytest"
    private var binding: FragmentNewsFeedBinding? = null
    private lateinit var viewModel: FeedViewModel
    private lateinit var newsFeedAdapter: NewsFeedAdapter

    private var position = 0

    companion object {
        fun newInstance(position: Int): NewsFeedFragment {

            val newsFeedFragment = NewsFeedFragment()
            val bundle = Bundle()
            bundle.putInt("POSITION", position)
            newsFeedFragment.arguments = bundle

            return newsFeedFragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNewsFeedBinding.inflate(layoutInflater, container, false)

        position = arguments?.getInt("POSITION") ?: 0

        val newsRepo = mainNewsRepo
        viewModel =
            ViewModelProvider(this, FeedViewModelFactory(newsRepo))[FeedViewModel::class.java]

        newsFeedAdapter = NewsFeedAdapter(requireContext())

        binding!!.rvNewsfeed.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsFeedAdapter
        }

        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "onViewCreated: NewsFeedFragment $position")

        when (position) {
            0 -> {
                viewModel.getNews("business", "in", 10, 1)
                viewModel.feed_business.observe(viewLifecycleOwner, Observer {
                    Log.d(TAG, "onViewCreated:business- ${it?.get(0)?.title}")
                    newsFeedAdapter.submitList(it)
                })

            }

            1 -> {
                viewModel.getNews("entertainment", "in", 10, 1)
                viewModel.feed_entertainment.observe(viewLifecycleOwner, Observer {
                    Log.d(TAG, "onViewCreated:entertainment- ${it?.get(0)?.title}")
                    newsFeedAdapter.submitList(it)
                })
            }

            2 -> {
                viewModel.getNews("general", "in", 10, 1)
                viewModel.feed_general.observe(viewLifecycleOwner, Observer {
                    Log.d(TAG, "onViewCreated:general- ${it?.get(0)?.title}")
                    newsFeedAdapter.submitList(it)
                })
            }

            3 -> {
                viewModel.getNews("sports", "in", 10, 1)
                viewModel.feed_sports.observe(viewLifecycleOwner, Observer {
                    Log.d(TAG, "onViewCreated:sports- ${it?.get(0)?.title}")
                    newsFeedAdapter.submitList(it)
                })
            }

            4 -> {
                viewModel.getNews("technology", "in", 10, 1)
                viewModel.feed_technology.observe(viewLifecycleOwner, Observer {
                    Log.d(TAG, "onViewCreated:technology- ${it?.get(0)?.title}")
                    newsFeedAdapter.submitList(it)
                })
            }

            5 -> {
                viewModel.getNews("health", "in", 10, 1)
                viewModel.feed_health.observe(viewLifecycleOwner, Observer {
                    Log.d(TAG, "onViewCreated:health- ${it?.get(0)?.title}")
                    newsFeedAdapter.submitList(it)
                })
            }

            6 -> {
                viewModel.getNews("science", "in", 10, 1)
                viewModel.feed_science.observe(viewLifecycleOwner, Observer {
                    Log.d(TAG, "onViewCreated:science- ${it?.get(0)?.title}")
                    newsFeedAdapter.submitList(it)
                })
            }

            else -> {
                viewModel.getNews("general", "in", 10, 1)
                viewModel.feed_general.observe(viewLifecycleOwner, Observer {
                    Log.d(TAG, "onViewCreated:general- ${it?.get(0)?.title}")
                    newsFeedAdapter.submitList(it)
                })
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}