package com.example.parcial_ii;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MostrarDatos extends BaseAdapter {
    Context context;
    ArrayList<agenda> datosAgendaArrayList;
    LayoutInflater layoutInflater;
    agenda misTareas;

    public MostrarDatos(Context context, ArrayList<agenda> datosAgendaArrayList) {
        this.context = context;
        this.datosAgendaArrayList = datosAgendaArrayList;
    }

    @Override
    public int getCount() {
        return datosAgendaArrayList.size();
    }
    @Override
    public Object getItem(int position) {
        return datosAgendaArrayList.get(position);
    }
    @Override
    public long getItemId(int position) {
        return Long.parseLong( datosAgendaArrayList.get(position).getIdAgenda() );
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.list_mostrar, parent, false);
        TextView tempVal = itemView.findViewById(R.id.lbldui);
        TextView tempVal1 = itemView.findViewById(R.id.lblnombre);
        TextView tempVal2 = itemView.findViewById(R.id.lblfecha);

        try{
            misTareas = datosAgendaArrayList.get(position);
            tempVal.setText(misTareas.getDui());
            tempVal1.setText(misTareas.getNombre());
            tempVal2.setText(misTareas.getDay() + "-" + misTareas.getMes());

        }catch (Exception e){
        }
        return itemView;
    }
}
