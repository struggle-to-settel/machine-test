package com.test.machinetest.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)

@Entity(tableName = "news")
data class Article(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
)

