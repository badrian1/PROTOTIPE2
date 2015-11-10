package com.example.boris.prototipe2;

import android.database.Cursor;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.SyncStateContract;

/**
 * Created by Boris on 03-11-2015.
 */
public class DataBaseManager {

    public static final String TABLE_NAME = "medicamentos";

    public static final String CN_ID = "_id";
    public static final String CN_NOMBRE = "nombre";
    public static final String CN_DOSIS_TOTALES = "dosis_totales";
    public static final String CN_CONSUMO_POR_DOSIS = "consumo_por_dosis";
    public static final String CN_HORARIOS_DOSIS = "horario_dosis";
    public static final String CN_FECHA_RECETA = "fecha_receta";

    public static final String CREATE_TABLE = "create table " + TABLE_NAME + "( "
            + CN_ID + " integer primary key autoincrement,"
            + CN_NOMBRE + " text not null,"
            + CN_DOSIS_TOTALES + " text,"
            + CN_CONSUMO_POR_DOSIS + " integer,"
            + CN_HORARIOS_DOSIS + " integer,"
            + CN_FECHA_RECETA + " text);";

    private DBHelper helper;
    private SQLiteDatabase db;

    public DataBaseManager(Context context) {

        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    public ContentValues generarContentValues (String nombre, String dosis_totales, String consumo_por_dosis,
                                               String horario_dosis, String fecha_receta){

        ContentValues valores = new ContentValues();
        valores.put(CN_NOMBRE,nombre);
        valores.put(CN_DOSIS_TOTALES,dosis_totales);
        valores.put(CN_CONSUMO_POR_DOSIS,consumo_por_dosis);
        valores.put(CN_HORARIOS_DOSIS,horario_dosis);
        valores.put(CN_FECHA_RECETA,fecha_receta);
        return valores;
    }

    public void insertar (String nombre, String dosis_totales, String consumo_por_dosis,
                          String horario_dosis, String fecha_receta){

        db.insert(TABLE_NAME, null, generarContentValues(nombre, dosis_totales, consumo_por_dosis,
                horario_dosis, fecha_receta));

    }

    public void insertar2(String nombre, String dosis_totales, String consumo_por_dosis,
                          String horario_dosis, String fecha_receta){

        db.execSQL("insert into " + TABLE_NAME + " values (null,'" + nombre + "'," + dosis_totales + "," +
                consumo_por_dosis + "," + horario_dosis + "," + fecha_receta + ")");
    }

    public Cursor cargarCursorMedicamentos(){

        String[] columna = new String[]{CN_ID,CN_NOMBRE,CN_DOSIS_TOTALES,CN_CONSUMO_POR_DOSIS,
                CN_HORARIOS_DOSIS,CN_FECHA_RECETA};

        return db.query(TABLE_NAME, columna,null,null,null,null,null);

    }

}
