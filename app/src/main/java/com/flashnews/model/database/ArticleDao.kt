package com.flashnews.model.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.flashnews.model.dto.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArticle(article: Article): Long

    @Query("SELECT * FROM articles")
    suspend fun getArticles(): List<Article>

    @Delete
    suspend fun deleteArticle(article: Article)

}