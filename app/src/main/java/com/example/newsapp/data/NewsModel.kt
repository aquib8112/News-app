package com.example.newsapp.data

import com.google.gson.annotations.SerializedName

data class Article(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleDetails>
)

data class Source(
    val id: String?,
    val name: String
)

data class ArticleDetails(
    val source: Source,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    @SerializedName("urlToImage") val imageUrl: String?,
    @SerializedName("publishedAt") val publishDate: String,
    val content: String?
)


