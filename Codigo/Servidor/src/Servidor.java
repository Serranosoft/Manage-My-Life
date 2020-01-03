
import Conexion.DBConnection;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Clase encargada de cargar los distintos hilos que se van ejecutando en la aplicación
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
                ex.printStackTrace();
            }

        }

    }

}
