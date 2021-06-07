package com.example.appsem17

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val btnFor = findViewById<CardView>(R.id.forCiclo)
        val btnIf = findViewById<CardView>(R.id.ifCondicion)
        val btnDoWhile = findViewById<CardView>(R.id.doWhileCiclo)
        val btnWhile = findViewById<CardView>(R.id.whileCiclo)
        //val btnSwitch = findViewById<CardView>(R.id.switchCase)

        btnIf.setOnClickListener {
            val intent = Intent(this, IfActivity::class.java)
            startActivity(intent)
        }

        btnFor.setOnClickListener {
            val intent = Intent(this, ForActivity::class.java)
            startActivity(intent)
        }

        btnDoWhile.setOnClickListener {
        val intent = Intent(this, DoWhileActivity::class.java)
            startActivity(intent)
        }

        btnWhile.setOnClickListener {
        val intent = Intent(this, WhileActivity::class.java)
            startActivity(intent)
        }

        /*btnSwitch.setOnClickListener {
        val intent = Intent(this, SwitchActivity::class.java)
            startActivity(intent)
        }*/

    }
}