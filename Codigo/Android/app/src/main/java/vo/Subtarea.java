package vo;

import java.io.Serializable;
/**
 * Clase Subtarea encargada de mantener los distintos atributos del objeto compartido Subtareas
 */
public class Subtarea implements Serializable {

    private int id;
    private String nombre;
    private int estado;
    private int ID_Tarea;

    /**
     * Constructor por defecto de la clase subtarea
     */
    public Subtarea() {
    }

    /**
     * Constructor completo de la clase subtarea
     * @param id Identificador de la subtarea
     * @param nombre Nombre de la subtarea
     * @param estado Estado de la subtarea
     * @param ID_Tarea Identificador de la tarea relacionado a la subtarea
     */
    public Subtarea(int id, String nombre, int estado, int ID_Tarea) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.ID_Tarea = ID_Tarea;
    }

    /**
     * Getter del identificador de la tarea relacionado a la subtarea
     * @return Identificador de la tarea relacionado a la subtarea
     */
    public int getID_Tarea() {
        return ID_Tarea;
    }

    /**
     * Setter del identificador de la tarea relacionado a la subtarea
     * @param ID_Tarea Identificador de la tarea relacionado a la subtarea
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

