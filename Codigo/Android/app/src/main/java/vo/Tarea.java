package vo;

import java.io.Serializable;
import java.sql.Date;
/**
 * Clase Tarea encargada de mantener los distintos atributos del objeto compartido Tareas
 */
public class Tarea implements Serializable {

    private int id;
    private String nombre;
    private String categoria;
    private Date fecha_inscrita;
    private Date fecha_realizar;
    private String descripcion;
    private int estado = 0;
    private int prioritario = 0;

    /**
     * Constructor por defecto de la tarea
     */
    public Tarea() {
    }

    /**
     * Getter del identificador de la tarea
     * @return Identificador de la tarea
     */
    public int getId() {
        return id;
    }

    /**
     * Setter del identificador de la tarea
     * @param id Identificador de la tarea
     */
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Date getFecha_inscrita() {
        return fecha_inscrita;
    }

    public void setFecha_inscrita(Date fecha_inscrita) {
        this.fecha_inscrita = fecha_inscrita;
    }

    public Date getFecha_realizar() {
        return fecha_realizar;
    }

    public void setFecha_realizar(Date fecha_realizar) {
        this.fecha_realizar = fecha_realizar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrioritario() {
        return prioritario;
    }

    public void setPrioritario(int prioritario) {
        this.prioritario = prioritario;
    }


}
