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
//            val exampleArticles = withContext(Dispatchers.IO) {
//                Retrofit.api.getHomeNews().body()
//            }
//            _articles.value = exampleArticles?.articles
            _articles.value = getMockedArticles()
        }
    }

    private fun getMockedArticles(): List<Article> {
        return listOf(
            Article(
                id = 1,
                author = "John Doe",
                content = "Bitcoin has seen a significant rise in value over the past few weeks...",
                description = "Bitcoin price surges to new highs.",
                publishedAt = "2024-06-01T12:00:00Z",
                source = Source(id = "1", name = "ReadWrite"),
                title = "Bitcoin Price Hits New Highs",
                url = "https://readwrite.com/bitcoin-price-hits-new-highs",
                urlToImage = "https://readwrite.com/wp-content/uploads/2024/05/Bitcoin-price.jpg"
            ),
            Article(
                id = 2,
                author = "Jane Smith",
                content = "The gaming industry is booming with the release of the latest titles...",
                description = "Latest gaming trends in 2024.",
                publishedAt = "2024-06-10T15:30:00Z",
                source = Source(id = "2", name = "ReadWrite"),
                title = "Gaming Industry Trends in 2024",
                url = "https://readwrite.com/gaming-industry-trends-2024",
                urlToImage = "https://readwrite.com/wp-content/uploads/2024/06/ea85a934-c8fc-4d65-9279-ff85bb79fbae.webp"
            ),
            Article(
                id = 3,
                author = "Emily Johnson",
                content = "Advancements in AI technology are paving the way for smarter solutions...",
                description = "AI technology advancements in 2024.",
                publishedAt = "2024-06-15T09:00:00Z",
                source = Source(id = "3", name = "ReadWrite"),
                title = "The Future of AI Technology",
                url = "https://readwrite.com/future-of-ai-technology",
                urlToImage = "https://readwrite.com/wp-content/uploads/2024/06/fc23b9c7-9438-4ba4-a436-fabd4fed874e.webp"
            ),
            Article(
                id = 4,
                author = "Michael Brown",
                content = "Satoshi Nakamoto's legacy continues to influence the world of cryptocurrency...",
                description = "The legacy of Satoshi Nakamoto.",
                publishedAt = "2024-06-20T18:00:00Z",
                source = Source(id = "4", name = "ReadWrite"),
                title = "Satoshi Nakamoto: The Rebel Innovator",
                url = "https://readwrite.com/satoshi-nakamoto-rebel-innovator",
                urlToImage = "https://readwrite.com/wp-content/uploads/2024/06/Featured-Image-for-Rebel-Satoshi.jpg"
            ),
            Article(
                id = 5,
                author = "Sarah Wilson",
                content = "New advancements in blockchain technology are set to revolutionize various industries...",
                description = "Revolutionary blockchain advancements.",
                publishedAt = "2024-06-25T21:45:00Z",
                source = Source(id = "5", name = "ReadWrite"),
                title = "Blockchain Advancements in 2024",
                url = "https://readwrite.com/blockchain-advancements-2024",
                urlToImage = "https://readwrite.com/wp-content/uploads/2024/06/GQij8RdakAU8Vor-min-scaled.jpeg"
            )
        )
    }

}
