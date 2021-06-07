package com.example.parcial_ii;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.Time;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton btn;
    DB miBD;
    Context miContext;
    ListView ltsAgenda;
    Cursor datosAgendaCursor = null;
    ArrayList<agenda> agendaArrayList=new ArrayList<agenda>();
    ArrayList<agenda> agendaArrayListCopy=new ArrayList<agenda>();
    agenda misTareas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btnAgregarTarea);
        btn.setOnClickListener(v->{
            agregarAgenda("nuevo", new String[]{});
        });
        obtenerDatosAgenda();
        buscarAgenda();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_agenda, menu);

        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo)menuInfo;
        datosAgendaCursor.moveToPosition(adapterContextMenuInfo.position);
        menu.setHeaderTitle(datosAgendaCursor.getString(1));
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        try {
            switch (item.getItemId()) {
                case R.id.mnxAgregar:
                    agregarAgenda("nuevo", new String[]{});
                    break;
                case R.id.mnxModificar:
                    String[] datos = {
                            datosAgendaCursor.getString(0),//idAmigo
                            datosAgendaCursor.getString(1),//nombre
                            datosAgendaCursor.getString(2),//telefono
                            datosAgendaCursor.getString(3),//direccion
                            datosAgendaCursor.getString(4) //email
                    };
                    agregarAgenda("modificar", datos);
                    break;
                case R.id.mnxEliminar:
                    eliminarAgenda();
                    break;
            }
        }catch (Exception ex){
            mostrarMsgToask(ex.getMessage());
        }
        return super.onContextItemSelected(item);
    }
    private void eliminarAgenda(){
        try {
            AlertDialog.Builder confirmacion = new AlertDialog.Builder(MainActivity.this);
            confirmacion.setTitle("Esta seguro de eliminar el registro?");
            confirmacion.setMessage(datosAgendaCursor.getString(1));
            confirmacion.setPositiveButton("Si", (dialog, which) -> {
                miBD = new DB(getApplicationContext(), "", null, 1);
                datosAgendaCursor = miBD.administracion_agenda("eliminar", new String[]{datosAgendaCursor.getString(0)});
                obtenerDatosAgenda();
                mostrarMsgToask("Registro Eliminado con exito...");
                dialog.dismiss();
            });
            confirmacion.setNegativeButton("No", (dialog, which) -> {
                mostrarMsgToask("Eliminacion cancelada por el usuario...");
                dialog.dismiss();
            });
            confirmacion.create().show();
        }catch (Exception ex){
            mostrarMsgToask(ex.getMessage());
        }
    }

    private void buscarAgenda() {
        TextView tempVal = findViewById(R.id.txtBuscarTarea);
        tempVal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    agendaArrayList.clear();
                    if( tempVal.getText().toString().trim().length()<1 ){
                        agendaArrayList.addAll(agendaArrayListCopy);
                    } else {
                        for (agenda am : agendaArrayListCopy){
                            String dui = am.getDui();
                            String nombre = am.getNombre();
                            String dia = am.getDay();
                            String fecha = am.getMes();

                            String buscando = tempVal.getText().toString().trim().toLowerCase();

                            if(dui.toLowerCase().trim().contains(buscando) ||
                                    nombre.trim().contains(buscando) ||
                                    dia.trim().toLowerCase().contains(buscando) ||
                                    fecha.trim().toLowerCase().contains(buscando)
                            ){
                                agendaArrayList.add(am);
                            }
                        }
                    }
                    MostrarDatos adaptador = new MostrarDatos(getApplicationContext(), agendaArrayList);
                    ltsAgenda.setAdapter(adaptador);
                }catch (Exception e){
                    mostrarMsgToask(e.getMessage());
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private void agregarAgenda(String accion, String[] datos){
        try {
            Bundle parametrosAgenda = new Bundle();
            parametrosAgenda.putString("accion", accion);
            parametrosAgenda.putStringArray("datos", datos);

            Intent agregarAgenda = new Intent(getApplicationContext(), AgregarActivity.class);
            agregarAgenda.putExtras(parametrosAgenda);
            startActivity(agregarAgenda);
        }catch (Exception e){
            mostrarMsgToask(e.getMessage());
        }
    }

    private void obtenerDatosAgenda(){
        miBD = new DB(getApplicationContext(),"",null,1);
        datosAgendaCursor = miBD.administracion_agenda("consultar",null);
        if( datosAgendaCursor.moveToFirst() ){
            mostrarDatosAgenda();
        } else {
            mostrarMsgToask("No hay tareas que mostrar, por favor agregue nuevas tareas...");
            agregarAgenda("nuevo", new String[]{});
        }
    }

    private void mostrarDatosAgenda(){
        try {
            ltsAgenda = findViewById(R.id.ltsagenda);
            agendaArrayList.clear();
            agendaArrayListCopy.clear();
            do {
                misTareas = new agenda(
                        datosAgendaCursor.getString(0),
                        datosAgendaCursor.getString(1),
                        datosAgendaCursor.getString(2),
                        datosAgendaCursor.getString(3),
                        datosAgendaCursor.getString(4)
                );
                agendaArrayList.add(misTareas);
            } while (datosAgendaCursor.moveToNext());
            MostrarDatos adaptador = new MostrarDatos(getApplicationContext(), agendaArrayList);
            ltsAgenda.setAdapter(adaptador);

            registerForContextMenu(ltsAgenda);

            agendaArrayListCopy.addAll(agendaArrayList);
        }catch (Exception e){
            Toast.makeText( miContext,"error: "+ e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }
    private void mostrarMsgToask(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }

}

class agenda{
    String idAgenda;
    String dui;
    String nombre;
    String day;
    String mes;

    public agenda(String idAgenda, String dui, String nombre, String day, String mes) {
        this.idAgenda = idAgenda;
        this.dui = dui;
        this.nombre = nombre;
        this.day = day;
        this.mes = mes;
    }

    public String getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(String idAgenda) {
        this.idAgenda = idAgenda;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }
}