/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import vo.Tarea;

/**
 *
 * @author manue
 */
public class TareasOP {

    public final String cadcon = "jdbc:mysql://localhost/proyecto_final_prueba?serverTimezone=UTC";
    public final String user = "root";
    public final String password = "123456";

    public ArrayList<Tarea> selectTareas() {
        
        ArrayList<Tarea> listado_tareas = new ArrayList<Tarea>();
        ResultSet rs = null;
        Connection conexion = null;
        PreparedStatement ps = null;
        
        try {
            String sql = "SELECT * FROM Tareas";
            conexion = DriverManager.getConnection(cadcon, user, password);
            ps = conexion.prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Tarea tarea = new Tarea();
                tarea.setNombre(rs.getString("Nombre"));
                tarea.setEstado(Boolean.parseBoolean(rs.getString("Estado")));

                listado_tareas.add(tarea);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            
            try {
                rs.close();
                conexion.close();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(TareasOP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listado_tareas;

    }

}
