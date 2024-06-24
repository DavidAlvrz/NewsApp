package com.flashnews.model.rest

import com.flashnews.model.dto.NewsResponse
import com.flashnews.util.Constants
import com.flashnews.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {


    @GET("/v2/everything")
    suspend fun getHomeNews(
        @Query("q") q: String = "*",
        @Query("apiKey") apiKey: String = API_KEY,
        @Query("page") pageNumber: Int = 1,
        @Query("pageSize") pageSize: Int = 20,
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("language") language: String = "es"
    ): Response<NewsResponse>
}