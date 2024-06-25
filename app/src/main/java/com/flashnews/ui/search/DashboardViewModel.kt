package com.flashnews.ui.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flashnews.model.dto.Article
import com.flashnews.model.rest.Retrofit
import com.flashnews.ui.common.CommonViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DashboardViewModel(application: Application) : CommonViewModel(application) {

    fun searchNews(query: String) {
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                Retrofit.api.getHomeNews(q = query, pageSize = 20).body()
            }
            _articles.value = response?.articles
        }
    }
}
