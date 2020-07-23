package com.jcastillo.pruebasalmacenamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jcastillo.pruebasalmacenamiento.db.BaseDatos;
import com.jcastillo.pruebasalmacenamiento.db.ConstantesBaseDatos;
import com.jcastillo.pruebasalmacenamiento.pojo.Contacto;

import java.util.ArrayList;

public class EjemploSQLite extends AppCompatActivity {

    EditText edtDBNombre;
    EditText edtDBCorreo;
    TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        edtDBNombre = (EditText) findViewById(R.id.edtDBNombre);
        edtDBCorreo = (EditText) findViewById(R.id.edtDBCorreo);
        tvData = (TextView) findViewById(R.id.tvData);

    }

    public void mostrarDatosBDS(View view) {
        BaseDatos db = new BaseDatos(this);
        ArrayList<Contacto> contactos = db.obtenerTodosLosContactos();
        String data = "";
        for (Contacto c : contactos) {
            data += "\nNombre: " + c.getNombre() + "\nCorreo: " + c.getEmail();
        }
        if (data.equals("")) {
            data = "Sin datos almacenados";

        }
        tvData.setText(data);


    }

    public void guardarContactoBD(View view) {
        String nombre = edtDBNombre.getText().toString().trim();
        String email = edtDBCorreo.getText().toString().trim();
        if (nombre.equals("") && email.equals("")) {
            Toast.makeText(this, "Debe ingresar nombre y correo", Toast.LENGTH_SHORT).show();
        } else {
            BaseDatos db = new BaseDatos(this);
            ContentValues values = new ContentValues();
            values.put(ConstantesBaseDatos.TABLE_CONTACTO_NOMBRE, nombre);
            values.put(ConstantesBaseDatos.TABLE_CONTACTO_EMAIL, email);
            db.insertarContacto(values);
        }
        edtDBNombre.setText("");
        edtDBCorreo.setText("");
    }
}