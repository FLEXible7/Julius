package com.example.julius

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

    val manager = supportFragmentManager

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

    val adapter = RecyclerAdapter()
    //val dealItems = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addToListIntent = intent
        val deal = addToListIntent.getStringExtra("Deal")
        val tag = addToListIntent.getStringExtra("Tag")
        val date = addToListIntent.getStringExtra("Date")

        val example = findViewById<TextView>(R.id.exampleTW)
        example.text = "" + deal + "\n" + tag + "\n" + date

        val list = findViewById<RecyclerView>(R.id.recycler)
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter
        //recycler.layoutManager = LinearLayoutManager(this)
        //recycler.adapter = RecyclerAdapter()

        val dealId = findViewById<TextView>(R.id.DealId)
        val dealTag = findViewById<TextView>(R.id.DealsTag)
        val dealDate = findViewById<TextView>(R.id.DealsDate)

        //dealId.text = deal
        //dealTag.text = tag
        //dealDate.text = date

        adapter.notifyDataSetChanged()

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener(bNavigationItemSelectedListener)

    }

        fun clickAddDeal(view: View){
        val newDealIntent = Intent(this, AddNewDeal::class.java)
        startActivity(newDealIntent)
    }

}


//dealItems.add(deal)
//dealItems.add(tag)
//dealItems.add(date)

//recycler.layoutManager = LinearLayoutManager(this)
//recycler.adapter = RecyclerAdapter()