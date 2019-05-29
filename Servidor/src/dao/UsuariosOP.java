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
 * Operaciones referente a los usuarios
 * @author manue
 */
public class UsuariosOP {

    DBConnection conn = new DBConnection();
/**
 * Método para insertar un usuario en el sistema
 * @param usuario Objeto usuarios con los valores del usuario a insertar
 */
    public void insertarUsuario(Usuarios usuario) {

        Connection conexion = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO Usuario (Usuario, Nombre, Salario, Contraseña) VALUES (?,?,?,?)";

        try {
            conexion = conn.getConnection();
            ps = conexion.prepareStatement(sql);

            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getNombre());
            ps.setDouble(3, usuario.getSalario());
            ps.setString(4, usuario.getContraseña());

            ps.executeUpdate();

            System.out.println("Usuario introducido");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
/**
 * Método para comprobar si un usuario existe, si existe devuelve sus datos
 * @param usuario Objeto usuarios con los valores del usuario que intenta acceder al sistema
 * @return Devuelve si existe o no, si existe devuelve sus datos.
 */
    public Usuarios comprobarUsuario(Usuarios usuario) {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String imageString = null;
        String sql = "SELECT * FROM Usuario where Usuario = ? AND Contraseña = ?";

        try {
            conexion = conn.getConnection();
            ps = conexion.prepareStatement(sql);

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
                usuario.setSalario(Double.valueOf(rs.getString("Salario")));
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
/**
 * Método para modificar los datos del usuario
 * @param usuario Objeto usuarios con los datos a actualizar
 */
    public void modificarUsuario(Usuarios usuario) {

        Connection conexion = null;
        PreparedStatement ps = null;

        String sql = "UPDATE Usuario SET Usuario = ?, Nombre = ?, Imagen = ? WHERE ID = ?";

        try {
            conexion = conn.getConnection();
            ps = conexion.prepareStatement(sql);

            byte[] imageByte;
            BufferedImage image = null;
            try {
                if (usuario.getImagen() == null) {

                } else {
                    imageByte = Base64.getDecoder().decode(usuario.getImagen());
                    ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
                    image = ImageIO.read(bis);
                    ImageIO.write(image, "jpg", new File("media/" + usuario.getId() + "_profile.jpg"));
                    bis.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            usuario.setImagen("media/" + usuario.getId() + "_profile.jpg");
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getImagen());
            ps.setInt(4, usuario.getId());

            ps.executeUpdate();

            System.out.println("Usuario actualizado");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
/**
 * Método para comprobar si existe un usuario
 * @param usuario Objeto usuario con los valores del usuario que intenta acceder al sistema
 * @return Devuelve si existe o no
 */
    public Usuarios comprobarExisteUsuario(Usuarios usuario) {
        Connection conexion = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM Usuario where Usuario = ?";

        try {
            conexion = conn.getConnection();
            ps = conexion.prepareStatement(sql);

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
/**
 * Método para actualizar el salario del usuario
 * @param usuario Objeto usuarios con el usuario que hay que actualizar el salario
 */
    public void actualizarSalario(Usuarios usuario) {
        Connection conexion = null;
        PreparedStatement ps = null;
        String sql = "UPDATE Usuario SET Salario = ? WHERE ID = ?";

        try {
            conexion = conn.getConnection();
            ps = conexion.prepareStatement(sql);

            ps.setDouble(1, usuario.getSalario());
            ps.setInt(2, usuario.getId());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Salario actualizado");
    }
/**
 * Método para obtener toda la información referente a un usuario
 * @param usuario Objeto usuario con los valores del usuario que se quiere extraer los datos
 * @return Devuelve toda la información del usuario
 */
    public Usuarios obtenerInformacion(Usuarios usuario) {
        Connection conexion = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM Usuario WHERE ID = ?";
        ResultSet rs = null;
        try {
            conexion = conn.getConnection();
            ps = conexion.prepareStatement(sql);

            ps.setInt(1, usuario.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                usuario.setId(Integer.valueOf(rs.getString("ID")));
                usuario.setUsuario(rs.getString("Usuario"));
                usuario.setNombre(rs.getString("Nombre"));
                usuario.setSalario(Double.valueOf(rs.getString("Salario")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Obtener informacion usuario");
        return usuario;
    }

}
