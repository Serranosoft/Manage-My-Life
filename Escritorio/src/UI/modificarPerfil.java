package UI;

import Compartir.Peticion;
import Compartir.Tareas;
import Compartir.Usuarios;
import Conexion.Conexion;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Date;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author manue
 */
public class modificarPerfil extends javax.swing.JDialog {

    /**
     * Creates new form insertarTarea
     */
    final Conexion conexion = new Conexion();
    final String server = conexion.getServer();
    Socket cliente = null;
    ObjectOutputStream salida = null;
    ObjectInputStream entrada = null;
    Peticion peticion = new Peticion();
    Usuarios usuarios = new Usuarios();
    ImageIcon photo = null;

    public modificarPerfil(java.awt.Frame parent, boolean modal, Usuarios usuarios) {
        super(parent, modal);
        initComponents();
        try {
            cliente = new Socket(server, 4444);
            salida = new ObjectOutputStream(cliente.getOutputStream());
            entrada = new ObjectInputStream(cliente.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.setLocationRelativeTo(null);
        this.usuarios = usuarios;
        cargaDatos();
        buscar_imagen.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser file = new JFileChooser();
                file.setCurrentDirectory(new File(System.getProperty("user.home")));
                FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "png");
                file.addChoosableFileFilter(filter);
                int result = file.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = file.getSelectedFile();
                    String path = selectedFile.getAbsolutePath();
                    photo = ResizeImage(path);
                    imagen_perfil.setIcon(ResizeImage(path));
                } else if (result == JFileChooser.CANCEL_OPTION) {
                    System.out.println("No se ha seleccionado ninguna imágen");
                }

            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        usuario_nombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        usuario_usuario = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        modificar_perfil = new javax.swing.JPanel();
        label = new javax.swing.JLabel();
        usuario_salario = new javax.swing.JTextField();
        imagen_perfil = new javax.swing.JLabel();
        buscar_imagen = new javax.swing.JButton();
        prueba = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(0, 102, 153));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("MODIFICAR PERFIL");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(48, 48, 48))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setText("Nombre");

        jLabel3.setText("Usuario");

        jLabel5.setText("Salario");

        modificar_perfil.setBackground(new java.awt.Color(0, 102, 153));
        modificar_perfil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modificar_perfilMouseClicked(evt);
            }
        });

        label.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        label.setText("APLICAR CAMBIOS");

        javax.swing.GroupLayout modificar_perfilLayout = new javax.swing.GroupLayout(modificar_perfil);
        modificar_perfil.setLayout(modificar_perfilLayout);
        modificar_perfilLayout.setHorizontalGroup(
            modificar_perfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modificar_perfilLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(label)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        modificar_perfilLayout.setVerticalGroup(
            modificar_perfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modificar_perfilLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label)
                .addContainerGap())
        );

        imagen_perfil.setIcon(new javax.swing.ImageIcon("F:\\ManuelSerranoScholz\\AndroidStudio\\ProyectoFinal\\Escritorio\\src\\imagenes\\user.png")); // NOI18N

        buscar_imagen.setText("Buscar");

        prueba.setText("jLabel4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(modificar_perfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(usuario_salario, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                            .addComponent(usuario_usuario)
                            .addComponent(usuario_nombre))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(prueba)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(buscar_imagen, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                            .addComponent(imagen_perfil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(60, 60, 60))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(imagen_perfil, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buscar_imagen)
                .addGap(67, 67, 67)
                .addComponent(prueba)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usuario_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usuario_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(usuario_salario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(modificar_perfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void modificar_perfilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modificar_perfilMouseClicked
        try {
            
            usuarios.setNombre(usuario_nombre.getText());
            usuarios.setUsuario(usuario_usuario.getText());
            usuarios.setSalario(Integer.valueOf(usuario_salario.getText()));
            
            if(photo != null) {
                BufferedImage image = new BufferedImage(photo.getIconWidth(),
                photo.getIconHeight(),BufferedImage.TYPE_INT_RGB);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ImageIO.write(image, "jpg", bos);
                byte[] data = bos.toByteArray();
            }
            
            
            //usuarios.setImagen(data);
            peticion.setConsulta(11);
            salida.writeObject(peticion);
            salida.writeObject(usuarios);
            this.setVisible(false);
            
            // enviar array de bytes al servidor con la demás informacion del usuario
            
            
            // prueba para leer array y convertir a imagen
            /*ByteArrayInputStream bis = new ByteArrayInputStream(data);
            BufferedImage bImage2 = ImageIO.read(bis);
            
            ImageIcon prueba2 = new ImageIcon(bImage2);
            
            prueba.setIcon(prueba2);*/
        } catch (IOException ex) {
            Logger.getLogger(modificarPerfil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_modificar_perfilMouseClicked

    public void cargaDatos() {
        usuario_nombre.setText(usuarios.getNombre());
        usuario_usuario.setText(usuarios.getUsuario());
        usuario_salario.setText(usuarios.getSalario()+"");
        
        /*try {
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(usuarios.getImagen()));
            ImageIcon p1 = new ImageIcon(img);
            prueba.setIcon(p1);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }*/

    }
    public ImageIcon ResizeImage(String ImagePath) {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(imagen_perfil.getWidth(), imagen_perfil.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(modificarPerfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(modificarPerfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(modificarPerfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(modificarPerfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                modificarPerfil dialog = new modificarPerfil(new javax.swing.JFrame(), true, new Usuarios());
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscar_imagen;
    private javax.swing.JLabel imagen_perfil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel label;
    private javax.swing.JPanel modificar_perfil;
    private javax.swing.JLabel prueba;
    private javax.swing.JTextField usuario_nombre;
    private javax.swing.JTextField usuario_salario;
    private javax.swing.JTextField usuario_usuario;
    // End of variables declaration//GEN-END:variables
}