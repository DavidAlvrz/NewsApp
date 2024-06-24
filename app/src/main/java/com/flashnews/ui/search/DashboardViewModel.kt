package com.flashnews.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flashnews.model.dto.Article
import com.flashnews.model.rest.Retrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DashboardViewModel : ViewModel() {

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> get() = _articles

    public fun searchNews(query:String) {
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                Retrofit.api.getHomeNews(q = query).body()
            }
            _articles.value = response?.articles
        }
    }
}
