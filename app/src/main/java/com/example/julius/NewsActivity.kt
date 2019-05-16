package com.example.julius

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import kotlinx.android.synthetic.main.activity_news.*
import kotlinx.android.synthetic.main.activity_places.*
import okhttp3.*
import java.io.IOException


class NewsActivity : AppCompatActivity() {

    val bNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId)
        {
            R.id.news ->{
                return@OnNavigationItemSelectedListener true
            }

            R.id.deals ->{
                val dealsIntent = Intent(this, MainActivity::class.java)
                startActivity(dealsIntent)
                return@OnNavigationItemSelectedListener true
            }

            R.id.interesting_places ->{
                val placesIntent = Intent(this, PlacesActivity::class.java)
                startActivity(placesIntent)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val itemDecoration = DividerItemDecoration(applicationContext, 1)
        itemDecoration.setDrawable(ColorDrawable(Color.DKGRAY))
        news_recycler.addItemDecoration(itemDecoration)

        news_recycler.layoutManager = LinearLayoutManager(this)


        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation_news)
        bottomNavigation.getMenu().getItem(1).setChecked(true)
        bottomNavigation.setOnNavigationItemSelectedListener(bNavigationItemSelectedListener)

        loadData()

    }


    fun loadData(){

        val url = "https://kudago.com/public-api/v1.2/news/?fields=title,site_url,images&page_size=20&order_by=-publication_date&actual_only=1"

        val request = Request.Builder()
            .url(url)
            .build()

        val httpClient = OkHttpClient()
        httpClient.newCall(request).enqueue(object : Callback {

            override fun onResponse(call: Call, response: Response) {

                val body = response.body()?.string()
                val gson = GsonBuilder().create()
                val newsList = gson.fromJson(body, NewsList::class.java)

                runOnUiThread {
                    news_recycler.adapter = NewsAdapter(this@NewsActivity, newsList)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
            }
        })
    }

}


class NewsList(val results : List<Results>){}

class Results (val title : String, @SerializedName("images") val imgs : List<Imgs>, val site_url : String)

class Imgs(@SerializedName("image") val img : String, @SerializedName("source") val src : Src)

class Src(@SerializedName("name") val site : String, val link : String)


