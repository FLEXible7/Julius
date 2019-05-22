package com.example.julius

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.news_row.view.*
import kotlinx.android.synthetic.main.places_row.view.*
import java.io.File
import java.util.*

class PlacesAdapter(val context: Context, val placesList : PlacesList): RecyclerView.Adapter<CustomViewHolderPlaces>() {


    override fun getItemCount(): Int {
        return placesList.results.count()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolderPlaces {

        val layoutInflater = LayoutInflater.from(p0.context)
        val cellForRow = layoutInflater.inflate(R.layout.places_row, p0, false)

        return CustomViewHolderPlaces(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolderPlaces, position: Int) {

        val item = placesList.results.get(position)

        holder.view.place_title.text = item.title
        holder.view.place_address.text = item.address
        holder.view.place_phone.text = item.phone


        val goToBrowserBtn = holder.view.go_to_browser_btn
        goToBrowserBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.item_url))
            context.startActivity(intent)
        }

        val addNewsButton = holder.view.add_place_button
        addNewsButton.setOnClickListener {
            val fileName = "${UUID.randomUUID()}.deal"
            val filePath = File(context.filesDir, "deal")
            val newsFile = File(filePath, fileName)
            newsFile.createNewFile()

            newsFile.writeText(item.title + "\n" +
                    "Места" + "\n" + "" +
                    "\n" + item.item_url)
            Toast.makeText(context, "Место добавлено в список заметок", Toast.LENGTH_SHORT).show()
        }

        val shareButton = holder.view.share_place_button
        shareButton.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "type/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, item.item_url)
            context.startActivity(Intent.createChooser(shareIntent, "Поделиться местом"))
        }
    }

}

class CustomViewHolderPlaces(val view: View): RecyclerView.ViewHolder(view){

}