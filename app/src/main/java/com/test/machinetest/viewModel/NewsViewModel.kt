package com.test.machinetest.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.machinetest.db.NewsDatabase
import com.test.machinetest.model.Article
import com.test.machinetest.network.Error
import com.test.machinetest.network.NetworkResponse
import com.test.machinetest.network.RestClient
import com.test.machinetest.network.Success
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {


    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->

    }
    val newsResponse = MutableLiveData<NetworkResponse<List<Article>?>>()

    fun fetchNews(db: NewsDatabase) {
        viewModelScope.launch(exceptionHandler) {
            getLocalNews(db)
            RestClient.get().getHeadlines(category = "business").apply {
                if (isSuccessful) {
                    newsResponse.postValue(Success(body()?.articles))
                    CoroutineScope(Dispatchers.IO).launch {
                        db.newsDao().deleteAllNews()
                        for (news in body()?.articles ?: emptyList())
                            db.newsDao().insert(news)
                    }
                } else {
                    newsResponse.postValue(Error(errorBody()))
                }
            }
        }
    }


    private fun getLocalNews(db: NewsDatabase) {
        CoroutineScope(Dispatchers.IO).launch(exceptionHandler) {
            newsResponse.postValue(Success(db.newsDao().getAllNews()))
        }
    }

}