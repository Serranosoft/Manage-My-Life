package UI;

import Compartir.Peticion;
import Compartir.Usuarios;
import Conexion.Conexion;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Base64;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Carga de componentes de JDialog de modificar perfil, métodos para envio y
 * recibo de imágenes entre cliente y servidor.
 *
 * @author manue
 */
public class modificarPerfil extends javax.swing.JDialog {

    /**
     * Variables
     */
    final Conexion conexion = new Conexion();
    final String server = conexion.getServer();
    final int puerto = conexion.getPuerto();
    Socket cliente = null;
    ObjectOutputStream salida = null;
    ObjectInputStream entrada = null;
    Peticion peticion = new Peticion();
    Usuarios usuarios = new Usuarios();
    ImageIcon photo = null;
    Image image = null;
    String path = "";
    InicioSesionUI inicioSesionUI = new InicioSesionUI();
    Frame parent = null;

    /**
     * Constructor para carga de componentes y configuración de busqueda de
     * archivos
     *
     * @param parent JFrame padre
     * @param modal modal
     * @param usuarios Objeto usuarios con los datos del usuario actual
     */
    public modificarPerfil(java.awt.Frame parent, boolean modal, Usuarios usuarios) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.usuarios = usuarios;
        cargaDatos();
        this.parent = parent;
        obtenerImagenPerfil(usuarios);
        buscar_imagen.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser file = new JFileChooser();
                file.setCurrentDirectory(new File(System.getProperty("user.home")));
                FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg");
                file.addChoosableFileFilter(filter);

                int result = file.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = file.getSelectedFile();
                    if (filter.accept(selectedFile)) {
                        path = selectedFile.getAbsolutePath();
                        image = ResizeImage(path);
                        obtenerImagenPerfil(selectedFile);
                    } else {
                        JOptionPane.showMessageDialog(null, "Solo se aceptan imagenes con formato .jpg");
                    }

                } else if (result == JFileChooser.CANCEL_OPTION) {
                }

            }
        });
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        usuario_nombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        usuario_usuario = new javax.swing.JTextField();
        modificar_perfil = new javax.swing.JPanel();
        label = new javax.swing.JLabel();
        perfil_imagen = new javax.swing.JLabel();
        buscar_imagen = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(0, 102, 153));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel1.setText("MODIFICAR PERFIL");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Nombre");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Usuario");

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
                .addGap(17, 17, 17)
                .addComponent(label)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        modificar_perfilLayout.setVerticalGroup(
            modificar_perfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modificar_perfilLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        perfil_imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/user.png"))); // NOI18N

        buscar_imagen.setText("Buscar");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel4.setText("Solo se aceptan imagenes con extension .jpg");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buscar_imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(perfil_imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(usuario_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(modificar_perfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(usuario_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(perfil_imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buscar_imagen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(usuario_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usuario_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(modificar_perfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void modificar_perfilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modificar_perfilMouseClicked
        try {

            if (usuario_nombre.getText().isEmpty() || usuario_usuario.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Rellena todos los campos!");
            } else {

                cliente = new Socket(server, puerto);
                salida = new ObjectOutputStream(cliente.getOutputStream());
                entrada = new ObjectInputStream(cliente.getInputStream());

                String imageString = null;
                ByteArrayOutputStream bos = new ByteArrayOutputStream();

                try {
                    if (path.isEmpty()) {

                    } else {
                        byte[] imageBytes = Files.readAllBytes(new File(path).toPath());
                        System.out.println("TAMAÑO DEL ARRAY DE BYTES: " + imageBytes.length);
                        imageString = Base64.getEncoder().encodeToString(imageBytes);
                        System.out.println("IMAGESTRING LENGTH: " + imageString.length());
                        bos.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

                usuarios.setNombre(usuario_nombre.getText());
                usuarios.setUsuario(usuario_usuario.getText());
                usuarios.setImagen(imageString);
                peticion.setConsulta(11);
                salida.writeObject(peticion);
                salida.flush();
                salida.writeObject(usuarios);
                salida.flush();
                JOptionPane.showMessageDialog(null, "Para aplicar los cambios debes iniciar sesión de nuevo");
                parent.setVisible(false);
                inicioSesionUI.setVisible(true);
                this.setVisible(false);

            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Introduce carácteres válidos!");
        } finally {
            try {
                cliente.close();
                salida.close();
                entrada.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_modificar_perfilMouseClicked

    /**
     * Método para cargar la imágen del usuario (carga una imagen user si no ha
     * indicado ninguna imágen de perfil)
     * @param usuarios Objeto usuarios con los datos del usuario actual
     */
    public void obtenerImagenPerfil(Usuarios usuarios) {
        try {
            System.out.println(usuarios.getImagen().length());
            if (usuarios.getImagen().equals("null") || usuarios.getImagen().length() == 0) {

            } else {
                byte[] imageByte = Base64.getDecoder().decode(usuarios.getImagen());
                ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
                Image imagen = ImageIO.read(bis);
                ImageIcon image_perfil = new ImageIcon(imagen);
                perfil_imagen.setIcon(image_perfil);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
/**
 * Método para cargar los distintos datos en pantalla (nombre del usuario y usuario de acceso del usuario)
 */
    public void cargaDatos() {
        usuario_nombre.setText(usuarios.getNombre());
        usuario_usuario.setText(usuarios.getUsuario());
    }
/**
 * Método para convertir imagen y adaptarlo al label de la pantalla
 * @param ImagePath ruta de la imágen
 * @return Imagen del perfil
 */
    public Image ResizeImage(String ImagePath) {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(perfil_imagen.getWidth(), perfil_imagen.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return newImg;
    }
/**
 * Método para obtener la imagen del perfil en icono y cargarlo en pantalla
 * @param ruta ruta de la imagen
 */
    public void obtenerImagenPerfil(File ruta) {
        try {
            byte[] imageByte = Files.readAllBytes(ruta.toPath());
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            Image imagen = ImageIO.read(bis);
            ImageIcon image_perfil = new ImageIcon(imagen);
            perfil_imagen.setIcon(image_perfil);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel label;
    private javax.swing.JPanel modificar_perfil;
    private javax.swing.JLabel perfil_imagen;
    private javax.swing.JTextField usuario_nombre;
    private javax.swing.JTextField usuario_usuario;
    // End of variables declaration//GEN-END:variables
}
