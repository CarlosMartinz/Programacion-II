package com.example.appsem17

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView

class DoWhileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_do_while)

        val btn = findViewById<TextView>(R.id.btnRepetir)

        btn.setOnClickListener(View.OnClickListener {

            val txt = findViewById<TextView>(R.id.txtRepetir2)
            val txtNum = findViewById<TextView>(R.id.txtRepetir)
            var mListView = findViewById<ListView>(R.id.listt)
            var num:Int = txt.text.toString().toInt()
            var numRepe:Int = txtNum.text.toString().toInt()
            val arreglo = Array(num, { indice -> indice + 1})
            var i:Int = 0

            do {
                arreglo[i] = numRepe
                i++
            }while (i < num)

            val arrayAdapter = ArrayAdapter<Int>(this,android.R.layout.simple_list_item_1,arreglo)
            mListView.adapter = arrayAdapter

        })
    }
}