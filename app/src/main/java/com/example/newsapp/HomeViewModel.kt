package com.example.newsapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.Article
import com.example.newsapp.data.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor (private val repository: HomeRepository) : ViewModel() {

    val newsList: LiveData<NetworkResult<Article>>
        get() =  repository.newsList

    fun getNews(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getNews()
        }
    }

}