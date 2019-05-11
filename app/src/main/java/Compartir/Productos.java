package Compartir;

import java.io.Serializable;

public class Productos implements Serializable {

    private int ID;
    private String Nombre_Producto;
    private int Precio_Producto;
    private int ID_Gasto;

    public Productos() {
    }

    public Productos(int ID, String Nombre_Producto, int Precio_Producto, int ID_Gasto) {
        this.ID = ID;
        this.Nombre_Producto = Nombre_Producto;
        this.Precio_Producto = Precio_Producto;
        this.ID_Gasto = ID_Gasto;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre_Producto() {
        return Nombre_Producto;
    }

    public void setNombre_Producto(String Nombre_Producto) {
        this.Nombre_Producto = Nombre_Producto;
    }

    public int getPrecio_Producto() {
        return Precio_Producto;
    }

    public void setPrecio_Producto(int Precio_Producto) {
        this.Precio_Producto = Precio_Producto;
    }

    public int getID_Gasto() {
        return ID_Gasto;
    }

    public void setID_Gasto(int ID_Gasto) {
        this.ID_Gasto = ID_Gasto;
    }





}
