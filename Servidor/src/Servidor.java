
import Compartir.Tareas;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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
        
        try {
            servidor = new ServerSocket(4444);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        while(true){
            
            try {
                usuario = servidor.accept();
                salida = new ObjectOutputStream(usuario.getOutputStream());
                HiloServidor juego = new HiloServidor(usuario, salida);
                juego.start();
                
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }   
        
    }   
    
}
