
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
                case 1:
                    usuarios = (Usuarios) entrada.readObject();
                    insertarUsuario(usuarios);
                    break;
                case 2:
                    usuarios = (Usuarios) entrada.readObject();
                    comprobarUsuario(usuarios);
                    salida.writeObject(usuarios);
                    break;
                case 3:
                    tareas = (Tareas) entrada.readObject();
                    obtenerTareas(tareas);
                    salida.writeObject(tareas);
                    break;
                case 4:
                    tareas = (Tareas) entrada.readObject();
                    insertarTarea(tareas);
                    break;
                case 5:
                    tareas = (Tareas) entrada.readObject();
                    obtenerTarea(tareas);
                    salida.writeObject(tareas);
                    break; 
                case 6:
                    tareas = (Tareas) entrada.readObject();
                    eliminarTarea(tareas);
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
            listado_tareas = top.selectTareas(tareas);

        } catch (Exception e) {
            e.printStackTrace();
        }
        tareas.setResultados_tareas(listado_tareas);
    }
    
    private void insertarUsuario(Usuarios usuarios) {
        
        UsuariosOP uop = new UsuariosOP();
        uop.insertarUsuario(usuarios);
    }

    private void comprobarUsuario(Usuarios usuarios) {
        
        UsuariosOP uop = new UsuariosOP();
        System.out.println("Llamo al metodo comprobar usuario");
        usuarios = uop.comprobarUsuario(usuarios);
    }
    
    private void insertarTarea (Tareas tareas) {
        TareasOP top = new TareasOP();
        top.insertarTarea(tareas);
    }
    
    private void obtenerTarea(Tareas tareas) {
        try {

            TareasOP top = new TareasOP();
            tareas = top.selectTarea(tareas);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void eliminarTarea(Tareas tareas) {
        
        try {
            TareasOP top = new TareasOP();
            top.eliminarTarea(tareas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
