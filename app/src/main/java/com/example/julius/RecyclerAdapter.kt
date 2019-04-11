package com.example.julius

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.deal_row.view.*

class RecyclerAdapter(): RecyclerView.Adapter<CustomViewHolder>() {

    //var clickListener : (((View) -> Unit))? = null

    val deals = ArrayList<String>()
    val tags = ArrayList<String>()
    val dates = ArrayList<String>()



    override fun getItemCount(): Int {

        return deals.size
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {

        val layoutInflater = LayoutInflater.from(p0.context)
        val cellForRow = layoutInflater.inflate(R.layout.deal_row, p0, false)

        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        //deals.add(items.get(0))
        //tags.add(items.get(1))
        //dates.add(items.get(2))


        holder.view.DealId.text = deals.get(position)
        holder.view.DealsTag.text = tags.get(position)
        holder.view.DealsDate.text = dates.get(position)
    }
}


class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view){

}
