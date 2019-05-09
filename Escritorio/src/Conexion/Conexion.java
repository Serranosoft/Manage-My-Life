package Conexion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    /**
     * Parametros de conexion
     */
    static String server = "";
    static String puerto = "";
    // jasper report
    static String bd = "";
    static String login = "";
    static String password = "";
    static String url = "jdbc:mysql://localhost/";
    Connection connection = null;

    public Conexion() {
        try {

            BufferedReader br = new BufferedReader(new FileReader("config.txt"));

            server = br.readLine();
            puerto = br.readLine();
            bd = br.readLine();
            login = br.readLine();
            password = br.readLine();
            connection = DriverManager.getConnection(url + bd + "?serverTimezone=UTC", login, password);
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String getServer() {
        return server;
    }

    public Connection getConnection() {
        return connection;
    }

    public void desconectar() {
        connection = null;
    }
    public Integer getPuerto() {
        return Integer.valueOf(puerto);
    }
}
