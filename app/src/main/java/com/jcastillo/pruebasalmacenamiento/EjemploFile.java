package com.jcastillo.pruebasalmacenamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;

public class EjemploFile extends AppCompatActivity {
    EditText edtNombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejemplo_file);
        edtNombre = (EditText) findViewById(R.id.edtNombre);
    }


    public void crearArchivo(View view) {
        try {
            String nombre = edtNombre.getText().toString();
            FileOutputStream fileOutputStream;
            fileOutputStream = openFileOutput("MiArchivo.txt", Context.MODE_APPEND);
            fileOutputStream.write(nombre.getBytes());
            fileOutputStream.close();

            Toast.makeText(this, "El archivo fue creado con exito", Toast.LENGTH_LONG).show();
            edtNombre.setText("");
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Hubo un error al crear el archivo", Toast.LENGTH_SHORT).show();

        }

    }
}