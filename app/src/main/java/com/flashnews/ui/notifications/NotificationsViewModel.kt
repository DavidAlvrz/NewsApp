package com.flashnews.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.flashnews.model.database.ArticleDatabase
import com.flashnews.model.dto.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotificationsViewModel(application: Application) : AndroidViewModel(application) {

    private val articleDao = ArticleDatabase.invoke(application).getArticleDao()
    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> get() = _articles

    init {
        loadSavedArticles()
    }

    private fun loadSavedArticles() {
        viewModelScope.launch {
            val savedArticles = withContext(Dispatchers.IO) {
                // Cambiar para devolver la lista completa de artículos
                articleDao.getArticles()
            }
            _articles.postValue(savedArticles)
        }
    }
}
