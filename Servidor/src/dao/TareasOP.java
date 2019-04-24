/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Compartir.Tareas;
import java.sql.Connection;
import java.sql.Date;
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
                tarea.setId(Integer.valueOf(rs.getString("ID")));
                tarea.setNombre(rs.getString("Nombre"));
                tarea.setCategoria(rs.getString("Categoria"));
                tarea.setFecha_inscrita(Date.valueOf(rs.getString("Fecha_inscrita")));
                tarea.setFecha_realizar(Date.valueOf(rs.getString("Fecha_Realizar")));
                tarea.setDescripcion(rs.getString("Descripcion"));
                tarea.setEstado(Integer.valueOf(rs.getString("Estado")));
                tarea.setPrioritario(Integer.valueOf(rs.getString("Prioritario")));

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
                ex.printStackTrace();
            }
        }
        return listado_tareas;

    }
    
    public Tareas selectTarea(Tareas tareas) {

        ResultSet rs = null;
        Connection conexion = null;
        PreparedStatement ps = null;
        try {
            String sql = "SELECT * FROM Tarea WHERE ID = ?";
            conexion = DriverManager.getConnection(cadcon, user, password);
            ps = conexion.prepareCall(sql);
            ps.setInt(1, tareas.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                tareas.setNombre(rs.getString("Nombre"));
                tareas.setCategoria(rs.getString("Categoria"));
                tareas.setFecha_inscrita(Date.valueOf(rs.getString("Fecha_inscrita")));
                tareas.setFecha_realizar(Date.valueOf(rs.getString("Fecha_Realizar")));
                tareas.setDescripcion(rs.getString("Descripcion"));
                tareas.setEstado(Integer.valueOf(rs.getString("Estado")));
                tareas.setPrioritario(Integer.valueOf(rs.getString("Prioritario")));

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
        return tareas;

    }
    
    public void insertarTarea(Tareas tareas) {

        Connection conexion = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO Tarea (Nombre, Categoria, Fecha_Inscrita, Fecha_Realizar, Descripcion, Estado, Prioritario, Id_Usuario) VALUES (?,?,?,?,?,?,?,?)";

        try {
            conexion = DriverManager.getConnection(cadcon, user, password);
            ps = conexion.prepareCall(sql);

            ps.setString(1, tareas.getNombre());
            ps.setString(2, tareas.getCategoria());
            ps.setDate(3, tareas.getFecha_inscrita());
            ps.setDate(4, tareas.getFecha_realizar());
            ps.setString(5, tareas.getDescripcion());
            ps.setInt(6, tareas.getEstado());
            ps.setInt(7, tareas.getPrioritario());
            ps.setInt(8, tareas.getIdUsuario());

            ps.executeUpdate();

            System.out.println("Tarea introducida");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public void eliminarTarea(Tareas tareas) {

        Connection conexion = null;
        PreparedStatement ps = null;

        String sql = "DELETE FROM Tarea WHERE ID = ?";

        try {
            conexion = DriverManager.getConnection(cadcon, user, password);
            ps = conexion.prepareCall(sql);

            ps.setInt(1, tareas.getId());
            ps.executeUpdate();

            System.out.println("Tarea eliminada");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    

}
