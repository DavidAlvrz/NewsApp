package com.flashnews.ui.common

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.flashnews.model.database.ArticleDatabase
import com.flashnews.model.dto.Article
import kotlinx.coroutines.launch

open class CommonViewModel(application: Application) : AndroidViewModel(application) {

    protected val articleDao = ArticleDatabase.invoke(application).getArticleDao()
    protected val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> get() = _articles

    open fun saveArticle(article: Article) {
        viewModelScope.launch {
            articleDao.saveArticle(article)
        }
    }

    open fun deleteArticle(article: Article) {
        viewModelScope.launch {
            articleDao.deleteArticle(article)
        }
    }
}
