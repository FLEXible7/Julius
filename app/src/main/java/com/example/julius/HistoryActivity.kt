package com.example.julius

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView

class HistoryActivity : AppCompatActivity() {

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
                val placesIntent = Intent(this, PlacesActivity::class.java)
                startActivity(placesIntent)
                return@OnNavigationItemSelectedListener true
            }

            R.id.history ->{

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation_history)
        bottomNavigation.getMenu().getItem(3).setChecked(true)
        bottomNavigation.setOnNavigationItemSelectedListener(bNavigationItemSelectedListener)
    }
}
