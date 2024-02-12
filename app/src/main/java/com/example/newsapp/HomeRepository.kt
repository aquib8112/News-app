package com.example.newsapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.data.Article
import com.example.newsapp.data.NetworkResult
import com.example.newsapp.data.NewsApiService
import javax.inject.Inject

class HomeRepository @Inject constructor(private val newsApiService: NewsApiService){

    private val _newsList = MutableLiveData<NetworkResult<Article>>()
    val newsList: LiveData<NetworkResult<Article>>
        get() =  _newsList

    suspend fun getNews() {
        val result = newsApiService.getNews()
        if (result.isSuccessful && result.body() != null){
            _newsList.postValue(NetworkResult.OnSuccess(result.body()))
        } else if (result.errorBody() != null){
            _newsList.postValue(NetworkResult.OnFailure(message = "Something went wrong"))
        }
    }

}