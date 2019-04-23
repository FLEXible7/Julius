package com.example.julius

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.news_row.view.*

class NewsAdapter(val newsList: NewsList): RecyclerView.Adapter<CustomViewHolderNews>() {


    override fun getItemCount(): Int {

        return newsList.results.count()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolderNews {

        val layoutInflater = LayoutInflater.from(p0.context)
        val cellForRow = layoutInflater.inflate(R.layout.news_row, p0, false)

        return CustomViewHolderNews(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolderNews, position: Int) {

        val item = newsList.results.get(position)
        holder.view.title.text = item.details.title

        val description = item.details.description.replace("<p>", "").replace("</p>", "").replace("\\n", "")
        holder.view.description.text = description
    }

}

class CustomViewHolderNews(val view: View): RecyclerView.ViewHolder(view){

}