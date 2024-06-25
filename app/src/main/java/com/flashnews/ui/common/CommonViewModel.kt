package com.flashnews.ui.common

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.flashnews.model.database.ArticleDatabase
import com.flashnews.model.dto.Article
import kotlinx.coroutines.launch

class CommonViewModel(application: Application) : AndroidViewModel(application) {

    private val articleDao = ArticleDatabase.invoke(application).getArticleDao()

    fun saveArticle(article: Article) {
        viewModelScope.launch {
            articleDao.saveArticle(article)
        }
    }

    fun deleteArticle(article: Article) {
        viewModelScope.launch {
            articleDao.deleteArticle(article)
        }
    }
}
