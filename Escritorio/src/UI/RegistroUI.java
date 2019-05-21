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
 * Página que permite el registro de usuarios
 * @author manue
 */
public class RegistroUI extends javax.swing.JFrame {

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

    public RegistroUI() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        kGradientPanel1 = new keeptoo.KGradientPanel();
        jPanel1 = new javax.swing.JPanel();
        registro_email_field = new javax.swing.JTextField();
        registro_salario_field = new javax.swing.JTextField();
        registro_nombre_field = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        registro_password_field = new javax.swing.JPasswordField();
        registrar_boton = new keeptoo.KGradientPanel();
        jLabel10 = new javax.swing.JLabel();
        registrar_inicioSesion_btn = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        kGradientPanel1.setkEndColor(new java.awt.Color(0, 51, 153));
        kGradientPanel1.setkGradientFocus(300);
        kGradientPanel1.setkStartColor(new java.awt.Color(51, 204, 255));
        kGradientPanel1.setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(102, 102, 102));

        registro_email_field.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        registro_email_field.setForeground(new java.awt.Color(102, 102, 102));
        registro_email_field.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));

        registro_salario_field.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        registro_salario_field.setForeground(new java.awt.Color(102, 102, 102));
        registro_salario_field.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));

        registro_nombre_field.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        registro_nombre_field.setForeground(new java.awt.Color(102, 102, 102));
        registro_nombre_field.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Registrate");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("usuario / email");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("salario");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("nombre");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("contraseña");

        registro_password_field.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        registro_password_field.setForeground(new java.awt.Color(102, 102, 102));
        registro_password_field.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));

        registrar_boton.setkStartColor(new java.awt.Color(0, 153, 153));
        registrar_boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                registrar_botonMouseClicked(evt);
            }
        });
        registrar_boton.setLayout(null);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("REGISTRARSE");
        registrar_boton.add(jLabel10);
        jLabel10.setBounds(90, 0, 180, 40);

        registrar_inicioSesion_btn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        registrar_inicioSesion_btn.setForeground(new java.awt.Color(102, 102, 102));
        registrar_inicioSesion_btn.setText("¿Tienes una cuenta ya creada? ¡Inicia Sesión aqui!");
        registrar_inicioSesion_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                registrar_inicioSesion_btnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel7)
                        .addComponent(jLabel8)
                        .addComponent(jLabel5)
                        .addComponent(registro_nombre_field, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                        .addComponent(registro_salario_field, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                        .addComponent(registro_email_field)
                        .addComponent(registro_password_field)
                        .addComponent(registrar_boton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(registrar_inicioSesion_btn, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel3)
                .addGap(32, 32, 32)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(registro_email_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(registro_password_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addGap(12, 12, 12)
                .addComponent(registro_nombre_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(registro_salario_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(registrar_boton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(registrar_inicioSesion_btn)
                .addGap(12, 12, 12))
        );

        kGradientPanel1.add(jPanel1);
        jPanel1.setBounds(90, 140, 400, 480);

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("GESTOR DE TAREAS Y FINANZAS");
        kGradientPanel1.add(jLabel4);
        jLabel4.setBounds(110, 80, 450, 40);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 2, 45)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Manage My Life");
        kGradientPanel1.add(jLabel11);
        jLabel11.setBounds(130, 10, 420, 61);

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

    private void registrar_inicioSesion_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registrar_inicioSesion_btnMouseClicked
        InicioSesionUI inicio_panel = new InicioSesionUI();
        inicio_panel.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_registrar_inicioSesion_btnMouseClicked
/**
 * Método que envía un objeto usuarios con los datos que ha escrito para registrarlo
 * @param evt Evento clic
 */
    private void registrar_botonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registrar_botonMouseClicked
        try {

            cliente = new Socket(server, puerto);
            salida = new ObjectOutputStream(cliente.getOutputStream());
            entrada = new ObjectInputStream(cliente.getInputStream());

            usuarios.setUsuario(registro_email_field.getText());
            usuarios.setNombre(registro_nombre_field.getText());
            usuarios.setSalario(Integer.valueOf(registro_salario_field.getText()));

            String contraseña = new String(registro_password_field.getPassword());
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] hash = digest.digest(contraseña.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
            }

            usuarios.setContraseña(sb.toString());

            // comprobar si existe el usuario
            peticion.setConsulta(18);
            salida.writeObject(peticion);
            salida.flush();
            salida.writeObject(usuarios);
            salida.flush();

            usuarios = (Usuarios) entrada.readObject();
            if (usuarios.isExiste()) {
                JOptionPane.showMessageDialog(this, "Ese usuario ya existe!");
                cliente.close();
                salida.close();
                entrada.close();
            } else {
                cliente = new Socket(server, puerto);
                salida = new ObjectOutputStream(cliente.getOutputStream());
                entrada = new ObjectInputStream(cliente.getInputStream());
                // Le envio la consulta al servidor
                peticion.setConsulta(1);
                salida.writeObject(peticion);
                // Le envio el objeto usuarios con los datos al servidor
                salida.writeObject(usuarios);

                // Envio al usuario a la pantalla de inicio de sesión
                InicioSesionUI inicio_panel = new InicioSesionUI();
                inicio_panel.setVisible(true);
                this.setVisible(false);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RegistroUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistroUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch(NumberFormatException nfe){
            JOptionPane.showMessageDialog(null, "Introduce carácteres válidos!");
        }
    }//GEN-LAST:event_registrar_botonMouseClicked


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
            java.util.logging.Logger.getLogger(RegistroUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new RegistroUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private keeptoo.KGradientPanel kGradientPanel1;
    private keeptoo.KGradientPanel registrar_boton;
    private javax.swing.JLabel registrar_inicioSesion_btn;
    private javax.swing.JTextField registro_email_field;
    private javax.swing.JTextField registro_nombre_field;
    private javax.swing.JPasswordField registro_password_field;
    private javax.swing.JTextField registro_salario_field;
    // End of variables declaration//GEN-END:variables
}
