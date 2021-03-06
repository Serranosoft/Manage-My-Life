package UI;

import Compartir.Peticion;
import Compartir.Usuarios;
import Conexion.Conexion;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Carga de componentes de la interfaz de inicio de sesión, cifrado de
 * contrasenas y comprobación de usuarios
 *
 * @author manue
 */
public class InicioSesionUI extends javax.swing.JFrame {

    /**
     * Variables
     */
    final Conexion conexion = new Conexion();
    final String server = conexion.getServer();
    final int puerto = conexion.getPuerto();
    Socket cliente = null;
    ObjectOutputStream salida = null;
    ObjectInputStream entrada = null;
    Usuarios usuarios = new Usuarios();
    Peticion peticion = new Peticion();
/**
 * Constructor para cargar los distintos componentes
 */
    public InicioSesionUI() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        jPanel1 = new javax.swing.JPanel();
        inicio_email_field = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        inicio_password_field = new javax.swing.JPasswordField();
        inicio_registrarse_btn = new keeptoo.KGradientPanel();
        jLabel10 = new javax.swing.JLabel();
        inicio_iniciarSesion_btn = new keeptoo.KGradientPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        kGradientPanel1.setkEndColor(new java.awt.Color(0, 51, 153));
        kGradientPanel1.setkGradientFocus(300);
        kGradientPanel1.setkStartColor(new java.awt.Color(51, 204, 255));
        kGradientPanel1.setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(102, 102, 102));

        inicio_email_field.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        inicio_email_field.setForeground(new java.awt.Color(51, 51, 255));
        inicio_email_field.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        inicio_email_field.setCaretColor(new java.awt.Color(51, 51, 255));
        inicio_email_field.setDisabledTextColor(new java.awt.Color(255, 0, 0));
        inicio_email_field.setSelectedTextColor(new java.awt.Color(255, 51, 0));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Iniciar Sesión");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("usuario");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("contraseña");

        inicio_password_field.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        inicio_password_field.setForeground(new java.awt.Color(102, 102, 102));
        inicio_password_field.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        inicio_password_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inicio_password_fieldActionPerformed(evt);
            }
        });

        inicio_registrarse_btn.setkStartColor(new java.awt.Color(0, 153, 153));
        inicio_registrarse_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inicio_registrarse_btnMouseClicked(evt);
            }
        });
        inicio_registrarse_btn.setLayout(null);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("REGISTRARSE");
        inicio_registrarse_btn.add(jLabel10);
        jLabel10.setBounds(90, 0, 140, 40);

        inicio_iniciarSesion_btn.setkStartColor(new java.awt.Color(0, 153, 153));
        inicio_iniciarSesion_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inicio_iniciarSesion_btnMouseClicked(evt);
            }
        });
        inicio_iniciarSesion_btn.setLayout(null);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("INICIAR SESIÓN");
        inicio_iniciarSesion_btn.add(jLabel13);
        jLabel13.setBounds(80, 0, 150, 40);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(54, 54, 54)
                            .addComponent(jLabel3))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(44, 44, 44)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(inicio_registrarse_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(inicio_iniciarSesion_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(60, 60, 60)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7)
                                .addComponent(inicio_email_field, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel8)
                        .addComponent(inicio_password_field, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel3)
                .addGap(29, 29, 29)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(inicio_email_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(inicio_password_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(inicio_iniciarSesion_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(inicio_registrarse_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        kGradientPanel1.add(jPanel1);
        jPanel1.setBounds(90, 170, 400, 400);

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("GESTOR DE TAREAS Y FINANZAS");
        kGradientPanel1.add(jLabel4);
        jLabel4.setBounds(80, 100, 490, 40);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 2, 45)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Manage My Life");
        kGradientPanel1.add(jLabel11);
        jLabel11.setBounds(110, 40, 400, 60);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inicio_password_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inicio_password_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inicio_password_fieldActionPerformed

    private void inicio_registrarse_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inicio_registrarse_btnMouseClicked
        RegistroUI registro_panel = new RegistroUI();
        registro_panel.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_inicio_registrarse_btnMouseClicked
    /**
     * Método para iniciar sesión descifrando la contrasena
     * @param evt evento clic
     */
    private void inicio_iniciarSesion_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inicio_iniciarSesion_btnMouseClicked
        try {

            cliente = new Socket(server, puerto);
            salida = new ObjectOutputStream(cliente.getOutputStream());
            entrada = new ObjectInputStream(cliente.getInputStream());

            String usuario = inicio_email_field.getText();
            String password = inicio_password_field.getText();
            // Cifrado de contrasena a SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
            }

            peticion.setConsulta(2);
            salida.writeObject(peticion);
            salida.flush();
            usuarios.setUsuario(usuario);
            usuarios.setContraseña(sb.toString());
            salida.writeObject(usuarios);
            salida.flush();
            usuarios = (Usuarios) entrada.readObject();

            if (usuarios.isExiste()) {
                PerfilUI perfilUI = new PerfilUI(usuarios);
                perfilUI.setVisible(true);
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Usuario / Contraseña incorrecta");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(InicioSesionUI.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                cliente.close();
                salida.close();
                entrada.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }//GEN-LAST:event_inicio_iniciarSesion_btnMouseClicked

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
            java.util.logging.Logger.getLogger(InicioSesionUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InicioSesionUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InicioSesionUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InicioSesionUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InicioSesionUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField inicio_email_field;
    private keeptoo.KGradientPanel inicio_iniciarSesion_btn;
    private javax.swing.JPasswordField inicio_password_field;
    private keeptoo.KGradientPanel inicio_registrarse_btn;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private keeptoo.KGradientPanel kGradientPanel1;
    // End of variables declaration//GEN-END:variables
}
