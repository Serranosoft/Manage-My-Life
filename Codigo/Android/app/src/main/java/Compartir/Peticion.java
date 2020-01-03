package Compartir;

import java.io.Serializable;
/**
 * Clase Objeto de peticion para el envio/recibo de datos entre cliente/servidor
 */
public class Peticion implements Serializable {

    private int consulta;
    /**
     * Constructor por defecto de objeto compartido Peticion
     */
    public Peticion() {
    }
    /**
     * Constructor completo de objeto comparitdo Peticion
     * @param consulta numero de consulta de la petición
     */
    public Peticion(int consulta) {
        this.consulta = consulta;
    }

    /**
     * Getter de consulta
     * @return Objeto consulta con la petición
     */
    public int getConsulta() {
        return consulta;
    }
    /**
     * Setter de consulta
     * @param consulta Objeto consulta con la petición
     */
    public void setConsulta(int consulta) {
        this.consulta = consulta;
    }
}
