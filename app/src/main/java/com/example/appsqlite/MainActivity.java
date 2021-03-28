package com.example.appsqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.strictmode.IntentReceiverLeakedViolation;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.FocusFinder;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton btnSiguiente;
    DB miBD;
    ListView ltsAmigos;
    Cursor datosAmigosCursor = null;
    ArrayList<amigos> amigosArrayList=new ArrayList<amigos>();
    ArrayList<amigos> amigosArrayListCopy=new ArrayList<amigos>();
    amigos misAmigos;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextInputLayout textInputLayout = findViewById(R.id.input);
        TextInputEditText textInputEditText = findViewById(R.id.txtBuscar);
        imageView = findViewById(R.id.btnImg);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textInputLayout.setVisibility(View.VISIBLE);
            }
        });

        btnSiguiente = (FloatingActionButton) findViewById(R.id.btnSiguiente);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarAmigos("nuevo", new String[]{});
            }
        });

        buscarAmigos();
        obtenerDatosAmigos();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.bottom_app_bar, menu);

        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo)menuInfo;
        datosAmigosCursor.moveToPosition(adapterContextMenuInfo.position);
        menu.setHeaderTitle(datosAmigosCursor.getString(1));
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.btnAgregar:
                agregarAmigos("nuevo", new String[]{});
                break;
            case R.id.btnModificar:
                String[] datos = {
                        datosAmigosCursor.getString(0),//idAmigo
                        datosAmigosCursor.getString(1),//nombre
                        datosAmigosCursor.getString(2),//telefono
                        datosAmigosCursor.getString(3),//direccion
                        datosAmigosCursor.getString(4) //url photo
                };
                agregarAmigos("modificar",datos);
                break;

            case R.id.btnEliminar:
                String[] datos1 = {
                        datosAmigosCursor.getString(0)
                };
                return true;
            default:
        }
        return super.onContextItemSelected(item);
    }

    private void agregarAmigos(String accion, String[] datos) {
        try {
            Bundle parametrosAmigos = new Bundle();
            parametrosAmigos.putString("accion", accion);
            parametrosAmigos.putStringArray("datos", datos);

            Intent agregarAmigos = new Intent(getApplicationContext(), RegistrarActivity.class);
            agregarAmigos.putExtras(parametrosAmigos);
            startActivity(agregarAmigos);
        } catch (Exception e) {
            mostrarMsgToask(e.getMessage());
        }
    }
    private void buscarAmigos() {
        TextView tempVal = findViewById(R.id.txtBuscar);
        tempVal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    amigosArrayList.clear();
                    if( tempVal.getText().toString().trim().length()<1 ){//si no esta escribiendo, mostramos todos los registros
                        amigosArrayList.addAll(amigosArrayListCopy);
                    } else {//si esta buscando entonces filtramos los datos
                        for (amigos am : amigosArrayListCopy){
                            String nombre = am.getNombre();
                            if(nombre.toLowerCase().contains(tempVal.getText().toString().trim().toLowerCase())){
                                amigosArrayList.add(am);
                            }
                        }
                    }
                    adaptadorImagenes adaptadorImagenes = new adaptadorImagenes(getApplicationContext(), amigosArrayList);
                    ltsAmigos.setAdapter(adaptadorImagenes);
                }catch (Exception e){
                    mostrarMsgToask(e.getMessage());
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void obtenerDatosAmigos(){
        miBD = new DB(getApplicationContext(),"",null,1);
        datosAmigosCursor = miBD.administracion_amigos("consultar",null);
        if( datosAmigosCursor.moveToFirst() ){//si hay datos que mostrar
            mostrarDatosAmigos();
        } else {//sino que llame para agregar nuevos amigos...
            mostrarMsgToask("No hay datos de amigos que mostrar, por favor agregue nuevos amigos...");
            //agregarAmigos();
        }
    }
    private void mostrarDatosAmigos(){
        ltsAmigos = findViewById(R.id.ltsamigos);
        amigosArrayList.clear();
        do{
            misAmigos = new amigos(
                    datosAmigosCursor.getString(0),//idAmigo
                    datosAmigosCursor.getString(1),//nombre
                    datosAmigosCursor.getString(3),//direccion
                    datosAmigosCursor.getString(2),//telefono
                    datosAmigosCursor.getString(4) //urlPhoto
            );
            amigosArrayList.add(misAmigos);
            mostrarMsgToask(datosAmigosCursor.getString(4));
        }while(datosAmigosCursor.moveToNext());
        adaptadorImagenes adaptadorImagenes = new adaptadorImagenes(getApplicationContext(), amigosArrayList);
        ltsAmigos.setAdapter(adaptadorImagenes);

        registerForContextMenu(ltsAmigos);
    }
    private void mostrarMsgToask(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }

}

class amigos{
    String idAmigo;
    String nombre;
    String direccion;
    String telefono;
    String urlImg;

    public amigos(String idAmigo, String nombre, String telefono, String direccion, String urlImg) {
        this.idAmigo = idAmigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.urlImg = urlImg;
    }

    public String getIdAmigo() {
        return idAmigo;
    }

    public void setIdAmigo(String idAmigo) {
        this.idAmigo = idAmigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }
}