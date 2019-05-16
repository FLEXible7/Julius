package com.example.julius

import android.content.Context
import android.graphics.Color
import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.deal_row.view.*
import java.io.File

class RecyclerAdapter(val context : Context, val list : ArrayList<String>, val path : Array<File>): RecyclerView.Adapter<CustomViewHolder>() {

    var removedPosition : Int = 0
    var removedItem : String = ""

    override fun getItemCount(): Int {

        return list.size
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {

        val layoutInflater = LayoutInflater.from(p0.context)
        val cellForRow = layoutInflater.inflate(R.layout.deal_row, p0, false)

        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val item = list.get(position)
        val splittedText = item.split("\n")
        holder.view.DealId.text = splittedText.get(0)
        holder.view.DealsTag.text = splittedText.get(1)
        holder.view.DealsDate.text = splittedText.get(2)
    }

    fun removeItem(position : Int, viewHolder : RecyclerView.ViewHolder) {
        removedItem = list.get(position)
        removedPosition = position
        val file = path.get(position)

        list.removeAt(position)
        if (file.exists()){
            file.canonicalFile.delete()
            if (file.exists()){
                context.deleteFile(file.name)
            }
        }
        notifyItemRemoved(position)

        val snackbar = Snackbar.make(viewHolder.itemView, "$removedItem удалено", Snackbar.LENGTH_SHORT).setAction("Восстановить"){
            list.add(removedPosition, removedItem)

            val fileName = path.get(removedPosition).name
            val filePath = File(context.filesDir, "deal")
            val dealFile = File(filePath, fileName)
            dealFile.createNewFile()

            val item = list.get(removedPosition)
            val splittedText = item.split("\n")
            dealFile.writeText(splittedText.get(0) + "\n" +
                    splittedText.get(1) + "\n" +
                    splittedText.get(2))

            notifyItemInserted(removedPosition)
        }

        snackbar.setActionTextColor(Color.WHITE)
        val sbView = snackbar.view
        sbView.setBackgroundColor(Color.rgb(192, 192, 192))

        snackbar.show()
    }

}


class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view){

}

