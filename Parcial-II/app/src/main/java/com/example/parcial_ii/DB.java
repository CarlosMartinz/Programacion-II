package com.example.parcial_ii;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.format.Time;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DB extends SQLiteOpenHelper {
    Context miContext;
    static String nombreDB = "db_agenda";
    static String tblAgenda = "CREATE TABLE tblagenda(idAgenda integer primary key autoincrement, dui text, nombre text, dia text, mes text)";

    public DB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nombreDB, factory, version);
        miContext=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tblAgenda);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public Cursor administracion_agenda(String accion, String[] datos){
        try {
            Cursor datosCursor = null;

            Time today = new Time(Time.getCurrentTimezone());
            today.setToNow();
            int day = today.monthDay;
            int meth = today.month;
            meth = meth + 1;

            SQLiteDatabase sqLiteDatabaseW = getWritableDatabase();
            SQLiteDatabase sqLiteDatabaseR = getReadableDatabase();
            switch (accion) {
                case "consultar":
                    datosCursor = sqLiteDatabaseR.rawQuery("select * from tblagenda ", null);
                    // datosCursor = sqLiteDatabaseR.rawQuery("select * from tblagenda", null);
                    break;

                case "nuevo":
                    sqLiteDatabaseW.execSQL("INSERT INTO tblagenda(dui,nombre,dia,mes) VALUES  ('" + datos[1] + "','" + datos[2] + "','" + datos[3] + "','" + datos[4] + "')");

                case "modificar":
                    sqLiteDatabaseW.execSQL("UPDATE tblagenda SET dui='" + datos[1] + "',dia='" + datos[2] + "',nombre='" + datos[3] + "', mes ='" + datos[4] + "' WHERE idAgenda='" + datos[0] + "'");
                    break;

                case "eliminar":
                    sqLiteDatabaseW.execSQL("delete from tblagenda where idAgenda='" + datos[0] + "'");
                    break;
            }
            return datosCursor;
        }catch (Exception e){
            Toast.makeText(miContext, "Error en la administracion de la BD "+ e.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }

}
