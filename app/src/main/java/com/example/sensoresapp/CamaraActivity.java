package com.example.sensoresapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.BitSet;

public class CamaraActivity extends AppCompatActivity {

        private static final int REQUEST_IMAGE_CAMARA = 101;
        private static final int REQUEST_PERMISSION_CAMERA = 100;

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);

        ImageButton btnretunrC = (ImageButton)findViewById(R.id.imgVoverC);
        btnretunrC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnc = new Intent(CamaraActivity.this, MainActivity.class);
                startActivity(returnc);
                finish();
            }
        });

        img =findViewById(R.id.imgCamara);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ){
                if (ActivityCompat.checkSelfPermission(CamaraActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                    goCamara();
                }else {
                    ActivityCompat.requestPermissions(CamaraActivity.this, new String []{Manifest.permission.CAMERA},REQUEST_PERMISSION_CAMERA);
                }
               }else{
                goCamara();
               }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION_CAMERA){
            if (permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                goCamara();
            }else {
                Toast.makeText(this,"Compra un celular nuevo",Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAMARA) {
            if (resultCode == Activity.RESULT_OK) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                img.setImageBitmap(bitmap);
                Log.i("TAG","Result=>" + bitmap );
            }
        }
    }

    private void goCamara(){
        Intent camara = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (camara.resolveActivity(getPackageManager()) != null){
            startActivityForResult(camara,REQUEST_IMAGE_CAMARA);

        }
    }
}