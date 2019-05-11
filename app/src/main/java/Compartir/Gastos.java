package Compartir;

import java.io.Serializable;
import java.util.ArrayList;

import vo.Gasto;
import vo.Producto;

public class Gastos implements Serializable {
    private int id;
    private String nombre_gasto;
    private String tipo_gasto;
    private int precio_gasto = 0;
    private int idUsuario;
    private ArrayList<Gasto> resultados_gastos;
    private ArrayList<Producto> productos;

    public Gastos() {
    }


    public Gastos(int id, String nombre_gasto, String tipo_gasto, int idUsuario, ArrayList<Gasto> resultados_gastos, ArrayList<Producto> productos) {
        this.id = id;
        this.nombre_gasto = nombre_gasto;
        this.tipo_gasto = tipo_gasto;
        this.idUsuario = idUsuario;
        this.resultados_gastos = resultados_gastos;
        this.productos = productos;
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

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public ArrayList<Gasto> getResultados_gastos() {
        return resultados_gastos;
    }

    public void setResultados_gastos(ArrayList<Gasto> resultados_gastos) {
        this.resultados_gastos = resultados_gastos;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }



}
