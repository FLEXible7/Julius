package com.example.julius

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.Window
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text


class MainActivity : AppCompatActivity() {

    val bNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId)
        {
            R.id.deals ->{
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
                val historyIntent = Intent(this, HistoryActivity::class.java)
                startActivity(historyIntent)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    var deals = ArrayList<String>()
    var tags = ArrayList<String>()
    var dates = ArrayList<String>()
    val adapter = RecyclerAdapter(deals, tags, dates)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.layoutManager = LinearLayoutManager(this)

        recycler.adapter = RecyclerAdapter(deals, tags, dates)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.getMenu().getItem(0).setChecked(true)
        bottomNavigation.setOnNavigationItemSelectedListener(bNavigationItemSelectedListener)



    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //val d = data!!.getStringExtra("Deal")
        //val addToListIntent = intent
        val deal = data!!.getStringExtra("Deal")
        val tag = data!!.getStringExtra("Tag")
        val date = data!!.getStringExtra("Date")

        deals.add(deal)
        tags.add(tag)
        dates.add(date)

        adapter.notifyDataSetChanged()
        recycler.adapter = RecyclerAdapter(deals, tags, dates)


    }

    fun clickAddDeal(view: View){
        val newDealIntent = Intent(this, AddNewDeal::class.java)
        startActivityForResult(newDealIntent,1)
    }



}


//dealItems.add(deal)
//dealItems.add(tag)
//dealItems.add(date)

//recycler.layoutManager = LinearLayoutManager(this)
//recycler.adapter = RecyclerAdapter()