
package vo;

import java.io.Serializable;


/**
 * Configuración de la clase Tarea
 * @author manue
 */
public class Producto implements Serializable {

    private int id;
    private String nombre_producto;
    private double precio_producto = 0;

    /**
     * Constructor completo de producto
     *
     * @param id Identificador del proucto
     * @param nombre_producto Nombre del producto
     */
    public Producto(int id, String nombre_producto) {
        this.id = id;
        this.nombre_producto = nombre_producto;
    }

    /**
     * Constructor por defecto de producto
     */
    public Producto() {
    }

    /**
     * Getter del identificador del producto
     *
     * @return Identificador del producto
     */
    public int getId() {
        return id;
    }

    /**
     * Setter del identificador del producto
     *
     * @param id Identificador del producto
     */
    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public double getPrecio_producto() {
        return precio_producto;
    }

    public void setPrecio_producto(double precio_producto) {
        this.precio_producto = precio_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

}
