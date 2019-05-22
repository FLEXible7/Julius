package com.example.julius


import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.BottomNavigationView
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_places.*
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import okhttp3.*
import java.io.IOException

class PlacesActivity : AppCompatActivity() {

    val bNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId)
        {
            R.id.news ->{
                val newsIntent = Intent(this, NewsActivity::class.java)
                startActivity(newsIntent)
                return@OnNavigationItemSelectedListener true
            }

            R.id.deals ->{
                val dealsIntent = Intent(this, MainActivity::class.java)
                startActivity(dealsIntent)
                return@OnNavigationItemSelectedListener true
            }

            R.id.interesting_places ->{

                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_places)

        val itemDecoration = DividerItemDecoration(applicationContext, 1)
        places_recycler.addItemDecoration(itemDecoration)

        places_recycler.layoutManager = LinearLayoutManager(this)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation_places)
        bottomNavigation.getMenu().getItem(2).setChecked(true)
        bottomNavigation.setOnNavigationItemSelectedListener(bNavigationItemSelectedListener)

        val findPlaceBtn = findViewById<Button>(R.id.find_place_btn)
        findPlaceBtn.setOnClickListener{

            if (findViewById<EditText>(R.id.edit_request).text.toString() != ""){
                loadPlaces()
            }
            else{
                Toast.makeText(this, "Введите запрос", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun loadPlaces(){

        val uRequest = findViewById<EditText>(R.id.edit_request).text

        val url = "https://kudago.com/public-api/v1.3/search/?q=$uRequest&ctype=place&location=spb&page_size=100"

        val request = Request.Builder()
            .url(url)
            .build()

        val httpClient = OkHttpClient()
        httpClient.newCall(request).enqueue(object : Callback {

            override fun onResponse(call: Call, response: Response) {

                val body = response.body()?.string()

                val gson = GsonBuilder().create()
                val placesList = gson.fromJson(body, PlacesList::class.java)

                runOnUiThread {
                    places_recycler.adapter = PlacesAdapter(this@PlacesActivity, placesList)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
            }
        })
    }
}


class PlacesList(val results : List<ResultsP>)

class ResultsP(val id : Int, val title : String, val favorites_count : Int, val comments_count : Int,
               val description : String, val item_url : String, val disable_comments : Boolean,
               val ctype : String, val address : String, val location : String, val coords : Coords,
               val phone : String, val is_closed : Boolean, val is_stub : Boolean, val age_restriction : Int)

class Coords(val lat : Float, val lon : Float)


