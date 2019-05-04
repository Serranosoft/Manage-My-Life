/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Compartir.Subtareas;
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
import vo.Subtarea;
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

    public void actualizarTarea(Tareas tareas) {
        Connection conexion = null;
        PreparedStatement ps = null;

        String sql = "UPDATE Tarea SET Nombre = ?, Categoria = ?, Fecha_Inscrita = ?, Fecha_Realizar = ?, Descripcion = ?, Estado = ?, Prioritario = ? WHERE ID = ?";

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
            ps.setInt(8, tareas.getId());
            ps.executeUpdate();

            System.out.println("Tarea actualizada");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList obtenerSubtareas(Tareas tareas) {
        ArrayList<Subtarea> listado_subtareas = new ArrayList<>();
        ResultSet rs = null;
        Connection conexion = null;
        PreparedStatement ps = null;

        try {
            String sql = "SELECT * FROM Subtarea WHERE ID_Tarea = ?";
            conexion = DriverManager.getConnection(cadcon, user, password);
            ps = conexion.prepareCall(sql);
            ps.setInt(1, tareas.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                Subtarea subtarea = new Subtarea();
                subtarea.setId(Integer.valueOf(rs.getString("ID")));
                subtarea.setNombre(rs.getString("Nombre"));
                subtarea.setEstado(Integer.valueOf(rs.getString("Estado")));

                listado_subtareas.add(subtarea);
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
        return listado_subtareas;
    }

    public void insertarSubtarea(Subtareas subtareas) {
        Connection conexion = null;
        PreparedStatement ps = null;

        try {
            String sql = "INSERT INTO Subtarea (Nombre, Estado, ID_Tarea) VALUES (?,?,?)";
            conexion = DriverManager.getConnection(cadcon, user, password);
            ps = conexion.prepareCall(sql);
            ps.setString(1, subtareas.getNombre());
            ps.setInt(2, subtareas.getEstado());
            ps.setInt(3, subtareas.getID_Tarea());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void eliminarSubtarea(Subtareas subtareas) {
        Connection conexion = null;
        PreparedStatement ps = null;

        try {
            String sql = "DELETE FROM Subtarea WHERE ID = ?";
            conexion = DriverManager.getConnection(cadcon, user, password);
            ps = conexion.prepareCall(sql);
            ps.setInt(1, subtareas.getId());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void actualizarEstadoTarea(Tareas tareas) {
        Connection conexion = null;
        PreparedStatement ps = null;

        String sql = "UPDATE Tarea SET Estado = ? WHERE ID = ?";

        try {
            conexion = DriverManager.getConnection(cadcon, user, password);
            ps = conexion.prepareCall(sql);

            ps.setInt(1, tareas.getEstado());
            ps.setInt(2, tareas.getId());
            ps.executeUpdate();

            System.out.println("Estado de la tarea " + tareas.getId() + tareas.getNombre() + " actualizado");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void actualizarEstadoSubtarea(Subtareas subtareas) {
        Connection conexion = null;
        PreparedStatement ps = null;

        String sql = "UPDATE Subtarea SET Estado = ? WHERE ID = ?";

        try {
            conexion = DriverManager.getConnection(cadcon, user, password);
            ps = conexion.prepareCall(sql);

            ps.setInt(1, subtareas.getEstado());
            ps.setInt(2, subtareas.getId());
            ps.executeUpdate();

            System.out.println("Estado de la subtarea " + subtareas.getId() + subtareas.getNombre() + " actualizado");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
