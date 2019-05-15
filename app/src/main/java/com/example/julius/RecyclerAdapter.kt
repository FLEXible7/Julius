package com.example.julius

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.deal_row.view.*
import java.io.File

class RecyclerAdapter(val path : Array<File>): RecyclerView.Adapter<CustomViewHolder>() {

    override fun getItemCount(): Int {

        return path.size
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {

        val layoutInflater = LayoutInflater.from(p0.context)
        val cellForRow = layoutInflater.inflate(R.layout.deal_row, p0, false)

        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val pathText = path[position].readText()
        val splittedText = pathText.split("\n")
        holder.view.DealId.text = splittedText.get(0)
        holder.view.DealsTag.text = splittedText.get(1)
        holder.view.DealsDate.text = splittedText.get(2)

        val finish_check = holder.view.finish_deal_check
        finish_check.setOnClickListener {

        }
    }

}


class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view){

}
