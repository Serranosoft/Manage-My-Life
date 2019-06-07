package UI;

import Compartir.Gastos;
import Compartir.Peticion;
import Compartir.Productos;
import Compartir.Usuarios;
import Conexion.Conexion;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 * Interfaz para insertar productos
 *
 * @author manue
 */
public class insertarFondos extends javax.swing.JDialog {

    /**
     * Variables
     */
    final Conexion conexion = new Conexion();
    final String server = conexion.getServer();
    final int puerto = conexion.getPuerto();
    Socket cliente = null;
    ObjectOutputStream salida = null;
    ObjectInputStream entrada = null;
    Gastos gastos = new Gastos();
    Peticion peticion = new Peticion();
    Usuarios usuarios = new Usuarios();
    Productos productos = new Productos();
    double salario = 0.0;
    boolean añadido = false;
    public insertarFondos(java.awt.Frame parent, boolean modal, Usuarios usuarios) {
        super(parent, modal);
        initComponents();
        this.usuarios = usuarios;
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        fondos = new javax.swing.JTextField();
        producto_insertar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(0, 102, 153));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel1.setText("AGREGAR FONDOS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(96, 96, 96))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Fondos");

        producto_insertar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        producto_insertar.setText("OK");
        producto_insertar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                producto_insertarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fondos, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(61, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(producto_insertar)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fondos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(producto_insertar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
/**
     * Método para agregar fondos al balance
     *
     * @param evt Evento clic
     */
    private void producto_insertarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_producto_insertarMouseClicked

        try {

            if (Double.valueOf(fondos.getText()) < 0) {
                JOptionPane.showMessageDialog(null, "Introduce un numero positivo!");
            } else {
                obtenerInformacionPerfil(usuarios);
                cliente = new Socket(server, puerto);
                salida = new ObjectOutputStream(cliente.getOutputStream());
                entrada = new ObjectInputStream(cliente.getInputStream());
                peticion.setConsulta(21);
                salida.writeObject(peticion);
                salida.flush();
                salario = usuarios.getSalario();
                usuarios.setSalario(salario + Double.valueOf(fondos.getText()));
                salida.writeObject(usuarios);
                salida.flush();
                añadido = true;
                cerrarDialog();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Introduce carácteres válidos!");
        }
    }//GEN-LAST:event_producto_insertarMouseClicked
    /**
     * Método para cerrar el dialog
     *
     * @return Devuelve true/false si se ha cerrado
     */
    public boolean cerrarDialog() {
        this.setVisible(false);
        return true;
    }
    /**
     * Método para obtener los valores del usuario actual para rellenar el
     * balance actual en la página.
     *
     * @param usuarios Objeto usuarios con los valores del usuario actual
     */
    public void obtenerInformacionPerfil(Usuarios usuarios) {

        try {

            cliente = new Socket(server, puerto);
            salida = new ObjectOutputStream(cliente.getOutputStream());
            entrada = new ObjectInputStream(cliente.getInputStream());

            peticion.setConsulta(22);
            salida.writeObject(peticion);
            salida.flush();

            salida.writeObject(usuarios);
            salida.flush();
            this.usuarios = (Usuarios) entrada.readObject();
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
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
            java.util.logging.Logger.getLogger(insertarFondos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(insertarFondos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(insertarFondos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(insertarFondos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                insertarFondos dialog = new insertarFondos(new javax.swing.JFrame(), true, new Usuarios());
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
    private javax.swing.JTextField fondos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton producto_insertar;
    // End of variables declaration//GEN-END:variables
}
