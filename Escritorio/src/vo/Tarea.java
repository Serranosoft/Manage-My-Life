/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author manue
 */
public class Tarea implements Serializable {

    private int id;
    private String nombre;
    private String categoria;
    private Date fecha_inscrita;
    private Date fecha_realizar;
    private String descripcion;
    private int estado = 0;
    private int prioritario = 0;

    public Tarea(int id, String nombre, int estado) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
    }

    public Tarea(int id, String nombre, String categoria, Date fecha_inscrita, Date fecha_realizar, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.fecha_inscrita = fecha_inscrita;
        this.fecha_realizar = fecha_realizar;
        this.descripcion = descripcion;
    }

    public Tarea() {
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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

    public int getPrioritario() {
        return prioritario;
    }

    public void setPrioritario(int prioritario) {
        this.prioritario = prioritario;
    }

}
