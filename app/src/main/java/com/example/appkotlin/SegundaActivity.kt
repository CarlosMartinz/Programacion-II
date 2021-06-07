package com.example.appkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SegundaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_segunda)

        val txtName = findViewById<TextView>(R.id.txtName)
        val objectoIntent:Intent = intent
        var textName = objectoIntent.getStringExtra("nombre")
        txtName.text = textName

    }
}


