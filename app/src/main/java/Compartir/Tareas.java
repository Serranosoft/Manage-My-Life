package Compartir;

import vo.Tarea;

import java.io.Serializable;
import java.util.ArrayList;

public class Tareas implements Serializable {

    private String nombre_tarea;
    private int  estado_tarea;
    private ArrayList<Tarea> resultados_tareas;
    private int idUsuario;

    public Tareas() {
    }

    public Tareas(String nombre_tarea, int estado_tarea, ArrayList<Tarea> resultados_tareas, int idUsuario) {
        this.nombre_tarea = nombre_tarea;
        this.estado_tarea = estado_tarea;
        this.resultados_tareas = resultados_tareas;
        this.idUsuario = idUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre_tarea() {
        return nombre_tarea;
    }

    public void setNombre_tarea(String nombre_tarea) {
        this.nombre_tarea = nombre_tarea;
    }

    public int getEstado_tarea() {
        return estado_tarea;
    }

    public void setEstado_tarea(int estado_tarea) {
        this.estado_tarea = estado_tarea;
    }

    public ArrayList<Tarea> getResultados_tareas() {
        return resultados_tareas;
    }

    public void setResultados_tareas(ArrayList<Tarea> resultados_tareas) {
        this.resultados_tareas = resultados_tareas;
    }
}
