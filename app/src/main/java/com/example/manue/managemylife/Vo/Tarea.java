package com.example.manue.managemylife.Vo;

public class Tarea {

    private String nombre;
    private boolean estado;
    private boolean prioritario;

    public Tarea(String nombre, boolean estado, boolean prioritario) {
        this.nombre = nombre;
        this.estado = estado;
        this.prioritario = prioritario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public boolean isPrioritario() {
        return prioritario;
    }

    public void setPrioritario(boolean prioritario) {
        this.prioritario = prioritario;
    }
}
