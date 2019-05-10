/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Compartir.Usuarios;
import Conexion.DBConnection;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import java.util.Base64;

/**
 *
 * @author manue
 */
public class UsuariosOP {

    DBConnection conn = new DBConnection();

    public void insertarUsuario(Usuarios usuario) {

        Connection conexion = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO Usuario (Usuario, Nombre, Salario, Contraseña) VALUES (?,?,?,?)";

        try {
            conexion = conn.getConnection();
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
        String imageString = null;
        String sql = "SELECT * FROM Usuario where Usuario = ? AND Contraseña = ?";

        try {
            conexion = conn.getConnection();
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
                usuario.setImagen(rs.getString("Imagen"));
                try {
                    if (usuario.getImagen().isEmpty()) {
                        usuario.setImagen("null");
                    } else {
                        File ruta_imagen_enviar = new File(usuario.getImagen());
                        byte[] imageBytes = Files.readAllBytes(ruta_imagen_enviar.toPath());
                        imageString = Base64.getEncoder().encodeToString(imageBytes);
                        usuario.setImagen(imageString);
                    }

                } catch (NoSuchFileException ex) {
                    usuario.setImagen("null");
                    ex.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

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

    public void modificarUsuario(Usuarios usuario) {

        Connection conexion = null;
        PreparedStatement ps = null;

        String sql = "UPDATE Usuario SET Usuario = ?, Nombre = ?, Salario = ?, Imagen = ? WHERE ID = ?";

        try {
            conexion = conn.getConnection();
            ps = conexion.prepareCall(sql);

            byte[] imageByte;
            BufferedImage image = null;
            try {
                if (usuario.getImagen() == null) {

                } else {
                    imageByte = Base64.getDecoder().decode(usuario.getImagen());
                    ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
                    image = ImageIO.read(bis);
                    ImageIO.write(image, "jpg", new File("media/" + usuario.getUsuario() + "_profile.jpg"));
                    bis.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            usuario.setImagen("media/" + usuario.getUsuario() + "_profile.jpg");
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getNombre());
            ps.setInt(3, usuario.getSalario());
            ps.setString(4, usuario.getImagen());
            ps.setInt(5, usuario.getId());

            ps.executeUpdate();

            System.out.println("Usuario actualizado");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Usuarios comprobarExisteUsuario(Usuarios usuario) {
        Connection conexion = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM Usuario where Usuario = ?";

        try {
            conexion = conn.getConnection();
            ps = conexion.prepareCall(sql);

            ps.setString(1, usuario.getUsuario());
            ResultSet rs = ps.executeQuery();
            if (rs.next() == false) {
                usuario.setExiste(false);
            } else {
                usuario.setExiste(true);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuario;
    }

}
