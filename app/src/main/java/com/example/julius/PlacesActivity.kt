package com.example.julius

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView

class PlacesActivity : AppCompatActivity() {

    val bNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId)
        {
            R.id.deals ->{
                val dealsIntent = Intent(this, MainActivity::class.java)
                startActivity(dealsIntent)
                return@OnNavigationItemSelectedListener true
            }

            R.id.news ->{
                val newsIntent = Intent(this, NewsActivity::class.java)
                startActivity(newsIntent)
                return@OnNavigationItemSelectedListener true
            }


            R.id.interesting_places ->{

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
        setContentView(R.layout.activity_places)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation_places)
        bottomNavigation.getMenu().getItem(2).setChecked(true)
        bottomNavigation.setOnNavigationItemSelectedListener(bNavigationItemSelectedListener)
    }
}
