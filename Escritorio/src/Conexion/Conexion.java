package Conexion;

import java.io.BufferedReader;
import java.io.FileReader;

public class Conexion {

    /**
     * Parametros de conexion
     */
    static String server = "";
    static String puerto = "";

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

    public String getServer() {
        return server;
    }

    public Integer getPuerto() {
        return Integer.valueOf(puerto);
    }
}
