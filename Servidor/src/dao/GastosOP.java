/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Compartir.Gastos;
import Compartir.Productos;
import Compartir.Subtareas;
import Compartir.Tareas;
import Conexion.DBConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import vo.Gasto;
import vo.Producto;
import vo.Subtarea;
import vo.Tarea;

/**
 *
 * @author manue
 */
public class GastosOP {

    DBConnection conn = new DBConnection();

    public ArrayList<Gasto> selectGastos(Gastos gastos) {

        ArrayList<Gasto> listado_gastos = new ArrayList<>();
        ResultSet rs = null;
        Connection conexion = null;
        PreparedStatement ps = null;

        try {
            String sql = "SELECT * FROM Gasto WHERE Id_Usuario = ?";
            conexion = conn.getConnection();
            ps = conexion.prepareCall(sql);
            ps.setInt(1, gastos.getIdUsuario());
            rs = ps.executeQuery();
            while (rs.next()) {
                Gasto gasto = new Gasto();
                gasto.setId(Integer.valueOf(rs.getString("ID")));
                gasto.setNombre_gasto(rs.getString("Nombre_Gasto"));
                gasto.setTipo_gasto(rs.getString("Tipo_Gasto"));
                gasto.setPrecio_gasto(Integer.valueOf(rs.getString("Precio_Gasto")));

                listado_gastos.add(gasto);

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
        return listado_gastos;

    }

    public void insertarGasto(Gastos gastos) {

        Connection conexion = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO Gasto (Nombre_Gasto, Tipo_Gasto, Precio_Gasto, ID_Usuario) VALUES (?,?,?,?)";

        try {
            conexion = conn.getConnection();
            ps = conexion.prepareCall(sql);

            ps.setString(1, gastos.getNombre_gasto());
            ps.setString(2, gastos.getTipo_gasto());
            ps.setInt(3, gastos.getPrecio_gasto());
            ps.setInt(4, gastos.getIdUsuario());

            ps.executeUpdate();

            System.out.println("Gasto introducido");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void eliminarGasto(Gastos gastos) {

        Connection conexion = null;
        PreparedStatement ps = null;

        String sql = "DELETE FROM Gasto WHERE ID = ?";

        try {
            conexion = conn.getConnection();
            ps = conexion.prepareCall(sql);

            ps.setInt(1, gastos.getId());
            ps.executeUpdate();

            System.out.println("Gasto eliminado");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList obtenerProductos(Gastos gastos) {
        ArrayList<Producto> listado_productos = new ArrayList<>();
        ResultSet rs = null;
        Connection conexion = null;
        PreparedStatement ps = null;

        try {
            String sql = "SELECT * FROM Producto WHERE ID_Gasto = ?";
            conexion = conn.getConnection();
            ps = conexion.prepareCall(sql);
            System.out.println(gastos.getId());
            ps.setInt(1, gastos.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(Integer.valueOf(rs.getString("ID")));
                producto.setNombre_producto(rs.getString("Nombre_Prod"));
                producto.setPrecio_producto(Integer.valueOf(rs.getString("Precio_Prod")));

                listado_productos.add(producto);
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
        return listado_productos;
    }

    public void insertarProducto(Productos productos) {
        Connection conexion = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO Producto (Nombre_Prod, Precio_Prod, ID_Gasto) VALUES (?,?,?)";
        try {
            conexion = conn.getConnection();
            ps = conexion.prepareCall(sql);

            ps.setString(1, productos.getNombre_Producto());
            ps.setInt(2, productos.getPrecio_Producto());
            ps.setInt(3, productos.getID_Gasto());

            ps.executeUpdate();

            System.out.println("Producto introducido");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
        public void eliminarProducto(Productos productos) {
        Connection conexion = null;
        PreparedStatement ps = null;

        try {
            String sql = "DELETE FROM Producto WHERE ID = ?";
            conexion = conn.getConnection();
            ps = conexion.prepareCall(sql);
            ps.setInt(1, productos.getID());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
