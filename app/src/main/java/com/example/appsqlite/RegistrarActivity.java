package com.example.appsqlite;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class RegistrarActivity extends AppCompatActivity {

    FloatingActionButton btnAnterior;
    ImageView imgFotoAmigo;
    Intent tomarFotoIntent;
    String urlCompletaImg, idAmigo,accion="nuevo";
    Button btn;
    DB miBD;
    TextView tempVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        btnAnterior = (FloatingActionButton) findViewById(R.id.btnAnterior);
        btnAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              mostrarVistaPrincipal();
            }
        });

        imgFotoAmigo =findViewById(R.id.imgFoto);
        imgFotoAmigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tomarFotoAmigo();
            }
        });

        miBD = new DB(getApplicationContext(),"",null,1);

        btn = findViewById(R.id.btnGuardar);
        btn.setOnClickListener(v->{
            tempVal = findViewById(R.id.txtNombre);
            String nombre = tempVal.getText().toString();

            tempVal = findViewById(R.id.txtDireccion);
            String direccion = tempVal.getText().toString();

            tempVal = findViewById(R.id.txtTelefono);
            String tel = tempVal.getText().toString();

            String[] datos = {idAmigo,nombre,direccion,tel,urlCompletaImg};
            miBD.administracion_amigos(accion,datos);
            mostrarMsgToast("Registro guardado con exito.");

            mostrarVistaPrincipal();
        });
        mostrarDatosAmigos();
    }

    private void mostrarDatosAmigos() {
        try{
            Bundle recibirParametros = getIntent().getExtras();
            accion = recibirParametros.getString("accion");
            if(accion.equals("modificar")){
                String[] datos = recibirParametros.getStringArray("datos");

                idAmigo = datos[0];

                tempVal = findViewById(R.id.txtNombre);
                tempVal.setText(datos[1]);

                tempVal = findViewById(R.id.txtTelefono);
                tempVal.setText(datos[2]);

                tempVal = findViewById(R.id.txtDireccion);
                tempVal.setText(datos[3]);

                urlCompletaImg = datos[4];
                Bitmap bitmap = BitmapFactory.decodeFile((urlCompletaImg));
                imgFotoAmigo.setImageBitmap(bitmap);
            }
        }catch (Exception e){
            mostrarMsgToast(e.getMessage());
        }
    }
    private void mostrarVistaPrincipal(){
        Intent iprincipal = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(iprincipal);
    }
    private void tomarFotoAmigo(){
        tomarFotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if( tomarFotoIntent.resolveActivity(getPackageManager())!=null ){
            File photoAmigo = null;
            try{
                photoAmigo = crearImagenAmigo();
            }catch (Exception e){
                mostrarMsgToast(e.getMessage());
            }
            if( photoAmigo!=null ){
                try{
                    Uri uriPhotoAmigo = FileProvider.getUriForFile(RegistrarActivity.this, "com.example.appsqlite.fileprovider",photoAmigo);
                    tomarFotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriPhotoAmigo);
                    startActivityForResult(tomarFotoIntent,1);
                }catch (Exception e){
                    mostrarMsgToast(e.getMessage());
                }
            } else {
                mostrarMsgToast("No fue posible tomar la foto");
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try{
            if( requestCode==1 && resultCode==RESULT_OK ){
                Bitmap imagenBitmap = BitmapFactory.decodeFile(urlCompletaImg);
                imgFotoAmigo.setImageBitmap(imagenBitmap);
            }
        }catch (Exception e){
            mostrarMsgToast(e.getMessage());
        }
    }
    private File crearImagenAmigo() throws Exception {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String nombreImagen = "imagen_"+ timeStamp +"_";
        File dirAlmacenamiento = getExternalFilesDir(Environment.DIRECTORY_DCIM);
        if( dirAlmacenamiento.exists()==false ){
            dirAlmacenamiento.mkdirs();
        }
        File image = File.createTempFile(nombreImagen,".jpg",dirAlmacenamiento);
        urlCompletaImg = image.getAbsolutePath();
        return image;
    }
    private void mostrarMsgToast(String msg){
        Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG).show();
    }
}