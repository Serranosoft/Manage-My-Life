/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author manue
 */
public class Gasto implements Serializable{
    
    private int id;
    private String nombre_gasto;
    private String tipo_gasto;
    private int precio_gasto = 0;

    public Gasto() {
    }

    
    public Gasto(int id, String nombre_gasto, String tipo_gasto) {
        this.id = id;
        this.nombre_gasto = nombre_gasto;
        this.tipo_gasto = tipo_gasto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_gasto() {
        return nombre_gasto;
    }

    public void setNombre_gasto(String nombre_gasto) {
        this.nombre_gasto = nombre_gasto;
    }

    public String getTipo_gasto() {
        return tipo_gasto;
    }

    public void setTipo_gasto(String tipo_gasto) {
        this.tipo_gasto = tipo_gasto;
    }

    public int getPrecio_gasto() {
        return precio_gasto;
    }

    public void setPrecio_gasto(int precio_gasto) {
        this.precio_gasto = precio_gasto;
    }
    
    
    
}
