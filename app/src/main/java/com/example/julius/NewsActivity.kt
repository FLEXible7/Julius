package com.example.julius

import android.app.DownloadManager
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.design.widget.BottomNavigationView
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import kotlinx.android.synthetic.main.activity_news.*
import okhttp3.*
import java.io.IOException


class NewsActivity : AppCompatActivity() {

    val bNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId)
        {
            R.id.deals ->{
                val dealsIntent = Intent(this, MainActivity::class.java)
                startActivity(dealsIntent)
                return@OnNavigationItemSelectedListener true
            }

            R.id.news ->{
                return@OnNavigationItemSelectedListener true
            }


            R.id.interesting_places ->{
                val placesIntent = Intent(this, PlacesActivity::class.java)
                startActivity(placesIntent)
                return@OnNavigationItemSelectedListener true
            }

            R.id.history ->{
                val historyIntent = Intent(this, HistoryActivity::class.java)
                startActivity(historyIntent)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        news_recycler.layoutManager = LinearLayoutManager(this)


        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation_news)
        bottomNavigation.getMenu().getItem(1).setChecked(true)
        bottomNavigation.setOnNavigationItemSelectedListener(bNavigationItemSelectedListener)

        loadData()

    }


    fun loadData(){

        val url = "https://kudago.com/public-api/v1.4/events-of-the-day/?expand=object"

        val request = Request.Builder()
            .url(url)
            .build()

        val httpClient = OkHttpClient()
        httpClient.newCall(request).enqueue(object : Callback {

            override fun onResponse(call: Call, response: Response) {

                val body = response.body()?.string()
                println(body)

                val gson = GsonBuilder().create()
                val newsList = gson.fromJson(body, NewsList::class.java)

                runOnUiThread {
                    news_recycler.adapter = NewsAdapter(newsList)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
            }
        })
    }
}


class NewsList(val results : List<Results>){}

class Results(val date : String, val location : String, @SerializedName("object") val details : Details, val title : String){}

class Details(val id : Int, val title : String, val favorites_count : Int, val comments_count : Int,
             val description : String, val item_url : String, val disable_comments : Boolean,
             val ctype : String, val place : Place, val daterange : DateRange, val first_image : FirstImage,
             val age_restriction : String){}

class Place(val id : Int){}

class DateRange(val start_date : String, val start_time : String, val start : Int, val end_date : String, val end_time : String,
                val end : Int, val is_continuous : Boolean, val is_endless : Boolean, val is_startless : Boolean,
                val schedules : List<String>, val use_place_schedule : Boolean){}

class FirstImage(val image : String, val thumbnails : Thumbnails, val source : Source){}

class Thumbnails(@SerializedName("640x384") val resolution640 : String,
                 @SerializedName("144x96")  val resolution144 : String){}

class Source(val name : String, val link : String)


