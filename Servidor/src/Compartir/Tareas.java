package Compartir;


import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import vo.Subtarea;
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
    private int id;
    private String nombre;
    private String categoria;
    private Date fecha_inscrita;
    private Date fecha_realizar;
    private String descripcion;
    private int estado = 0;
    private int prioritario = 0;
    private int idUsuario;
    private ArrayList<Tarea> resultados_tareas;
    private ArrayList<Subtarea> subtareas;

    public Tareas() {
    }

    public Tareas(int id, String nombre, String categoria, Date fecha_inscrita, Date fecha_realizar, String descripcion, int idUsuario, ArrayList<Tarea> resultados_tareas, ArrayList<Subtarea> subtareas) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.fecha_inscrita = fecha_inscrita;
        this.fecha_realizar = fecha_realizar;
        this.descripcion = descripcion;
        this.idUsuario = idUsuario;
        this.resultados_tareas = resultados_tareas;
        this.subtareas = subtareas;
    }

    public ArrayList<Subtarea> getSubtareas() {
        return subtareas;
    }

    public void setSubtareas(ArrayList<Subtarea> subtareas) {
        this.subtareas = subtareas;
    }
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Date getFecha_inscrita() {
        return fecha_inscrita;
    }

    public void setFecha_inscrita(Date fecha_inscrita) {
        this.fecha_inscrita = fecha_inscrita;
    }

    public Date getFecha_realizar() {
        return fecha_realizar;
    }

    public void setFecha_realizar(Date fecha_realizar) {
        this.fecha_realizar = fecha_realizar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getPrioritario() {
        return prioritario;
    }

    public void setPrioritario(int prioritario) {
        this.prioritario = prioritario;
    }

   

    public ArrayList<Tarea> getResultados_tareas() {
        return resultados_tareas;
    }

    public void setResultados_tareas(ArrayList<Tarea> resultados_tareas) {
        this.resultados_tareas = resultados_tareas;
    }

    
    
    
    
}
