package Conexion;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Clase para configurar la conexión con el servidor
 *
 * @author manue
 */
public class Conexion {

    /**
     * Parametros de conexion
     */
    static String server = "";
    static String puerto = "";

    /**
     * Constructor para obtener todos los parámetros del archivo de
     * configuración de conexión al servidor.
     */
    public Conexion() {
        try {

            BufferedReader br = new BufferedReader(new FileReader("config.txt"));

            String[] configuracion = new String[2];
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] palabra = line.split(":");
                if (palabra[0].equals("IP")) {
                    configuracion[0] = palabra[1];
                } else if (palabra[0].equals("puerto")) {
                    configuracion[1] = palabra[1];
                }

            }
            server = configuracion[0];
            puerto = configuracion[1];

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Método que devuelve la dirección IP del servidor
     *
     * @return Dirección IP del servidor
     */
    public String getServer() {
        return server;
    }

    /**
     * Método que devuelve el puerto del servidor
     *
     * @return Devuelve el puerto del servidor
     */
    public Integer getPuerto() {
        return Integer.valueOf(puerto);
    }
}
