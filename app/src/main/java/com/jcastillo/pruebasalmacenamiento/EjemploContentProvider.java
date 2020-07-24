package com.jcastillo.pruebasalmacenamiento;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class EjemploContentProvider extends AppCompatActivity {

    private static final int CODIGO_SOILCITUD_PERMISO = 0;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
        activity = this;
    }

    public void mostrarLlamadas(View view) {

        if (checarStatusPermiso()) {
            consultarCPLlamadas();
        } else {
            solicitarPermiso();
        }

    }

    public void solicitarPermiso() {
        //Read Call Log
        boolean solicitarPermisoRCL = ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_CALL_LOG);

        //Write Call Log
        boolean solicitarPermisoWCL = ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_CALL_LOG);

        if (solicitarPermisoRCL && solicitarPermisoWCL) {

            Toast.makeText(activity, "Los permisos fueron otorgados", Toast.LENGTH_SHORT).show();

        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_CALL_LOG, Manifest.permission.READ_CALL_LOG}, CODIGO_SOILCITUD_PERMISO);
        }
    }

    public boolean checarStatusPermiso() {
        boolean permisoReadCallLog = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED;
        boolean permisoWriteCallLog = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALL_LOG) == PackageManager.PERMISSION_GRANTED;

        if (permisoReadCallLog && permisoWriteCallLog) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case CODIGO_SOILCITUD_PERMISO:
                if (checarStatusPermiso()) {
                    Toast.makeText(activity, "Ya estan activos los permisos", Toast.LENGTH_SHORT).show();
                    consultarCPLlamadas();
                } else {
                    Toast.makeText(activity, "No se otorgo el permiso", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    /*
     * Consultar Content Provider(CP) Llamadas
     * */
    public void consultarCPLlamadas() {

        TextView tvLamadas = (TextView) findViewById(R.id.tvLlamadas);
        tvLamadas.setText("");
        Uri direccionUriLlamadas = CallLog.Calls.CONTENT_URI;
        //Numero, fecha, tipo, duracion

        String[] campos = {
                CallLog.Calls.NUMBER,
                CallLog.Calls.DATE,
                CallLog.Calls.TYPE,
                CallLog.Calls.DURATION

        };

        ContentResolver contentResolver = getContentResolver();
        Cursor registros = contentResolver.query(direccionUriLlamadas, campos, null, null, CallLog.Calls.DATE + " DESC");
        while (registros.moveToNext()) {
            //OBTENEMOS LOS DATOS A PARITR DEL INDICE DE LA COLUMNA
            String numero = registros.getString(registros.getColumnIndex(campos[0]));
            Long fecha = registros.getLong(registros.getColumnIndex(campos[1]));
            int tipo = registros.getInt(registros.getColumnIndex(campos[2]));
            String duracion = registros.getString(registros.getColumnIndex(campos[3]));

            String tipoLlamada = "";
            //VALIDACION DE TIPO DE LLAMADA
            switch (tipo) {
                case CallLog.Calls.INCOMING_TYPE:
                    tipoLlamada = "Llamada de entrada";
                    break;
                case CallLog.Calls.MISSED_TYPE:
                    tipoLlamada = "Llamada de perdida";
                    break;
                case CallLog.Calls.OUTGOING_TYPE:
                    tipoLlamada = "Llamada de salida";
                    break;
                default:
                    tipoLlamada = "Llamada desconocida";
            }

            String detalle = "Numero: " + numero +
                    "\nFecha: " + DateFormat.format("dd/mm/yyyy k:mm", fecha) +
                    "\nTipo: " + tipoLlamada +
                    "\nDuracion: " + duracion + "s.\n";

            tvLamadas.append(detalle);
        }

    }
}