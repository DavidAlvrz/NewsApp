package com.flashnews.ui.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.flashnews.R
import com.flashnews.model.dto.Article
import com.flashnews.ui.common.CommonViewModel
import com.flashnews.ui.home.NotificationsViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsAdapter(private val context: Context?, private val viewModel: CommonViewModel) : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivArticleImage: ImageView = itemView.findViewById(R.id.ivPhoto)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        val tvDate: TextView = itemView.findViewById(R.id.tvDate)
    }

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.article_recylcer_item, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.tvTitle.text = article.title
        holder.tvDescription.text = article.description
        holder.tvDate.text = article.publishedAt
        Picasso.get().load(article.urlToImage).into(holder.ivArticleImage)

        holder.itemView.setOnClickListener { view ->
            showPopupMenu(view, article)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private fun showPopupMenu(view: View, article: Article) {
        val popup = PopupMenu(context!!, view)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.context_menu, popup.menu)

        //Show delete option only at stored articles fragment
        if (viewModel !is NotificationsViewModel) {
            popup.menu.findItem(R.id.action_delete).isVisible = false
        }

        CoroutineScope(Dispatchers.Main).launch {
            val isStored = viewModel.isArticleStored(article)
            if (isStored) {
                popup.menu.findItem(R.id.action_save).isVisible = false
            }
        }

        popup.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_visit -> {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
                    context.startActivity(intent)
                    true
                }
                R.id.action_save -> {
                    viewModel.saveArticle(article)
                    true
                }
                R.id.action_delete -> {
                    viewModel.deleteArticle(article)
                    true
                }
                else -> false
            }
        }
        popup.show()
    }

}
