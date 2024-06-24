package com.flashnews.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flashnews.model.dto.Article
import com.flashnews.model.dto.Source
import com.flashnews.model.rest.NewsAPI
import com.flashnews.model.rest.Retrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> get() = _articles

    init {
        loadArticles()
    }

    private fun loadArticles() {
        viewModelScope.launch {
            val exampleArticles = withContext(Dispatchers.IO) {
                Retrofit.api.getHomeNews().body()
            }
            _articles.value = exampleArticles?.articles
        }
    }
}
