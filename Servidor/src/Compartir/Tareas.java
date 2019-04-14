package Compartir;


import java.io.Serializable;
import java.util.ArrayList;
import vo.Tarea;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author manue
 */
public class Tareas implements Serializable{
    private String nombre_tarea;
    private boolean estado_tarea;
    private int consulta;
    private ArrayList<Tarea> resultados_tareas;

    public Tareas() {
    }

    public Tareas(String nombre_tarea, boolean estado_tarea, int consulta, ArrayList<Tarea> resultados_tareas) {
        this.nombre_tarea = nombre_tarea;
        this.estado_tarea = estado_tarea;
        this.consulta = consulta;
        this.resultados_tareas = resultados_tareas;
    }

    public String getNombre_tarea() {
        return nombre_tarea;
    }

    public void setNombre_tarea(String nombre_tarea) {
        this.nombre_tarea = nombre_tarea;
    }

    public boolean isEstado_tarea() {
        return estado_tarea;
    }

    public void setEstado_tarea(boolean estado_tarea) {
        this.estado_tarea = estado_tarea;
    }

    public int getConsulta() {
        return consulta;
    }

    public void setConsulta(int consulta) {
        this.consulta = consulta;
    }

    public ArrayList<Tarea> getResultados_tareas() {
        return resultados_tareas;
    }

    public void setResultados_tareas(ArrayList<Tarea> resultados_tareas) {
        this.resultados_tareas = resultados_tareas;
    }

    
    
    
    
}
