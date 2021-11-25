package com.example.lab9.Beans;

public class Estudiante extends Participante{

    private int idEstudiante;
    private String codigoAlumno;
    private int condicionAlumno;
    private double promedio;
    private int idUniversidad;
    private Condicion condicion;
    private Universidad universidad;

    public int getIdUniversidad() {
        return idUniversidad;
    }

    public void setIdUniversidad(int idUniversidad) {
        this.idUniversidad = idUniversidad;
    }

    public String getCodigoAlumno() {
        return codigoAlumno;
    }

    public void setCodigoAlumno(String codigoAlumno) {
        this.codigoAlumno = codigoAlumno;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Condicion getCondicion() {
        return condicion;
    }

    public void setCondicion(Condicion condicion) {
        this.condicion = condicion;
    }

    public int getCondicionAlumno() {
        return condicionAlumno;
    }

    public void setCondicionAlumno(int condicionAlumno) {
        this.condicionAlumno = condicionAlumno;
    }

    public Universidad getUniversidad() {
        return universidad;
    }

    public void setUniversidad(Universidad universidad) {
        this.universidad = universidad;
    }
}
