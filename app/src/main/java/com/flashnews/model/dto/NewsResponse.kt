package com.flashnews.model.dto

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)