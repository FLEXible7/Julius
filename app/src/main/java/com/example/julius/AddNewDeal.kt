package com.example.julius

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build.VERSION_CODES.N
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_add_new_deal.*
import java.util.*
import android.widget.EditText



class AddNewDeal : AppCompatActivity() {

    val tags = arrayOf("Не выбрано", "Работа", "Учёба", "Повседневное", "Личное", "Интересное")

    lateinit var tag : Spinner
    lateinit var result : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_deal)

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

        val dealEdit = findViewById<EditText>(R.id.edit_deal_name)
        val tagEdit = findViewById<TextView>(R.id.tag_result)
        val dateEdit = findViewById<TextView>(R.id.pick_date)
        val pushDealButton = findViewById<Button>(R.id.push_deal_button)

        pushDealButton.setOnClickListener {

            val deal = dealEdit.text.toString()
            val tag = tagEdit.text.toString()
            val date = dateEdit.text.toString()

            val dealListIntent = Intent(this, MainActivity::class.java)
            dealListIntent.putExtra("Deal", deal)
            dealListIntent.putExtra("Tag", tag)
            dealListIntent.putExtra("Date", date)
            setResult(Activity.RESULT_OK, dealListIntent)
            finish()
        }
    }

}
