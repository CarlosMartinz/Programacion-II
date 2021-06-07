package com.example.appkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnEnviar = findViewById<Button>(R.id.btnEnviar)

        btnEnviar.setOnClickListener{

            val nomUno = findViewById<TextView>(R.id.txtUno)
            val nomDos = findViewById<TextView>(R.id.txtDos)

            var uno:String = nomUno.text.toString()
            var dos:String = nomDos.text.toString()

            var nombreUnido = uno + " " +  dos

            val intent:Intent = Intent(this, SegundaActivity::class.java)
            intent.putExtra("nombre", nombreUnido)
            startActivity(intent)

        }

    }
}

