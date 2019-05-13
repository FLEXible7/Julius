package com.example.julius


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.news_row.view.*
import com.squareup.picasso.Picasso



class NewsAdapter(val context : Context, val newsList: NewsList): RecyclerView.Adapter<CustomViewHolderNews>() {

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

        val newsImage = holder.view.news_image
        Picasso.get().load(item.imgs.get(0).img).resize(1000 , 600).centerCrop().into(newsImage)

        newsImage.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.site_url))
            context.startActivity(intent)
        }
    }

}

class CustomViewHolderNews(val view: View): RecyclerView.ViewHolder(view){

}