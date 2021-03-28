package com.example.appsqlite;

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

import de.hdodenhof.circleimageview.CircleImageView;

public class adaptadorImagenes extends BaseAdapter {
    Context context;
    ArrayList<amigos> datosAmigosArrayList;
    LayoutInflater layoutInflater;
    amigos misAmigos;

    public adaptadorImagenes(Context context, ArrayList<amigos> datosAmigosArrayList) {
        this.context = context;
        this.datosAmigosArrayList = datosAmigosArrayList;
    }

    @Override
    public int getCount() {
        return datosAmigosArrayList.size();
    }
    @Override
    public Object getItem(int position) {
        return datosAmigosArrayList.get(position);
    }
    @Override
    public long getItemId(int position) {
        return Long.parseLong( datosAmigosArrayList.get(position).getIdAmigo() );
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.activity_design, parent, false);
        CircleImageView imgViewView = itemView.findViewById(R.id.img);
        TextView tempVal = itemView.findViewById(R.id.txtNom);
        TextView tempVal1 = itemView.findViewById(R.id.txtNum);
        try{
            misAmigos = datosAmigosArrayList.get(position);
            tempVal.setText(misAmigos.getNombre());
            tempVal1.setText(misAmigos.getTelefono());
            Bitmap imagenBitmap = BitmapFactory.decodeFile(misAmigos.getUrlImg());
            imgViewView.setImageBitmap(imagenBitmap);
        }catch (Exception e){
        }
        return itemView;
    }
}
