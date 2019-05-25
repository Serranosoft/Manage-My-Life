package Conexion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase con la configuración y protocolos para la conexion a la base de datos
 * MySQL
 *
 * @version 1.0
 * @author manuel
 */
public class DBConnection {

    /**
     * Parametros de conexion
     */
    static String puerto = "";
    static String bd = "";
    static String login = "";
    static String password = "";
    static String url = "";
    static String server = "";

    Connection connection = null;

    /**
     * Constructor de la conexión para inicializar la conexion pasandole los
     * distintos atributos de la conexión
     */
    public DBConnection() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("config.txt"));

            String[] configuracion = new String[5];
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] palabra = line.split(":");
                if (palabra[0].equals("puerto")) {
                    configuracion[0] = palabra[1];
                }else if(palabra[0].equals("nombre_base_datos")) {
                    configuracion[1] = palabra[1];
                }else if(palabra[0].equals("usuario_base_datos")) {
                    configuracion[2] = palabra[1];
                }else if(palabra[0].equals("contrasena_base_datos")) {
                    configuracion[3] = palabra[1];
                }else if(palabra[0].equals("servidor")) {
                    configuracion[4] = palabra[1];
                }

            }

            puerto = configuracion[0];
            bd = configuracion[1];
            login = configuracion[2];
            password = configuracion[3];
            server = configuracion[4];
            url = "jdbc:mysql://"+server +"/";
            connection = DriverManager.getConnection(url + bd +"?serverTimezone=UTC", login, password);
            
            /*puerto = br.readLine();
            bd = br.readLine();
            login = br.readLine();
            password = br.readLine();
            connection = DriverManager.getConnection(url + bd + "?serverTimezone=UTC", login, password);*/

        } catch (SQLException e) {
            System.out.println(e);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Permite retornar la conexión
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Permite desconectarse de la conexión
     */
    public void desconectar() {
        connection = null;
    }
/**
 * Getter del puerto del servidor
 * @return puerto del servidor
 */
    public int getPuerto() {
        return Integer.valueOf(puerto);
    }
}
