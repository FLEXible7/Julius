package com.example.julius


import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Toast
import kotlinx.android.synthetic.main.news_row.view.*
import com.squareup.picasso.Picasso
import java.io.File
import java.util.*


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

        val goToBrowserBtn = holder.view.go_to_browser_button
        goToBrowserBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.site_url))
            context.startActivity(intent)
        }

        val addNewsButton = holder.view.add_news_button
        addNewsButton.setOnClickListener {
            val fileName = "${UUID.randomUUID()}.deal"
            val filePath = File(context.filesDir, "deal")
            val newsFile = File(filePath, fileName)
            newsFile.createNewFile()

            newsFile.writeText(item.title + "\n" +
                                "События" + "\n" +
                                "Без срока" + "\n" + item.site_url)
            Toast.makeText(context, "Новость добавлена в список заметок", Toast.LENGTH_SHORT).show()
        }

        val shareButton = holder.view.share_news_button
        shareButton.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "type/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, item.site_url)
            context.startActivity(Intent.createChooser(shareIntent, "Поделиться новостью"))
        }
    }



}

class CustomViewHolderNews internal constructor(val view: View): RecyclerView.ViewHolder(view){

}
