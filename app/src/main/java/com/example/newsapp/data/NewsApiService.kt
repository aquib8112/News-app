package com.example.newsapp.data

import retrofit2.Response
import retrofit2.http.GET

interface NewsApiService {
    @GET("top-headlines?country=in&apiKey=2b7b799e6dd544a2a805cc1c2e0ee4ce")
    suspend fun getNews(): Response<Article>
}