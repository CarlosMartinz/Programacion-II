package com.example.appcalculo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnEnviar;
    public EditText txtNombre, txtSueldo;
    public ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtSueldo = (EditText) findViewById(R.id.txtSueldo);
        img = (ImageView) findViewById(R.id.img);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarImg();
            }
        });

        btnEnviar = (Button) findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              pasarDatos();
            }
        });
    }

    public void pasarDatos(){
        String nombre = txtNombre.getText().toString();
        String sueldo = txtSueldo.getText().toString();

        if(nombre.isEmpty() || sueldo.isEmpty()){
            Toast.makeText(MainActivity.this,"Complete los 2 campos", Toast.LENGTH_SHORT).show();
        }
        else{
            Bundle datos = new Bundle();
            //Bitmap bitmap = ((BitmapDrawable)img.getDrawable()).getBitmap();

            datos.putString("nombre", nombre);
            datos.putString("sueldo", sueldo);

            Intent next = new Intent( MainActivity.this, Resector.class);
            //next.putExtra("img", bitmap);
            next.putExtras(datos);
            startActivity(next);
            finish();
        }
    }

    public void  cargarImg(){
        Intent next = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        next.setType("image/");
        startActivityForResult(next.createChooser(next, "Seleccione la aplicacion"),10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Uri image = data.getData();
            img.setImageURI(image);
        }
    }
}