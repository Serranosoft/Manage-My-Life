
import Compartir.Tareas;
import Conexion.DBConnection;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author manue
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServerSocket servidor = null;
        ObjectOutputStream salida;
        Socket usuario = null;
        DBConnection conexion = new DBConnection();

        try {
            servidor = new ServerSocket(conexion.getPuerto());

        } catch (Exception e) {
            e.printStackTrace();
        }
        while(true){

            try {
                usuario = servidor.accept();

                salida = new ObjectOutputStream(usuario.getOutputStream());
                HiloServidor hilo = new HiloServidor(usuario, salida);
                hilo.start();
            } catch (IOException ex) {
                //Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
