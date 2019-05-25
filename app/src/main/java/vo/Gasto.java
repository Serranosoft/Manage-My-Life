package vo;

import java.io.Serializable;

/**
 * Clase Gasto encargada de mantener los distintos atributos del objeto compartido Gastos
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
     * @param id Identificador del gasto
     * @param nombre_gasto Nombre del gasto
     * @param tipo_gasto Tipo del gasto
     */
    public Gasto(int id, String nombre_gasto, String tipo_gasto) {
        this.id = id;
        this.nombre_gasto = nombre_gasto;
        this.tipo_gasto = tipo_gasto;
    }

    /**
     * Getter del identificador del gasto
     * @return Identificador del gasto
     */
    public int getId() {
        return id;
    }

    /**
     * Setter del identificador del gasto
     * @param id Identificador del gasto
     */
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
