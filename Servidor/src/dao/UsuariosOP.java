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
public class UsuariosOP {

    public final String cadcon = "jdbc:mysql://localhost/proyecto_final_prueba?serverTimezone=UTC";
    public final String user = "root";
    public final String password = "123456";

    public void insertarUsuario(Usuarios usuario){
        
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

}
