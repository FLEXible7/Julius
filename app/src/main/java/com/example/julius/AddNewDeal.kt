package com.example.julius

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_add_new_deal.*
import java.util.*
import android.widget.EditText
import java.io.File


class AddNewDeal : AppCompatActivity() {

    val tags = arrayOf("Работа", "Учёба", "Повседневное", "Личное", "Интересное")

    lateinit var tag : Spinner
    lateinit var result : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_deal)

        //Storage
        val filePath = File(filesDir, "deal")
        if (!filePath.isDirectory || !filePath.exists()){
            filePath.mkdir()
        }


        //Tag
        tag = findViewById(R.id.spinner) as Spinner
        result = findViewById(R.id.tag_result) as TextView

        tag.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, tags)

        tag.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                result.text = "Метка..."
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, Id: Long) {
                result.text = tags.get(position)
            }
        }

        //Date
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val moth = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val months = arrayOf("января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа", "сентября", "октября", "ноября", "декабря")

        pick_date.setOnClickListener{
            val dateDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{view, mYear :Int, mMoth :Int, mDay :Int ->
                pick_date.setText("" + mDay + " " + months[mMoth] + " " + mYear)
            }, year, moth, day)

            dateDialog.show()
        }

        val dealEdit = findViewById<EditText>(R.id.edit_request)
        val tagEdit = findViewById<TextView>(R.id.tag_result)
        val dateEdit = findViewById<TextView>(R.id.pick_date)
        val pushDealButton = findViewById<Button>(R.id.push_deal_button)

        //SendData
        pushDealButton.setOnClickListener {

            val deal = dealEdit.text.toString()
            val tag = tagEdit.text.toString()
            val date = dateEdit.text.toString()

            if (deal == "") {
                Toast.makeText(this, "Заполните поле \"Заголовок\"", Toast.LENGTH_SHORT).show()
            }

            else if (date == "") {
                Toast.makeText(this, "Заполните поле \"Срок выполнения\"", Toast.LENGTH_SHORT).show()
            }

            else {

                val fileName = "${UUID.randomUUID()}.deal"

                val dealFile = File(filePath, fileName)
                dealFile.createNewFile()
                dealFile.writeText(deal + "\n" + tag + "\n" + date)
                val dealListIntent = Intent(this, MainActivity::class.java)
                startActivity(dealListIntent)
            }
        }
    }

}
