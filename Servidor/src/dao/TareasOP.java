/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Compartir.Tareas;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public ArrayList<Tarea> selectTareas(Tareas tareas) {

        ArrayList<Tarea> listado_tareas = new ArrayList<>();
        ResultSet rs = null;
        Connection conexion = null;
        PreparedStatement ps = null;

        try {
            String sql = "SELECT * FROM Tarea WHERE Id_Usuario = ?";
            conexion = DriverManager.getConnection(cadcon, user, password);
            ps = conexion.prepareCall(sql);
            ps.setInt(1, tareas.getIdUsuario());
            rs = ps.executeQuery();
            while (rs.next()) {
                Tarea tarea = new Tarea();
                tarea.setNombre(rs.getString("Nombre"));
                tarea.setEstado(Integer.valueOf(rs.getString("Estado")));

                listado_tareas.add(tarea);
                System.out.println(tarea.getNombre());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                rs.close();
                conexion.close();
                ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return listado_tareas;

    }

}