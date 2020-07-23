package com.jcastillo.pruebasalmacenamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void irCrearArchivo(View view){
        Intent i = new Intent( this, EjemploFile.class );
        startActivity(i);
    }

    public void irCrearSharedPreferences(View view){
        Intent i = new Intent( this, EjemploSharedPreference.class);
        startActivity(i);

    }

    public void irCrearBD(View view) {
        Intent i = new Intent( this, EjemploSQLite.class);
        startActivity(i);
    }
}