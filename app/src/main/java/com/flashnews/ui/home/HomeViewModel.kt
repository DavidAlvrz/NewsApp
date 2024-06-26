package com.flashnews.ui.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flashnews.model.dto.Article
import com.flashnews.model.dto.Source
import com.flashnews.model.rest.NewsAPI
import com.flashnews.model.rest.Retrofit
import com.flashnews.ui.common.CommonViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(application: Application) : CommonViewModel(application){

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
