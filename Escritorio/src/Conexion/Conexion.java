package Conexion;




import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;


/**
 * Clase con la configuración y protocolos para la conexion a la base de datos
 * MySQL
 * @version 1.0
 * @author marodal
 */
public class Conexion {

    /**
     * Parametros de conexion
     */ 

    static String server = "";
    public Conexion() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("config.txt"));
            server = br.readLine();

        }catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public String getServer() {
        return server;
    }
}
