/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Compartir;

import java.io.Serializable;

/**
 *
 * @author manue
 */
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
