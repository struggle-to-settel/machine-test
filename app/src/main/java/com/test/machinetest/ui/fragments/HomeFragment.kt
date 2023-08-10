package com.test.machinetest.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import com.example.newsalltime.databinding.FragmentHomeBinding
import com.test.machinetest.db.NewsDatabase
import com.test.machinetest.network.Error
import com.test.machinetest.network.Success
import com.test.machinetest.ui.adapters.NewsAdapter
import com.test.machinetest.viewModel.NewsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter

    override fun getLayout(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsAdapter = NewsAdapter {
            newsAdapter.getItemAt(it)?.let { news ->
                CoroutineScope(Dispatchers.IO).launch {
                    NewsDatabase.getInstance(requireContext()).newsDao().delete(news)
                }
            }
            newsAdapter.removeAt(it)
        }

        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        viewModel.newsResponse.observe(viewLifecycleOwner) {
            if (lifecycle.currentState == Lifecycle.State.RESUMED) {
                when (it) {

                    is Success -> {
                        Log.d("newsResponse", "${it.data?.count() ?: 0}")
                        newsAdapter.setList(it.data ?: emptyList())
                    }

                    is Error -> {

                    }
                }
            }
        }

        binding.rvNews.adapter = newsAdapter

        viewModel.fetchNews(NewsDatabase.getInstance(requireContext()))
    }
}