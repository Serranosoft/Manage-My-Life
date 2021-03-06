
package vo;

import java.io.Serializable;

/**
 * Configuración de la clase Tarea
 * @author manue
 */
public class Subtarea implements Serializable{
    
    private int id;
    private String nombre;
    private int estado;
    private int ID_Tarea;

    public Subtarea() {
    }

    public Subtarea(int id, String nombre, int estado, int ID_Tarea) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.ID_Tarea = ID_Tarea;
    }

    public int getID_Tarea() {
        return ID_Tarea;
    }

    public void setID_Tarea(int ID_Tarea) {
        this.ID_Tarea = ID_Tarea;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    
    
}
