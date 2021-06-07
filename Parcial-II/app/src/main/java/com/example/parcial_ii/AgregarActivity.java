package com.example.parcial_ii;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AgregarActivity extends AppCompatActivity {

    FloatingActionButton btnAtras;
    String idAgenda,accion="nuevo";
    Button btn;
    DB miBD;
    TextView tempVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        miBD = new DB(getApplicationContext(),"",null,1);
        btnAtras = findViewById(R.id.btnAtras);
        btnAtras.setOnClickListener(v->{
            mostrarVistaPrincipal();
        });

        btn = findViewById(R.id.btnGuardar);
        btn.setOnClickListener(v->{
            tempVal = findViewById(R.id.txtDui);
            String titulo = tempVal.getText().toString();

            tempVal = findViewById(R.id.txtNombre);
            String descrip = tempVal.getText().toString();

            tempVal = findViewById(R.id.txtDayy);
            String dia = tempVal.getText().toString();

            tempVal = findViewById(R.id.txtMess);
            String moth = tempVal.getText().toString();

            String[] datos = {idAgenda,titulo,descrip,dia,moth};
            miBD.administracion_agenda(accion,datos);
            mostrarMsgToast("Registro guardado con exito.");

            mostrarVistaPrincipal();
        });
         mostrarDatosAgenda();

    }

    private void mostrarDatosAgenda() {
        try{
            Bundle recibirParametros = getIntent().getExtras();
            accion = recibirParametros.getString("accion");
            if(accion.equals("modificar")){
                String[] datos = recibirParametros.getStringArray("datos");

                idAgenda = datos[0];

                tempVal = findViewById(R.id.txtDui);
                tempVal.setText(datos[1]);

                tempVal = findViewById(R.id.txtNombre);
                tempVal.setText(datos[2]);

                tempVal = findViewById(R.id.txtDayy);
                tempVal.setText(datos[3]);

                tempVal = findViewById(R.id.txtMess);
                tempVal.setText(datos[4]);
            }
        }catch (Exception e){
            mostrarMsgToast(e.getMessage());
        }
    }
    private void mostrarVistaPrincipal(){
        Intent iprincipal = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(iprincipal);
    }

    private void mostrarMsgToast(String msg){
        Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG).show();
    }

}