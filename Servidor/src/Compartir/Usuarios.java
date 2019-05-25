package Compartir;

import java.io.Serializable;


/**
 * Clase Objeto usuario para el envio/recibo de datos entre cliente y servidor
 * @author manue
 */
public class Usuarios implements Serializable {

    private int id;
    private String usuario;
    private String nombre;
    private String contraseña;
    private int salario;
    private String imagen;
    private boolean existe;
/**
 * Constructor por defecto de usuario
 */
    public Usuarios() {
    }
/**
 * Constructor completo de usuario
 * @param id Identificador del usuario
 * @param usuario Usuario del usuario
 * @param nombre Nombre del usuario
 * @param contraseña Contraseña del usuario
 * @param salario salario del usuario
 * @param imagen imagen codificada del usuario
 * @param existe boolean para indicar si existe un usuario
 */
    public Usuarios(int id, String usuario, String nombre, String contraseña, int salario, String imagen, boolean existe) {
        this.id = id;
        this.usuario = usuario;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.salario = salario;
        this.imagen = imagen;
        this.existe = existe;
    }
/**
 * Getter de la imagen del usuario
 * @return Imagen del usuario codificada en string
 */
    public String getImagen() {
        return imagen;
    }
/**
 * Setter de la imágen del usuario
 * @param imagen Imagen del usuario codificada en string
 */
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
