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
public class Producto implements Serializable{
    
    private int id;
    private String nombre_producto;
    private int precio_producto = 0;

    public Producto(int id, String nombre_producto) {
        this.id = id;
        this.nombre_producto = nombre_producto;
    }

    public Producto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public int getPrecio_producto() {
        return precio_producto;
    }

    public void setPrecio_producto(int precio_producto) {
        this.precio_producto = precio_producto;
    }
    
    
    
    
    
}
