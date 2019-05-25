/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Configuración de la clase gasto
 *
 * @author manue
 */
public class Gasto implements Serializable {

    private int id;
    private String nombre_gasto;
    private String tipo_gasto;
    private int precio_gasto = 0;

    /**
     * Constructor por defecto de la clase Gasto
     */
    public Gasto() {
    }

    /**
     * Constructor completo de la clase gasto
     *
     * @param id Identificador del gasto
     * @param nombre_gasto Nombre del gasto
     * @param tipo_gasto Tipo de gasto
     */
    public Gasto(int id, String nombre_gasto, String tipo_gasto) {
        this.id = id;
        this.nombre_gasto = nombre_gasto;
        this.tipo_gasto = tipo_gasto;
    }

    /**
     * Getter de identificador del gasto
     *
     * @return identificador del gasto
     */
    public int getId() {
        return id;
    }

    /**
     * Setter del identificador del gasto
     *
     * @param id identificador del gasto
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter del nombre del gasto
     *
     * @return nombre del gasto
     */
    public String getNombre_gasto() {
        return nombre_gasto;
    }

    /**
     * Setter del nombre del gasto
     *
     * @param nombre_gasto nombre del gasto
     */
    public void setNombre_gasto(String nombre_gasto) {
        this.nombre_gasto = nombre_gasto;
    }

    /**
     * Getter del tipo de gasto
     *
     * @return tipo de gasto
     */
    public String getTipo_gasto() {
        return tipo_gasto;
    }

    /**
     * Setter del tipo de gasto
     *
     * @param tipo_gasto Tipo de gasto
     */
    public void setTipo_gasto(String tipo_gasto) {
        this.tipo_gasto = tipo_gasto;
    }

    /**
     * Getter del precio del gasto
     *
     * @return precio del gasto
     */
    public int getPrecio_gasto() {
        return precio_gasto;
    }

    /**
     * Setter del precio del gasto
     *
     * @param precio_gasto precio del gasto
     */
    public void setPrecio_gasto(int precio_gasto) {
        this.precio_gasto = precio_gasto;
    }

}
