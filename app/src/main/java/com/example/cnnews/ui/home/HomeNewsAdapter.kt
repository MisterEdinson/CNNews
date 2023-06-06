package com.example.cnnews.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cnnews.R
import com.example.cnnews.data.local.dao.model.NewsBookmarkEntity
import com.example.cnnews.data.network.model.ArticlesItem
import kotlinx.android.synthetic.main.item_news.view.*

class HomeNewsAdapter : RecyclerView.Adapter<HomeNewsAdapter.NewsViewHolder>() {
    var listNews = emptyList<NewsBookmarkEntity>()

    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = listNews[position]
        holder.itemView.apply {
            tvTitleNews.text = item.title
            tvDescNews.text = item.description
            tvAuthNews.text = item.author
            Glide.with(this).load(item.urlToImage).into(imgNews)

        }
    }

    override fun getItemCount(): Int {
        return listNews.size
    }

    fun setList(list: List<NewsBookmarkEntity>) {
        listNews = list
        notifyDataSetChanged()
    }
}