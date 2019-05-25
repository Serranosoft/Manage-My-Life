
package Compartir;

import java.io.Serializable;




/**
 * Clase Objeto informe para el envio/recibo de datos entre cliente y servidor
 * @author manue
 */
public class Informes implements Serializable{
    
    private String tipo;
    private String informe;
    private int id_usuario;

/**
 * Constructor por defecto de Informes
 */
    public Informes() {
    }
/**
 * Getter del tipo de informe
 * @return tipo de informe
 */
    public String getTipo() {
        return tipo;
    }
/**
 * Setter del tipo de informe
 * @param tipo 
 */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getInforme() {
        return informe;
    }

    public void setInforme(String informe) {
        this.informe = informe;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }


    
    
    
}
