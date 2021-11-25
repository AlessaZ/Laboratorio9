package com.example.lab9.Beans;

public class Condicion {
    
    private int idCondicion;
    private String nombre;


    public int getIdCondicion() {
        return idCondicion;
    }

    public void setIdCondicion(int idCondicion) {
        this.idCondicion = idCondicion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String ColorTexto() {
        String colorTxt="";
        if(nombre.equals("eliminado")){
            colorTxt="color: red";
        }
        return colorTxt;
    }
}
