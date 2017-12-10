package com.example.admin.sqlitedemo;

/**
 * Created by Eduardo on 8/12/2017.
 */

public class Planta {

    private int Codigo;
    private String Nombre;

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int id) {
        this.Codigo = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }


    @Override
    public String toString() {
        return Codigo+">>"+Nombre;
    }

}
