package com.jcastillo.pruebasalmacenamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EjemploSharedPreference extends AppCompatActivity {

    EditText edtSharedPreferencesNombre;
    EditText edtSharedPreferencesCorreo;
    TextView tvMostrarPrefrencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharedpreference);

        edtSharedPreferencesNombre = (EditText) findViewById(R.id.edtSharedPreferencesNombre);
        edtSharedPreferencesCorreo = (EditText) findViewById(R.id.edtSharedPreferencesCorreo);
        tvMostrarPrefrencias = (TextView) findViewById(R.id.tvMostrarPrefrencias);
    }

    public void crearSharedPreferences(View view) {


        SharedPreferences sharedPreferences = getSharedPreferences("mySharedPreference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String nombre = edtSharedPreferencesNombre.getText().toString();
        String correo = edtSharedPreferencesCorreo.getText().toString();

        editor.putString("nombre", nombre);
        editor.putString("correo", correo);
        editor.commit();

        Toast.makeText(this, "Se ha creado la preferencia compartida", Toast.LENGTH_SHORT).show();
        edtSharedPreferencesNombre.setText("");
        edtSharedPreferencesCorreo.setText("");

    }

    public void mostrarSharedPreferences(View view) {

        SharedPreferences sharedPreferences = getSharedPreferences("mySharedPreference", Context.MODE_PRIVATE);
        String nombre = sharedPreferences.getString("nombre", "No existe esa variable");
        String correo = sharedPreferences.getString("correo", "No existe esa variable");

        String preferencias = "Nombre: " + nombre + "\nCorreo: " + correo;
        tvMostrarPrefrencias.setText(preferencias);
    }
}