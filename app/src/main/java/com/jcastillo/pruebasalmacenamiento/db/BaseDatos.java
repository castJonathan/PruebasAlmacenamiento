package com.jcastillo.pruebasalmacenamiento.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.jcastillo.pruebasalmacenamiento.pojo.Contacto;

import java.util.ArrayList;

/**
 * Created by Jonathan Castillo on 22,July,2020
 */


public class BaseDatos extends SQLiteOpenHelper {

    private Context context;

    public BaseDatos(@Nullable Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String queryCrearTablaContacto = "CREATE TABLE " + ConstantesBaseDatos.TABLE_NAME_CONTACTO + "(" +
                ConstantesBaseDatos.TABLE_CONTACTO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_CONTACTO_NOMBRE + " TEXT, " +
                ConstantesBaseDatos.TABLE_CONTACTO_EMAIL + " TEXT" +
                ")";

        sqLiteDatabase.execSQL(queryCrearTablaContacto);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ ConstantesBaseDatos.TABLE_NAME_CONTACTO);
        onCreate(sqLiteDatabase);

    }

    public void insertarContacto(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_NAME_CONTACTO, null, contentValues);
        db.close();

    }

    public ArrayList<Contacto> obtenerTodosLosContactos(){

        ArrayList<Contacto> contactos = new ArrayList<Contacto>();

        String query ="SELECT * FROM "+ConstantesBaseDatos.TABLE_NAME_CONTACTO;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()){
            Contacto contactoActual = new Contacto();
            contactoActual.setId(registros.getInt(registros.getColumnIndex(ConstantesBaseDatos.TABLE_CONTACTO_ID)));
            contactoActual.setNombre(registros.getString(registros.getColumnIndex(ConstantesBaseDatos.TABLE_CONTACTO_NOMBRE)));
            contactoActual.setEmail(registros.getString(registros.getColumnIndex(ConstantesBaseDatos.TABLE_CONTACTO_EMAIL)));
            contactos.add(contactoActual);
        }
        db.close();
        return contactos;

    }

}
