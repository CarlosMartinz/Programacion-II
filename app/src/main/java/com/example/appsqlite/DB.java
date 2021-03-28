package com.example.appsqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;


public class DB extends SQLiteOpenHelper {
    static String nombreDB = "db_amigos";
    static String tblAmigos = "CREATE TABLE tblamigos(idAmigo integer primary key autoincrement, nombre text, direccion text, telefono text, urlPhoto text)";

    public DB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nombreDB, factory, version); //CREATE DATABASE db_amigos; -> SQLite
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tblAmigos);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //No, se usa porque es para migrar o actualizar a una nueva version...
    }
    public Cursor administracion_amigos(String accion, String[] datos){
        Cursor datosCursor = null;
        SQLiteDatabase sqLiteDatabaseW = getWritableDatabase();
        SQLiteDatabase sqLiteDatabaseR = getReadableDatabase();
        switch (accion){
            case "consultar":
                datosCursor = sqLiteDatabaseR.rawQuery("select * from tblamigos order by nombre",null);
                break;

            case "nuevo":
                sqLiteDatabaseW.execSQL("INSERT INTO tblamigos(nombre,direccion,telefono,urlPhoto) VALUES ('"+datos[1]+"','"+datos[2]+"','"+datos[3]+"','"+datos[4]+"')");

            case "modificar":
                sqLiteDatabaseW.execSQL("UPDATE tblamigos SET nombre='"+datos[1]+"',telefono='"+datos[2]+"',direccion='"+datos[3]+"',urlPhoto='"+datos[4]+"' WHERE idAmigo='"+datos[0]+"'");
                break;

            case "eliminar":
                sqLiteDatabaseW.execSQL("delete from usuarios where idUsuario='" + datos[0] + "'");
                break;
        }
        return datosCursor;
    }
}
