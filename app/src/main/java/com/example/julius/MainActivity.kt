package com.example.julius

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File


class MainActivity : AppCompatActivity() {

    var deals = ArrayList<String>()
    var tags = ArrayList<String>()
    var dates = ArrayList<String>()
    //val adapter = RecyclerAdapter(deals, tags, dates)


    val bNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId)
        {
            R.id.news ->{
                val newsIntent = Intent(this, NewsActivity::class.java)
                startActivity(newsIntent)
                return@OnNavigationItemSelectedListener true
            }

            R.id.deals ->{
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
        setContentView(R.layout.activity_main)

        recycler.layoutManager = LinearLayoutManager(this)

        val folder = File(filesDir, "deal")
        //folder.delete()
        val list = folder.listFiles()
        for (i in list){
            println(i.name)
            val some = i.readText()
            println(i.readText())
        }

        recycler.adapter = RecyclerAdapter(list)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.getMenu().getItem(0).setChecked(true)
        bottomNavigation.setOnNavigationItemSelectedListener(bNavigationItemSelectedListener)
    }

    fun clickAddDeal(view: View){
        val newDealIntent = Intent(this, AddNewDeal::class.java)
        startActivity(newDealIntent)
    }

}