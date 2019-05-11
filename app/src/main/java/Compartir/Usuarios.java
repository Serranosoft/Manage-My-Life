package Compartir;

import java.io.Serializable;

public class Usuarios implements Serializable {

    private int id;
    private String usuario;
    private String nombre;
    private String contraseña;
    private int salario;
    private String imagen;
    private boolean existe;

    public Usuarios() {
    }

    public Usuarios(int id, String usuario, String nombre, String contraseña, int salario, String imagen, boolean existe) {
        this.id = id;
        this.usuario = usuario;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.salario = salario;
        this.imagen = imagen;
        this.existe = existe;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



}

