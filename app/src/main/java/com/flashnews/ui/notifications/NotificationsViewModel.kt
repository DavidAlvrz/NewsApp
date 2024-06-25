package com.flashnews.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.flashnews.model.database.ArticleDatabase
import com.flashnews.model.dto.Article
import com.flashnews.ui.common.CommonViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotificationsViewModel(application: Application) : CommonViewModel(application) {

    init {
        loadSavedArticles()
    }

    fun loadSavedArticles() {
        viewModelScope.launch {
            val savedArticles = withContext(Dispatchers.IO) {
                articleDao.getArticles()
            }
            _articles.postValue(savedArticles)
        }
    }

    override fun saveArticle(article: Article) {
        super.saveArticle(article)
        loadSavedArticles()
    }

    override fun deleteArticle(article: Article) {
        super.deleteArticle(article)
        loadSavedArticles()
    }

}
