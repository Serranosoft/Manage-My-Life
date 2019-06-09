/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Compartir.Gastos;
import Compartir.Peticion;
import Compartir.Productos;
import Compartir.Subtareas;
import Compartir.Tareas;
import Compartir.Usuarios;
import Conexion.Conexion;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vo.Producto;
import vo.Subtarea;

/**
 * Interfaz con información del gasto seleccionado con los productos
 * relacionados al gasto con todas las funcionalidades relacionados a los
 * productos
 *
 * @author manue
 */
public class informacionGasto extends javax.swing.JDialog {

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
    boolean edicion = false;
    int id = 0;
    double sumaProductos = 0;
    int cant_productos = 0;
    double precio_eliminado = 0.0;
    double precio_gasto = 0;

    /**
     * Constructor para cargar distintos métodos iniciales.
     *
     * @param parent JFrame padre
     * @param modal modal
     * @param id Identificador del gasto
     * @param usuarios Objeto usuarios con los valores del usuario actual
     */
    public informacionGasto(java.awt.Frame parent, boolean modal, int id, Usuarios usuarios) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        obtenerProductos(id);
        this.id = id;
        this.usuarios = usuarios;
        obtenerInformacionPerfil(usuarios);
        //usuario_balance.setText(usuarios.getSalario() + " €");
        obtenerImagenPerfil(usuarios);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_productos = new javax.swing.JTable();
        añadir_producto = new javax.swing.JButton();
        eliminar_producto = new javax.swing.JButton();
        eliminar_Gastobtn = new javax.swing.JButton();
        cantidad_productos = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        usuario_balance = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        perfil_imagen = new javax.swing.JLabel();
        guardar_cambios = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(0, 102, 153));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel1.setText("PRODUCTOS ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(259, 259, 259))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(19, 19, 19))
        );

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        tabla_productos.setBackground(new java.awt.Color(187, 187, 187));
        tabla_productos.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tabla_productos.setForeground(new java.awt.Color(0, 0, 0));
        tabla_productos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Producto", "Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_productos.setGridColor(new java.awt.Color(102, 102, 102));
        tabla_productos.setRowHeight(22);
        tabla_productos.setSelectionBackground(new java.awt.Color(51, 102, 255));
        tabla_productos.setSelectionForeground(new java.awt.Color(0, 0, 102));
        jScrollPane1.setViewportView(tabla_productos);
        if (tabla_productos.getColumnModel().getColumnCount() > 0) {
            tabla_productos.getColumnModel().getColumn(1).setMinWidth(100);
            tabla_productos.getColumnModel().getColumn(1).setPreferredWidth(100);
            tabla_productos.getColumnModel().getColumn(1).setMaxWidth(100);
        }

        añadir_producto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/plus-black-symbol.png"))); // NOI18N
        añadir_producto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                añadir_productoMouseClicked(evt);
            }
        });

        eliminar_producto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/delete.png"))); // NOI18N
        eliminar_producto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eliminar_productoMouseClicked(evt);
            }
        });

        eliminar_Gastobtn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        eliminar_Gastobtn.setForeground(new java.awt.Color(0, 0, 0));
        eliminar_Gastobtn.setText("ELIMINAR GASTO");
        eliminar_Gastobtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eliminar_GastobtnMouseClicked(evt);
            }
        });

        cantidad_productos.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        cantidad_productos.setForeground(new java.awt.Color(51, 51, 51));
        cantidad_productos.setText("X");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 28)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("PRODUCTOS");

        usuario_balance.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        usuario_balance.setForeground(new java.awt.Color(51, 51, 51));
        usuario_balance.setText("XXX€");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 28)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("BALANCE");

        perfil_imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/user.png"))); // NOI18N

        guardar_cambios.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        guardar_cambios.setForeground(new java.awt.Color(0, 0, 0));
        guardar_cambios.setText("GUARDAR CAMBIOS");
        guardar_cambios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                guardar_cambiosMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 671, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(guardar_cambios, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(eliminar_Gastobtn, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(99, 99, 99)
                                .addComponent(cantidad_productos))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(perfil_imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(usuario_balance, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(añadir_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eliminar_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(cantidad_productos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(usuario_balance)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(perfil_imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(eliminar_producto, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(añadir_producto, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eliminar_Gastobtn)
                    .addComponent(guardar_cambios))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
/**
     * Método para eliminar productos seleccionando de la tabla y obteniendo su
     * identificador, actualizamos la tabla con los nuevos valores y actualiza
     * el salario del usuario
     *
     * @param evt Evento clic
     */
    private void eliminar_productoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eliminar_productoMouseClicked
        if (tabla_productos.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Selecciona algun producto!");
        } else if (tabla_productos.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "No hay productos!!");
        } else {
            obtenerInformacionPerfil(usuarios);
            precio_gasto = 0.0;

            try {
                cliente = new Socket(server, puerto);
                salida = new ObjectOutputStream(cliente.getOutputStream());
                entrada = new ObjectInputStream(cliente.getInputStream());

                peticion.setConsulta(17);
                salida.writeObject(peticion);
                salida.flush();
                gastos.setId(id);
                salida.writeObject(gastos);
                salida.flush();
                gastos = (Gastos) entrada.readObject();
                double precio_prod = 0.0;
                ArrayList<Producto> listado_productos = gastos.getProductos();
                for (int i = 0; i < listado_productos.size(); i++) {
                    Producto producto = listado_productos.get(i);
                    precio_prod = Math.round(producto.getPrecio_producto() * 100.0)/100.0;
                    Object[] array = {producto.getNombre_producto(), precio_prod + " €"};
                    cant_productos++;
                    precio_gasto += producto.getPrecio_producto();
                    m.addRow(array);

                }
                gastos.setPrecio_gasto(precio_gasto);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            try {
                cliente = new Socket(server, puerto);
                salida = new ObjectOutputStream(cliente.getOutputStream());
                entrada = new ObjectInputStream(cliente.getInputStream());

                int id = tabla_productos.getSelectedRow();
                productos.setID(gastos.getProductos().get(id).getId());
                precio_eliminado = gastos.getProductos().get(id).getPrecio_producto();
                peticion.setConsulta(20);
                salida.writeObject(peticion);
                salida.flush();
                salida.writeObject(productos);
                salida.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            m = (DefaultTableModel) tabla_productos.getModel();
            m.setRowCount(0);

            // Configuro salario actualizado (tras la eliminación de un producto)
            double salario_actual = usuarios.getSalario();
            usuarios.setSalario(salario_actual + precio_eliminado);
            double res = Math.round(usuarios.getSalario() * 100.0) / 100.0;
            usuario_balance.setText(res + " €");
            //usuario_balance.setText(usuarios.getSalario() + " €");
            cant_productos = 0;

            // Configuración de parámetros para la actualización del salario al usuario '?'
            try {
                cliente = new Socket(server, puerto);
                salida = new ObjectOutputStream(cliente.getOutputStream());
                entrada = new ObjectInputStream(cliente.getInputStream());

                peticion.setConsulta(21);
                salida.writeObject(peticion);
                salida.flush();
                salida.writeObject(usuarios);
                salida.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    cliente.close();
                    salida.close();
                    entrada.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            // Actualización del gasto
            gastos.setPrecio_gasto(gastos.getPrecio_gasto() - precio_eliminado);
            precio_eliminado = 0;
            try {
                cliente = new Socket(server, puerto);
                salida = new ObjectOutputStream(cliente.getOutputStream());
                entrada = new ObjectInputStream(cliente.getInputStream());

                peticion.setConsulta(23);
                salida.writeObject(peticion);
                salida.flush();
                salida.writeObject(gastos);
                salida.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    cliente.close();
                    salida.close();
                    entrada.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            try {
                cliente = new Socket(server, puerto);
                salida = new ObjectOutputStream(cliente.getOutputStream());
                entrada = new ObjectInputStream(cliente.getInputStream());

                peticion.setConsulta(17);
                salida.writeObject(peticion);
                salida.flush();
                gastos.setId(id);
                salida.writeObject(gastos);
                salida.flush();
                gastos = (Gastos) entrada.readObject();
                double precio_prod = 0.0;
                ArrayList<Producto> listado_productos = gastos.getProductos();
                for (int i = 0; i < listado_productos.size(); i++) {
                    Producto producto = listado_productos.get(i);
                    precio_prod = Math.round(producto.getPrecio_producto() * 100.0) / 100.0;
                    Object[] array = {producto.getNombre_producto(), precio_prod + " €"};
                    cant_productos++;
                    m.addRow(array);
                }
                cantidad_productos.setText(cant_productos + "");
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }

        }

    }//GEN-LAST:event_eliminar_productoMouseClicked
    /**
     * Método para eliminar gasto, actualización de tablas y actualización del
     * salario
     *
     * @param evt Evento clic
     */
    private void eliminar_GastobtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eliminar_GastobtnMouseClicked

        try {
            obtenerInformacionPerfil(usuarios);
            cliente = new Socket(server, puerto);
            salida = new ObjectOutputStream(cliente.getOutputStream());
            entrada = new ObjectInputStream(cliente.getInputStream());

            peticion.setConsulta(17);
            salida.writeObject(peticion);
            salida.flush();
            gastos.setId(id);
            salida.writeObject(gastos);
            salida.flush();
            gastos = (Gastos) entrada.readObject();

            for (int i = 0; i < gastos.getProductos().size(); i++) {
                precio_eliminado += gastos.getProductos().get(i).getPrecio_producto();
            }

            // Elimino el gasto
            cliente = new Socket(server, puerto);
            salida = new ObjectOutputStream(cliente.getOutputStream());
            entrada = new ObjectInputStream(cliente.getInputStream());

            //gastos.setId(id);
            peticion.setConsulta(16);
            salida.writeObject(peticion);
            salida.flush();
            salida.writeObject(gastos);
            salida.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(informacionGasto.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                cliente.close();
                salida.close();
                entrada.close();
                cerrarDialog();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        // Actualizo salario
        usuarios.setSalario(usuarios.getSalario() + precio_eliminado);
        try {
            cliente = new Socket(server, puerto);
            salida = new ObjectOutputStream(cliente.getOutputStream());
            entrada = new ObjectInputStream(cliente.getInputStream());

            peticion.setConsulta(21);
            salida.writeObject(peticion);
            salida.flush();
            salida.writeObject(usuarios);
            salida.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                cliente.close();
                salida.close();
                entrada.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        // Actualización del gasto
        gastos.setPrecio_gasto(precio_gasto);
        precio_gasto = 0;
        try {
            cliente = new Socket(server, puerto);
            salida = new ObjectOutputStream(cliente.getOutputStream());
            entrada = new ObjectInputStream(cliente.getInputStream());

            peticion.setConsulta(23);
            salida.writeObject(peticion);
            salida.flush();
            salida.writeObject(gastos);
            salida.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                cliente.close();
                salida.close();
                entrada.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }//GEN-LAST:event_eliminar_GastobtnMouseClicked
    boolean insercion = false;

    /**
     * Método para añadir producto, actualizando lista y actualizando el
     * salario.
     *
     * @param evt
     */
    private void añadir_productoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_añadir_productoMouseClicked
        insertarProducto inProducto = new insertarProducto(this, true, gastos);
        inProducto.setVisible(true);
        insercion = inProducto.insertado;
        // En cuanto se realiza la inserción se realiza una espera de 100ms
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        if (insercion) {
            obtenerInformacionPerfil(usuarios);
            m.setRowCount(0);
            // Configuración de parámetros y envió de solicitud para obtener productos actualizados
            try {
                cliente = new Socket(server, puerto);
                salida = new ObjectOutputStream(cliente.getOutputStream());
                entrada = new ObjectInputStream(cliente.getInputStream());

                peticion.setConsulta(17);
                salida.writeObject(peticion);
                gastos.setId(id);
                salida.writeObject(gastos);
                gastos = (Gastos) entrada.readObject();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    cliente.close();
                    salida.close();
                    entrada.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            double precio_prod = 0.0;
            ArrayList<Producto> listado_productos = gastos.getProductos();
            for (int i = 0; i < listado_productos.size(); i++) {
                Producto producto = listado_productos.get(i);
                precio_prod = Math.round(producto.getPrecio_producto() * 100.0) / 100.0;
                Object[] array = {producto.getNombre_producto(), precio_prod + " €"};
                cant_productos++;
                precio_gasto += producto.getPrecio_producto();
                m.addRow(array);

            }

            // Configuro salario actualizado (tras la inserción de un producto)
            double salario_actual = usuarios.getSalario();
            usuarios.setSalario(salario_actual - listado_productos.get(listado_productos.size() - 1).getPrecio_producto());

            cantidad_productos.setText(cant_productos + "");
            double res = Math.round(usuarios.getSalario() * 100.0) / 100.0;
            usuario_balance.setText(res + " €");
            //usuario_balance.setText(usuarios.getSalario() + " €");
            cant_productos = 0;
            // Configuración de parámetros para la actualización del salario al usuario '?'
            try {
                cliente = new Socket(server, puerto);
                salida = new ObjectOutputStream(cliente.getOutputStream());
                entrada = new ObjectInputStream(cliente.getInputStream());

                peticion.setConsulta(21);
                salida.writeObject(peticion);
                salida.flush();
                salida.writeObject(usuarios);
                salida.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    cliente.close();
                    salida.close();
                    entrada.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            // Actualización del gasto
            gastos.setPrecio_gasto(precio_gasto);
            precio_gasto = 0;
            try {
                cliente = new Socket(server, puerto);
                salida = new ObjectOutputStream(cliente.getOutputStream());
                entrada = new ObjectInputStream(cliente.getInputStream());

                peticion.setConsulta(23);
                salida.writeObject(peticion);
                salida.flush();
                salida.writeObject(gastos);
                salida.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    cliente.close();
                    salida.close();
                    entrada.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        }
    }//GEN-LAST:event_añadir_productoMouseClicked
    /**
     * Método para guardar cambios
     *
     * @param evt Evento clic
     */
    private void guardar_cambiosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guardar_cambiosMouseClicked
        cerrarDialog();
    }//GEN-LAST:event_guardar_cambiosMouseClicked
    /**
     * Método para cerrar el JDialog
     *
     * @return boolean indicando si se ha cerrado o no el JDialog
     */
    public boolean cerrarDialog() {
        this.setVisible(false);
        return true;
    }
    DefaultTableModel m;

    /**
     * Método para obtener todos los productos al iniciar el JDialog
     *
     * @param id Identificador de la tarea
     */
    public void obtenerProductos(int id) {
        m = (DefaultTableModel) tabla_productos.getModel();
        m.setRowCount(0);

        try {
            cliente = new Socket(server, puerto);
            salida = new ObjectOutputStream(cliente.getOutputStream());
            entrada = new ObjectInputStream(cliente.getInputStream());

            peticion.setConsulta(17);
            salida.writeObject(peticion);
            salida.flush();
            gastos.setId(id);
            salida.writeObject(gastos);
            salida.flush();
            gastos = (Gastos) entrada.readObject();
            double precio_prod = 0.0;
            ArrayList<Producto> listado_productos = gastos.getProductos();
            for (int i = 0; i < listado_productos.size(); i++) {
                Producto producto = listado_productos.get(i);
                precio_prod = Math.round(producto.getPrecio_producto() * 100.0) / 100.0;
                Object[] array = {producto.getNombre_producto(), precio_prod + " €"};
                cant_productos++;
                m.addRow(array);

            }
            cantidad_productos.setText(cant_productos + "");
            cant_productos = 0;
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            try {
                cliente.close();
                salida.close();
                entrada.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

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
            double res = Math.round(this.usuarios.getSalario() * 100.0) / 100.0;
            usuario_balance.setText(res + " €");
            //usuario_balance.setText(this.usuarios.getSalario() + " €");

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    // Método para obtener la información del usuario y configuración de la imágen de perfil
    public void obtenerImagenPerfil(Usuarios usuarios) {
        try {
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
            java.util.logging.Logger.getLogger(informacionGasto.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(informacionGasto.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(informacionGasto.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(informacionGasto.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                informacionGasto dialog = new informacionGasto(new javax.swing.JFrame(), true, 0, new Usuarios());
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
    private javax.swing.JButton añadir_producto;
    private javax.swing.JLabel cantidad_productos;
    private javax.swing.JButton eliminar_Gastobtn;
    private javax.swing.JButton eliminar_producto;
    private javax.swing.JButton guardar_cambios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel perfil_imagen;
    private javax.swing.JTable tabla_productos;
    private javax.swing.JLabel usuario_balance;
    // End of variables declaration//GEN-END:variables
}
