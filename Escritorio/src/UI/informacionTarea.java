package UI;

import Compartir.Peticion;
import Compartir.Subtareas;
import Compartir.Tareas;
import Compartir.Usuarios;
import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Date;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import vo.Subtarea;
import vo.Tarea;

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
    Subtareas subtareas = new Subtareas();
    boolean edicion = false;
    int id = 0;

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
        obtenerSubtareas(id);
        this.id = id;

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
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        fecha_inscrita_tarea = new javax.swing.JTextField();
        prioritaria_tarea = new javax.swing.JCheckBox();
        estado_tarea = new javax.swing.JCheckBox();
        activar_edicion = new javax.swing.JLabel();
        modificar_informacion_tarea = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        añadir_subtarea = new javax.swing.JButton();
        editar_subtarea = new javax.swing.JButton();
        categoria_tarea = new javax.swing.JComboBox<>();
        fecha_realizar_tarea = new com.github.lgooddatepicker.components.DatePicker();
        eliminar_tarea = new javax.swing.JLabel();
        eliminar_subtarea = new javax.swing.JButton();
        actualizar_tabla = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(0, 102, 153));

        jLabel1.setText("INFORMACION TAREAS");
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(315, 315, 315))
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

        jLabel6.setText("SALIR");
        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        javax.swing.GroupLayout salirLayout = new javax.swing.GroupLayout(salir);
        salir.setLayout(salirLayout);
        salirLayout.setHorizontalGroup(
            salirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, salirLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

            },
            new String [] {
                "Subtarea", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
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
            tabla_subtareas.getColumnModel().getColumn(0).setPreferredWidth(140);
            tabla_subtareas.getColumnModel().getColumn(1).setPreferredWidth(0);
        }

        jLabel7.setText("Descripción");

        jLabel8.setText("Fecha Terminar");

        jLabel9.setText("Fecha inscrita");

        fecha_inscrita_tarea.setEditable(false);

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
        modificar_informacion_tarea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modificar_informacion_tareaMouseClicked(evt);
            }
        });

        jLabel10.setText("APLICAR CAMBIOS");
        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

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

        añadir_subtarea.setIcon(new javax.swing.ImageIcon("F:\\ManuelSerranoScholz\\AndroidStudio\\ProyectoFinal\\Escritorio\\imagenes\\plus-black-symbol.png")); // NOI18N
        añadir_subtarea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                añadir_subtareaMouseClicked(evt);
            }
        });

        editar_subtarea.setIcon(new javax.swing.ImageIcon("F:\\ManuelSerranoScholz\\AndroidStudio\\ProyectoFinal\\Escritorio\\imagenes\\pencil-edit-button.png")); // NOI18N

        categoria_tarea.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ocio", "Trabajo", "Estudios", "Otros" }));
        categoria_tarea.setEnabled(false);

        fecha_realizar_tarea.setEnabled(false);

        eliminar_tarea.setIcon(new javax.swing.ImageIcon("F:\\ManuelSerranoScholz\\AndroidStudio\\ProyectoFinal\\Escritorio\\imagenes\\rubbish-bin.png")); // NOI18N
        eliminar_tarea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eliminar_tareaMouseClicked(evt);
            }
        });

        eliminar_subtarea.setIcon(new javax.swing.ImageIcon("F:\\ManuelSerranoScholz\\AndroidStudio\\ProyectoFinal\\Escritorio\\imagenes\\delete.png")); // NOI18N
        eliminar_subtarea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eliminar_subtareaMouseClicked(evt);
            }
        });

        actualizar_tabla.setIcon(new javax.swing.ImageIcon("F:\\ManuelSerranoScholz\\AndroidStudio\\ProyectoFinal\\Escritorio\\imagenes\\reload (2).png")); // NOI18N
        actualizar_tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                actualizar_tablaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(salir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(modificar_informacion_tarea, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(0, 1, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fecha_realizar_tarea, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(fecha_inscrita_tarea, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(descripcion_tarea, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(nombre_tarea, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel2)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(eliminar_tarea)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(activar_edicion))))
                                        .addComponent(categoria_tarea, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(prioritaria_tarea)
                            .addComponent(estado_tarea))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(eliminar_subtarea, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(editar_subtarea, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(añadir_subtarea, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(actualizar_tabla, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(añadir_subtarea)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editar_subtarea)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eliminar_subtarea)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(actualizar_tabla))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addComponent(jLabel2))
                                    .addComponent(activar_edicion)
                                    .addComponent(eliminar_tarea))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(nombre_tarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(categoria_tarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(descripcion_tarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fecha_inscrita_tarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fecha_realizar_tarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(estado_tarea)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(prioritaria_tarea)
                                .addGap(18, 18, 18)
                                .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(modificar_informacion_tarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            categoria_tarea.setEnabled(true);
            descripcion_tarea.setEnabled(true);
            estado_tarea.setEnabled(true);
            prioritaria_tarea.setEnabled(true);
            fecha_realizar_tarea.setEnabled(true);
            fecha_realizar_tarea.setDateToToday();
            

            System.out.println("Edicion activada");
        } else {
            edicion = false;
            modificar_informacion_tarea.setBackground(new Color(94, 104, 109));
            nombre_tarea.setEditable(false);
            categoria_tarea.setEnabled(false);
            descripcion_tarea.setEditable(false);
            estado_tarea.setEnabled(false);
            prioritaria_tarea.setEnabled(false);
            fecha_realizar_tarea.setEnabled(false);
            System.out.println("Edicion desactivada");
        }

    }//GEN-LAST:event_activar_edicionMouseClicked

    private void modificar_informacion_tareaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modificar_informacion_tareaMouseClicked

        try {
            cliente = new Socket(server, 4444);
            salida = new ObjectOutputStream(cliente.getOutputStream());
            entrada = new ObjectInputStream(cliente.getInputStream());

            if (estado_tarea.isSelected()) {
                tareas.setEstado(1);
            } else {
                tareas.setEstado(0);
            }
            if (prioritaria_tarea.isSelected()) {
                tareas.setPrioritario(1);
            } else {
                tareas.setPrioritario(0);
            }
            tareas.setNombre(nombre_tarea.getText());
            tareas.setCategoria(categoria_tarea.getSelectedItem().toString());
            tareas.setDescripcion(descripcion_tarea.getText());
            Date terminar = java.sql.Date.valueOf(fecha_realizar_tarea.getDate());
            tareas.setFecha_realizar(terminar);

            peticion.setConsulta(7);
            System.out.println("Envio peticion");
            salida.writeObject(peticion);
            System.out.println("Envio tareas");
            salida.writeObject(tareas);
            this.setVisible(false);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_modificar_informacion_tareaMouseClicked

    private void eliminar_tareaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eliminar_tareaMouseClicked
        try {
            cliente = new Socket(server, 4444);
            salida = new ObjectOutputStream(cliente.getOutputStream());
            entrada = new ObjectInputStream(cliente.getInputStream());

            peticion.setConsulta(6);
            System.out.println("Envio peticion");
            salida.writeObject(peticion);
            System.out.println("Envio tareas");
            salida.writeObject(tareas);
            this.setVisible(false);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_eliminar_tareaMouseClicked

    private void añadir_subtareaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_añadir_subtareaMouseClicked

        insertarSubtarea inSubtarea = new insertarSubtarea(this, false, tareas);
        inSubtarea.setVisible(true);
        //obtenerSubtareas(id);

    }//GEN-LAST:event_añadir_subtareaMouseClicked

    private void eliminar_subtareaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eliminar_subtareaMouseClicked
        try {
            cliente = new Socket(server, 4444);
            salida = new ObjectOutputStream(cliente.getOutputStream());
            entrada = new ObjectInputStream(cliente.getInputStream());

            int id = tabla_subtareas.getSelectedRow();
            subtareas.setId(tareas.getSubtareas().get(id).getId());
            peticion.setConsulta(10);
            System.out.println("Envio peticion");
            salida.writeObject(peticion);
            salida.flush();
            System.out.println("Envio subtareas");
            salida.writeObject(subtareas);
            salida.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                cliente.close();
                salida.close();
                entrada.close();
                obtenerSubtareas(this.id);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_eliminar_subtareaMouseClicked

    private void actualizar_tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_actualizar_tablaMouseClicked
        obtenerSubtareas(id);
    }//GEN-LAST:event_actualizar_tablaMouseClicked

    private void obtenerTarea(int id) {

        try {
            System.out.println("Envio la peticion");
            peticion.setConsulta(5);
            salida.writeObject(peticion);
            tareas.setId(id);
            salida.writeObject(tareas);
            tareas = (Tareas) entrada.readObject();

            nombre_tarea.setText(tareas.getNombre());
            categoria_tarea.setSelectedItem(tareas.getCategoria());
            descripcion_tarea.setText(tareas.getDescripcion());
            fecha_inscrita_tarea.setText(tareas.getFecha_inscrita().toString());
            fecha_realizar_tarea.setText(tareas.getFecha_realizar().toString());
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
    DefaultTableModel m;

    private void obtenerSubtareas(int id) {
        m = (DefaultTableModel) tabla_subtareas.getModel();
        m.setRowCount(0);

        try {
            cliente = new Socket(server, 4444);
            salida = new ObjectOutputStream(cliente.getOutputStream());
            entrada = new ObjectInputStream(cliente.getInputStream());

            System.out.println("Envio la peticion");
            peticion.setConsulta(8);
            salida.writeObject(peticion);
            salida.flush();
            tareas.setId(id);
            salida.writeObject(tareas);
            salida.flush();
            tareas = (Tareas) entrada.readObject();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            try {
                cliente.close();
                salida.close();
                entrada.close();

                String estado = "Pendiente";
                ArrayList<Subtarea> listado_subtareas = tareas.getSubtareas();
                for (int i = 0; i < listado_subtareas.size(); i++) {
                    Subtarea subtarea = listado_subtareas.get(i);
                    if (subtarea.getEstado() == 1) {
                        estado = "Terminado";
                    }
                    
                    Object[] array = {subtarea.getNombre(), estado};
                    m.addRow(array);

                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
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
    private javax.swing.JButton actualizar_tabla;
    private javax.swing.JButton añadir_subtarea;
    private javax.swing.JComboBox<String> categoria_tarea;
    private javax.swing.JTextField descripcion_tarea;
    private javax.swing.JButton editar_subtarea;
    private javax.swing.JButton eliminar_subtarea;
    private javax.swing.JLabel eliminar_tarea;
    private javax.swing.JCheckBox estado_tarea;
    private javax.swing.JTextField fecha_inscrita_tarea;
    private com.github.lgooddatepicker.components.DatePicker fecha_realizar_tarea;
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
