package com.example.admin.sqlitedemo;

/**
 * Created by Eduardo on 8/12/2017.
 */

public class Maquina {

    private int Codigo;
    private String Nombre;
    private int LíneaDeProduccion;
    private int Planta;

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

    public int getLíneaDeProduccion() {
        return LíneaDeProduccion;
    }

    public void setLíneaDeProduccion(int LíneaDeProduccion) {
        this.LíneaDeProduccion = LíneaDeProduccion;
    }

    public int getPlanta() {
        return Planta;
    }

    public void setPlanta(int Planta) {
        this.Planta = Planta;
    }

    @Override
    public String toString() {
        return Codigo+">>"+Nombre+">>"+LíneaDeProduccion+">>"+Planta;
    }

}
