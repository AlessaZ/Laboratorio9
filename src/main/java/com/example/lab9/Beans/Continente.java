package com.example.lab9.Beans;

public class Continente {

    private int idContinente;
    private String nombre;

    public int getIdContinente() {
        return idContinente;
    }

    public void setIdContinente(int idContinente) {
        this.idContinente = idContinente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String ColorFila(int idC) {
        String color;
        switch(idC){
            case 1:
                color="table-danger";
                break;
            case 2:
                color= "table-warning";
                break;
            case 3:
                color= "table-success";
                break;
            case 4:
                color= "table-info";
                break;
            default:
                color="";
        }
        return color;
    }
}
