package com.example.julius

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_add_new_deal.view.*
import kotlinx.android.synthetic.main.deal_row.view.*

class RecyclerAdapter: RecyclerView.Adapter<CustomViewHolder>() {

    val dealTitles = listOf<String>("Deal 1", "Deal 2", "Deal 3", "Deal 4", "Deal 5", "Deal 6", "Deal 7", "Deal 8")
    val tagTitles = listOf<String>("Tag 1", "Tag 2", "Tag 3", "Tag 4", "Tag 5", "Tag 6", "Tag 7", "Tag 8")

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
    }

}


class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view){

}
