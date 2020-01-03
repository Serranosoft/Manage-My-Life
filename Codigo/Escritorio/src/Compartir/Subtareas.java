
package Compartir;

import java.io.Serializable;

/**
 * Clase Objeto subtareas para el envio/recibo de datos entre cliente y servidor
 * @author manue
 */
public class Subtareas implements Serializable{
    
    private int id;
    private String nombre;
    private int estado;
    private int ID_Tarea;
/**
 * Constructor por defecto de subtareas
 */
    public Subtareas() {
    }
/**
 * Constructor completo de subtareas
 * @param id Identificador de la subtarea
 * @param nombre Nombre de la subtarea
 * @param estado Estado de la subtarea
 * @param ID_Tarea Identificador de la tarea al que hace relación
 */
    public Subtareas(int id, String nombre, int estado, int ID_Tarea) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.ID_Tarea = ID_Tarea;
    }
/**
 * Getter del identificador de la tarea al que hace relación
 * @return Identificador de la tarea al que hace relación
 */
    public int getID_Tarea() {
        return ID_Tarea;
    }
/**
 * Setter del identificador de la tarea al que hace relación
 * @param ID_Tarea Identificador de la tarea
 */
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
