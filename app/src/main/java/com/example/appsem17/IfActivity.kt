package com.example.appsem17

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EdgeEffect
import android.widget.EditText
import android.widget.TextView
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat

class IfActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_if)

        val txtNota1 = findViewById<TextView>(R.id.txtNota1)
        val txtNota2 = findViewById<TextView>(R.id.txtNota2)
        val txtNota3 = findViewById<TextView>(R.id.txtNota3)
        val result = findViewById<TextView>(R.id.Resultado)
        val estado = findViewById<TextView>(R.id.estado)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)

        btnCalcular.setOnClickListener(View.OnClickListener {

            var suma = (txtNota1.text.toString().toDouble() + txtNota2.text.toString().toDouble() + txtNota3.text.toString().toDouble()) / 3

            val decimal = BigDecimal(suma).setScale(2, RoundingMode.HALF_EVEN)

            if (suma >= 6){
                result.setText( decimal.toString())
                estado.setText( "Aprobado")
            }else{
                result.setText( decimal.toString())
                estado.setText( "Reprobado")
            }

        })

    }
}