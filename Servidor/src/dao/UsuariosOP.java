/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Compartir.Usuarios;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author manue
 */
public class UsuariosOP {

    public final String cadcon = "jdbc:mysql://localhost/proyecto_final_prueba?serverTimezone=UTC";
    public final String user = "root";
    public final String password = "123456";

    public void insertarUsuario(Usuarios usuario) {

        Connection conexion = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO Usuario (Usuario, Nombre, Salario, Contraseña) VALUES (?,?,?,?)";

        try {
            conexion = DriverManager.getConnection(cadcon, user, password);
            ps = conexion.prepareCall(sql);

            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getNombre());
            ps.setInt(3, usuario.getSalario());
            ps.setString(4, usuario.getContraseña());

            ps.executeUpdate();

            System.out.println("Usuario introducido");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Usuarios comprobarUsuario(Usuarios usuario) {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Usuario where Usuario = ? AND Contraseña = ?";

        try {
            conexion = DriverManager.getConnection(cadcon, user, password);
            ps = conexion.prepareCall(sql);

            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getContraseña());
            rs = ps.executeQuery();
            if (rs.next() == false) {
                usuario.setExiste(false);
            } else {
                usuario.setExiste(true);
                usuario.setId(Integer.valueOf(rs.getString("ID")));
                usuario.setUsuario(rs.getString("Usuario"));
                usuario.setNombre(rs.getString("Nombre"));
                usuario.setSalario(Integer.valueOf(rs.getString("Salario")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                conexion.close();
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return usuario;
    }

}
