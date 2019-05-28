
package Compartir;

import java.io.Serializable;

/**
 * Clase Objeto productos para el envio/recibo de datos entre cliente y servidor
 * @author manue
 */
public class Productos implements Serializable{
    
    private int ID;
    private String Nombre_Producto;
    private double Precio_Producto;
    private int ID_Gasto;
/**
 * Constructor de productos por defecto
 */
    public Productos() {
    }
/**
 * Constructor de productos completo
 * @param ID Identificador del producto
 * @param Nombre_Producto Nombre del producto
 * @param Precio_Producto Precio del producto
 * @param ID_Gasto Identificador del gasto al que hace referencia
 */
    public Productos(int ID, String Nombre_Producto, double Precio_Producto, int ID_Gasto) {
        this.ID = ID;
        this.Nombre_Producto = Nombre_Producto;
        this.Precio_Producto = Precio_Producto;
        this.ID_Gasto = ID_Gasto;
    }
/**
 * Getter del identificador del producto
 * @return  Identificador del producto
 */
    public int getID() {
        return ID;
    }
/**
 * Setter del identificador del producto
 * @param ID Identificador del producto
 */
    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre_Producto() {
        return Nombre_Producto;
    }

    public void setNombre_Producto(String Nombre_Producto) {
        this.Nombre_Producto = Nombre_Producto;
    }

    public double getPrecio_Producto() {
        return Precio_Producto;
    }

    public void setPrecio_Producto(double Precio_Producto) {
        this.Precio_Producto = Precio_Producto;
    }


    public int getID_Gasto() {
        return ID_Gasto;
    }

    public void setID_Gasto(int ID_Gasto) {
        this.ID_Gasto = ID_Gasto;
    }
    
    
    
    
    
}
