package com.example.appsem17

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class WhileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_while)

        val txtTabla1 = findViewById<TextView>(R.id.txtTabla1)
        val tabla11 = findViewById<TextView>(R.id.tabla11)
        val tabla22 = findViewById<TextView>(R.id.tabla22)
        val tabla33 = findViewById<TextView>(R.id.tabla33)
        val tabla44 = findViewById<TextView>(R.id.tabla44)
        val tabla55 = findViewById<TextView>(R.id.tabla55)
        val btn = findViewById<Button>(R.id.btnCalcularTablas1)

        btn.setOnClickListener(View.OnClickListener {
            val numm = txtTabla1.text.toString().toInt()
            var i = 0
            val arrayyyy = Array(5,{i -> i * 1})
            while (i <= 4)
            {
                val multip = i * numm
                arrayyyy[i] = multip
                i++
            }

            tabla11.setText(numm.toString() + " * 0 = " + arrayyyy[0] )
            tabla22.setText(numm.toString() + " * 1 = " + arrayyyy[1] )
            tabla33.setText(numm.toString() + " * 2 = " + arrayyyy[2] )
            tabla44.setText(numm.toString() + " * 3 = " + arrayyyy[3] )
            tabla55.setText(numm.toString() + " * 4 = " + arrayyyy[4] )

        })

    }
}