package com.example.sensoresapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCamara = (Button)findViewById(R.id.btnCamara);

        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(MainActivity.this, CamaraActivity.class);
                startActivity(next);
                finish();
            }
        });

        Button btnProximidad = (Button)findViewById(R.id.btnProximidad);
        btnProximidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(MainActivity.this, ProximidadActivity.class);
                startActivity(next);
            }
        });
    }
}