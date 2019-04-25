package com.example.julius

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.news_row.view.*
import android.support.v4.content.ContextCompat.startActivity



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
        holder.view.title.text = item.title
        holder.view.site_url.text = item.site_url

        /*holder.view.site_url.setOnClickListener{
            val url = item.site_url
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }*/
    }

}

class CustomViewHolderNews(val view: View): RecyclerView.ViewHolder(view){

}