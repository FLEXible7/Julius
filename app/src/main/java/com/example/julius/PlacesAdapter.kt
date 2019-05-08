package com.example.julius

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.places_row.view.*

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

        holder.view.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.item_url))
            context.startActivity(intent)
        }
    }

}

class CustomViewHolderPlaces(val view: View): RecyclerView.ViewHolder(view){

}