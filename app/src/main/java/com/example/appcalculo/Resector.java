package com.example.appcalculo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Resector extends AppCompatActivity {

    private Button btnRegresar;
    private TextView nombre, sueldo, afp, isss;

    public double ISSSD, AFPD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resector);

        nombre = (TextView) findViewById(R.id.nombre);
        afp = (TextView) findViewById(R.id.AFP);
        isss = (TextView) findViewById(R.id.ISSS);
        sueldo = (TextView) findViewById(R.id.Neto);

        String name = getIntent().getStringExtra("nombre");
        nombre.setText(name);

        /*Intent next = getIntent();
        Bitmap bitmap = (Bitmap) next.getParcelableExtra("img");
        ImageView image = (ImageView)findViewById(R.id.imgReset);
        image.setImageBitmap(bitmap);*/

        btnRegresar = (Button) findViewById(R.id.btnRegresar);
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next2 = new Intent( Resector.this, MainActivity.class);
                startActivity(next2);
                finish();
            }
        });

        calcularAFP();
        calcularISSS();
        calcularNeto();
    }

    public void calcularAFP(){
        double afp2 = 0.067;

        String money = getIntent().getStringExtra("sueldo");

        double a = Double.parseDouble(money);
        double resultadoAFP;

        resultadoAFP = afp2 * a;
        String b = String.valueOf(resultadoAFP);
        afp.setText(b);
        AFPD = Double.valueOf(b);
    }

    public void calcularISSS(){
        double isss2 = 0.03, Sueldo = 0.0;

        String money = getIntent().getStringExtra("sueldo");

        double a = Double.parseDouble(money);
        double resultadoISSS;

        resultadoISSS = isss2 * a;
        String b = String.valueOf(resultadoISSS);
        isss.setText(b);
        ISSSD = Double.valueOf(b);
    }

    public void calcularNeto(){
        double neto2 = 0.0;

        String money = getIntent().getStringExtra("sueldo");

        double a = Double.parseDouble(money);
        double resultadoNeto;

        neto2 = AFPD + ISSSD;
        resultadoNeto = a - neto2;
        String b = String.valueOf(resultadoNeto);
        sueldo.setText(b);
    }
}