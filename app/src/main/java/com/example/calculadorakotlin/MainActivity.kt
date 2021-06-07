package com.example.calculadorakotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.cardview.widget.CardView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txt1 = findViewById<EditText>(R.id.editTextNumberDecimal)
        val txt2 = findViewById<EditText>(R.id.editTextNumberDecimal2)

        val btnSuma = findViewById<Button>(R.id.button)
        val btnResta = findViewById<Button>(R.id.button2)
        val btnMultiplicacion = findViewById<Button>(R.id.button3)
        val btnDivision = findViewById<Button>(R.id.button4)

        btnSuma.setOnClickListener {
            if(txt1.text.toString().isEmpty()){
                txt1.error = "Ingrese un numero"
                txt1.requestFocus()
            }else if (txt2.text.toString().isEmpty()){
                txt2.error = "Ingrese un numero"
                txt2.requestFocus()
            }
            else{
                val resul = findViewById<TextView>(R.id.textView)
                val num1 = txt1.text.toString().toDouble()
                val num2 = txt2.text.toString().toDouble()
                val suma = num1 + num2
                resul.setText(suma.toString())
            }
        }
        btnResta.setOnClickListener {
            if(txt1.text.toString().isEmpty()){
                txt1.error = "Ingrese un numero"
                txt1.requestFocus()
            }else if (txt2.text.toString().isEmpty()){
                txt2.error = "Ingrese un numero"
                txt2.requestFocus()
            }
            else{
                val resul = findViewById<TextView>(R.id.textView)
                val num1 = txt1.text.toString().toDouble()
                val num2 = txt2.text.toString().toDouble()
                val suma = num1 - num2
                resul.setText(suma.toString())
            }
        }
        btnMultiplicacion.setOnClickListener {
            if(txt1.text.toString().isEmpty()){
                txt1.error = "Ingrese un numero"
                txt1.requestFocus()
            }else if (txt2.text.toString().isEmpty()){
                txt2.error = "Ingrese un numero"
                txt2.requestFocus()
            }
            else{
                val resul = findViewById<TextView>(R.id.textView)
                val num1 = txt1.text.toString().toDouble()
                val num2 = txt2.text.toString().toDouble()
                val suma = num1 * num2
                resul.setText(suma.toString())
            }
        }
        btnDivision.setOnClickListener {
            if(txt1.text.toString().isEmpty()){
                txt1.error = "Ingrese un numero"
                txt1.requestFocus()
            }else if (txt2.text.toString().isEmpty()){
                txt2.error = "Ingrese un numero"
                txt2.requestFocus()
            }
            else{
                val resul = findViewById<TextView>(R.id.textView)
                val num1 = txt1.text.toString().toDouble()
                val num2 = txt2.text.toString().toDouble()
                val suma = num1 / num2
                resul.setText(suma.toString())
            }
        }

    }
}