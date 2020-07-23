package com.jcastillo.pruebasalmacenamiento.pojo;

/**
 * Created by Jonathan Castillo on 22,July,2020
 */


public class Contacto {
    private int id;
    private String nombre;
    private String email;

    public Contacto(int id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public Contacto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
