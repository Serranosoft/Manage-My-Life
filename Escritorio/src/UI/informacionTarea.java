package UI;

import Compartir.Peticion;
import Compartir.Tareas;
import Compartir.Usuarios;
import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author manue
 */
public class informacionTarea extends javax.swing.JDialog {

    /**
     * Creates new form insertarGasto
     */
    final String server = "192.168.0.158";
    Socket cliente = null;
    ObjectOutputStream salida = null;
    ObjectInputStream entrada = null;
    Tareas tareas = new Tareas();
    Peticion peticion = new Peticion();
    Usuarios usuarios = new Usuarios();
    boolean edicion = false;

    public informacionTarea(java.awt.Frame parent, boolean modal, int id) {
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

        obtenerTarea(id);

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
        descripcion_tarea = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        salir = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        nombre_tarea = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_subtareas = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        categoria_tarea = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        fecha_inscrita_tarea = new javax.swing.JTextField();
        fecha_terminar_tarea = new javax.swing.JTextField();
        prioritaria_tarea = new javax.swing.JCheckBox();
        estado_tarea = new javax.swing.JCheckBox();
        activar_edicion = new javax.swing.JLabel();
        modificar_informacion_tarea = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(0, 102, 153));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("INFORMACION TAREAS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(287, 287, 287)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jLabel2.setText("Nombre");

        descripcion_tarea.setEditable(false);

        jLabel5.setText("Categoria");

        salir.setBackground(new java.awt.Color(0, 102, 153));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setText("SALIR");

        javax.swing.GroupLayout salirLayout = new javax.swing.GroupLayout(salir);
        salir.setLayout(salirLayout);
        salirLayout.setHorizontalGroup(
            salirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, salirLayout.createSequentialGroup()
                .addContainerGap(122, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(115, 115, 115))
        );
        salirLayout.setVerticalGroup(
            salirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(salirLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        nombre_tarea.setEditable(false);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        tabla_subtareas.setBackground(new java.awt.Color(187, 187, 187));
        tabla_subtareas.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tabla_subtareas.setForeground(new java.awt.Color(0, 0, 0));
        tabla_subtareas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Subtarea1",  new Boolean(true)},
                {"Subtarea2", null}
            },
            new String [] {
                "Subtarea", "Terminada"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_subtareas.setGridColor(new java.awt.Color(102, 102, 102));
        tabla_subtareas.setRowHeight(22);
        tabla_subtareas.setSelectionBackground(new java.awt.Color(51, 102, 255));
        tabla_subtareas.setSelectionForeground(new java.awt.Color(0, 0, 102));
        jScrollPane1.setViewportView(tabla_subtareas);
        if (tabla_subtareas.getColumnModel().getColumnCount() > 0) {
            tabla_subtareas.getColumnModel().getColumn(0).setResizable(false);
            tabla_subtareas.getColumnModel().getColumn(1).setResizable(false);
            tabla_subtareas.getColumnModel().getColumn(1).setPreferredWidth(100);
        }

        jLabel7.setText("Descripción");

        categoria_tarea.setEditable(false);

        jLabel8.setText("Fecha Inscrita");

        jLabel9.setText("Fecha a terminar");

        fecha_inscrita_tarea.setEditable(false);

        fecha_terminar_tarea.setEditable(false);

        prioritaria_tarea.setText("Prioritaria");
        prioritaria_tarea.setEnabled(false);
        prioritaria_tarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prioritaria_tareaActionPerformed(evt);
            }
        });

        estado_tarea.setText("Terminado");
        estado_tarea.setEnabled(false);
        estado_tarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estado_tareaActionPerformed(evt);
            }
        });

        activar_edicion.setIcon(new javax.swing.ImageIcon("F:\\ManuelSerranoScholz\\AndroidStudio\\ProyectoFinal\\Escritorio\\imagenes\\edit.png")); // NOI18N
        activar_edicion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                activar_edicionMouseClicked(evt);
            }
        });

        modificar_informacion_tarea.setBackground(new java.awt.Color(94, 104, 109));
        modificar_informacion_tarea.setEnabled(false);

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setText("APLICAR CAMBIOS");

        javax.swing.GroupLayout modificar_informacion_tareaLayout = new javax.swing.GroupLayout(modificar_informacion_tarea);
        modificar_informacion_tarea.setLayout(modificar_informacion_tareaLayout);
        modificar_informacion_tareaLayout.setHorizontalGroup(
            modificar_informacion_tareaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modificar_informacion_tareaLayout.createSequentialGroup()
                .addContainerGap(66, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(55, 55, 55))
        );
        modificar_informacion_tareaLayout.setVerticalGroup(
            modificar_informacion_tareaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modificar_informacion_tareaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(fecha_terminar_tarea, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(7, 7, 7))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(nombre_tarea, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(activar_edicion)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fecha_inscrita_tarea, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(categoria_tarea, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(descripcion_tarea, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGap(26, 26, 26)
                                            .addComponent(jLabel8)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel9)
                                            .addGap(19, 19, 19)))
                                    .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)
                                    .addComponent(modificar_informacion_tarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(prioritaria_tarea)
                            .addComponent(estado_tarea))
                        .addGap(142, 142, 142)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel2))
                            .addComponent(activar_edicion))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nombre_tarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(categoria_tarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(descripcion_tarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fecha_inscrita_tarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fecha_terminar_tarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(estado_tarea)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(prioritaria_tarea)
                        .addGap(18, 18, 18)
                        .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(modificar_informacion_tarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void prioritaria_tareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prioritaria_tareaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prioritaria_tareaActionPerformed

    private void estado_tareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estado_tareaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_estado_tareaActionPerformed

    private void activar_edicionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_activar_edicionMouseClicked

        if (!edicion) {
            edicion = true;
            modificar_informacion_tarea.setBackground(new Color(0, 102, 153));
            nombre_tarea.setEditable(true);
            categoria_tarea.setEditable(true);
            descripcion_tarea.setEditable(true);
            estado_tarea.setEnabled(true);
            prioritaria_tarea.setEnabled(true);
            System.out.println("Edicion activada");
        }else{
            edicion = false;
            modificar_informacion_tarea.setBackground(new Color(94,104,109));
            nombre_tarea.setEditable(false);
            categoria_tarea.setEditable(false);
            descripcion_tarea.setEditable(false);
            estado_tarea.setEnabled(false);
            prioritaria_tarea.setEnabled(false);
            System.out.println("Edicion desactivada");
        }

    }//GEN-LAST:event_activar_edicionMouseClicked

    private void obtenerTarea(int id) {

        try {
            System.out.println("Envio la peticion");
            peticion.setConsulta(5);
            salida.writeObject(peticion);
            tareas.setId(id);
            salida.writeObject(tareas);
            tareas = (Tareas) entrada.readObject();

            nombre_tarea.setText(tareas.getNombre());
            categoria_tarea.setText(tareas.getCategoria());
            descripcion_tarea.setText(tareas.getDescripcion());
            fecha_inscrita_tarea.setText(tareas.getFecha_inscrita().toString());
            fecha_terminar_tarea.setText(tareas.getFecha_realizar().toString());
            if (tareas.getEstado() == 1) {
                estado_tarea.setSelected(true);
            }
            if (tareas.getPrioritario() == 1) {
                prioritaria_tarea.setSelected(true);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
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
            java.util.logging.Logger.getLogger(informacionTarea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(informacionTarea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(informacionTarea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(informacionTarea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                informacionTarea dialog = new informacionTarea(new javax.swing.JFrame(), true, 0);
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
    private javax.swing.JLabel activar_edicion;
    private javax.swing.JTextField categoria_tarea;
    private javax.swing.JTextField descripcion_tarea;
    private javax.swing.JCheckBox estado_tarea;
    private javax.swing.JTextField fecha_inscrita_tarea;
    private javax.swing.JTextField fecha_terminar_tarea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel modificar_informacion_tarea;
    private javax.swing.JTextField nombre_tarea;
    private javax.swing.JCheckBox prioritaria_tarea;
    private javax.swing.JPanel salir;
    private javax.swing.JTable tabla_subtareas;
    // End of variables declaration//GEN-END:variables

}
