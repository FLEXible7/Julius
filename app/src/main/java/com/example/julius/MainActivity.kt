package com.example.julius

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.Window
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.TextView
import org.w3c.dom.Text


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = RecyclerAdapter(addRow())





    }

    fun addRow(): ArrayList<DealInfo>{

        val addToListIntent = intent
        val deal = addToListIntent.getStringExtra("Deal")
        val tag = addToListIntent.getStringExtra("Tag")
        val date = addToListIntent.getStringExtra("Date")

        val newRow = ArrayList<DealInfo>()

        for (i in 0..9){
            var row: DealInfo = DealInfo("deal", "tag", "date")
            newRow.add(row)
        }
        //val row = DealInfo(deal, tag, date)
        //newRow.add(row)

        return newRow
    }

    fun clickAddDeal(view: View){
        val newDealIntent = Intent(this, AddNewDeal::class.java)
        startActivity(newDealIntent)
    }

}
