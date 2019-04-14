
import Compartir.Peticion;
import Compartir.Tareas;
import Compartir.Usuarios;
import dao.TareasOP;
import dao.UsuariosOP;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import vo.Tarea;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author manue
 */
class HiloServidor extends Thread {

    ObjectOutputStream salida;
    ObjectInputStream entrada;

    Socket usuario = null;

    Tareas tareas = new Tareas();
    Usuarios usuarios = new Usuarios();
    Peticion peticion = new Peticion();
    int consulta = 0;

    HiloServidor(Socket usuario, ObjectOutputStream salida) {

        this.usuario = usuario;

        this.salida = salida;
        try {
            this.entrada = new ObjectInputStream(usuario.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void run() {

        try {
            // Se queda esperando a que el cliente mande una consulta a traves del objeto peticion
            peticion = (Peticion) entrada.readObject();
            consulta = peticion.getConsulta();
            switch (consulta) {
                case 10:
                    obtenerTareas(tareas);
                    salida.writeObject(tareas);
                    break;
                case 20:
                    usuarios = (Usuarios) entrada.readObject();
                    insertarUsuario(usuarios);
                    break;
                    
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }

    private void obtenerTareas(Tareas tareas) {

        ArrayList<Tarea> listado_tareas = null;
        try {

            TareasOP top = new TareasOP();
            listado_tareas = top.selectTareas();

        } catch (Exception e) {
            e.printStackTrace();
        }
        tareas.setResultados_tareas(listado_tareas);
    }
    
    private void insertarUsuario(Usuarios usuarios) {
        
        UsuariosOP uop = new UsuariosOP();
        
        uop.insertarUsuario(usuarios);
    }

}
