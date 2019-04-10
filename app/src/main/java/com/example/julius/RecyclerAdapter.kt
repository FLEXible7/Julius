package com.example.julius

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.deal_row.view.*

class RecyclerAdapter: RecyclerView.Adapter<CustomViewHolder>() {

    private val dealTitles = arrayListOf("Deal 1","Deal 2","Deal 3","Deal 4","Deal 5","Deal 6","Deal 7","Deal 8", "Deal 9", "Deal 10", "Deal 11")
    private val tagTitles = arrayListOf("Tag 1", "Tag 2", "Tag 3", "Tag 4", "Tag 5", "Tag 6", "Tag 7", "Tag 8", "Tag 9", "Tag 10", "Tag 11")
    private val dateTitles = arrayListOf("Date 1", "Date 2", "Date 3", "Date 4", "Date 5", "Date 6", "Date 7", "Date 8", "Date 9", "Date 10", "Date 11")



    //var clickListener : (((View) -> Unit))? = null

    override fun getItemCount(): Int {
        return dealTitles.size
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {

        val layoutInflater = LayoutInflater.from(p0.context)
        val cellForRow = layoutInflater.inflate(R.layout.deal_row, p0, false)


        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(p0: CustomViewHolder, p1: Int) {
        val dealTitle = dealTitles.get(p1)
        p0.view.DealId.text = dealTitle

        val tagTitle = tagTitles.get(p1)
        p0.view.DealsTag.text = tagTitle

        val dateTitle = dateTitles.get(p1)
        p0.view.DealsDate.text = dateTitle
    }

    /*fun removeItem(index: Int){
        val itemPosition = dealTitles.size - 1
        dealTitles.removeAt(index)
        notifyItemRemoved(itemPosition)
    }*/
}


class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view){

}
