package com.example.appsem17

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class ForActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_for)

        val txtTabla = findViewById<TextView>(R.id.txtTabla)
        val tabla1 = findViewById<TextView>(R.id.tabla1)
        val tabla2 = findViewById<TextView>(R.id.tabla2)
        val tabla3 = findViewById<TextView>(R.id.tabla3)
        val tabla4 = findViewById<TextView>(R.id.tabla4)
        val tabla5 = findViewById<TextView>(R.id.tabla5)
        val btn = findViewById<Button>(R.id.btnCalcularTablas)

        btn.setOnClickListener(View.OnClickListener {
            val num = txtTabla.text.toString().toInt()

            val arrayname = Array(5,{i -> i * 1})
            for (i in 0..4)
            {
                val multi = i * num
                arrayname[i] = multi
            }

            tabla1.setText(num.toString() + " * 0 = " + arrayname[0] )
            tabla2.setText(num.toString() + " * 1 = " + arrayname[1] )
            tabla3.setText(num.toString() + " * 2 = " + arrayname[2] )
            tabla4.setText(num.toString() + " * 3 = " + arrayname[3] )
            tabla5.setText(num.toString() + " * 4 = " + arrayname[4] )

        })

    }
}