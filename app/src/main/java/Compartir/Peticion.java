package Compartir;

import java.io.Serializable;

public class Peticion implements Serializable {

    private int consulta;

    public Peticion() {
    }

    public Peticion(int consulta) {
        this.consulta = consulta;
    }


    public int getConsulta() {
        return consulta;
    }

    public void setConsulta(int consulta) {
        this.consulta = consulta;
    }
}
