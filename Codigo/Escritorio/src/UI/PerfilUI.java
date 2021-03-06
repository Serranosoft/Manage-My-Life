package UI;

import Compartir.Peticion;
import Compartir.Tareas;
import Compartir.Usuarios;
import Conexion.Conexion;
import java.awt.Color;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Base64;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Calcula la información de la pantalla del Perfil y carga de distintos
 * componentes swing de la aplicación
 *
 * @author manue
 */
public class PerfilUI extends javax.swing.JFrame {

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
    Tareas tareas = new Tareas();

    /**
     * Constructor administrando la interfaz, llamadas de métodos.
     *
     * @param usuario Objeto usuarios con los datos del usuario actual
     */
    public PerfilUI(Usuarios usuario) {
        initComponents();
        try {
            cliente = new Socket(server, puerto);
            salida = new ObjectOutputStream(cliente.getOutputStream());
            entrada = new ObjectInputStream(cliente.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.setLocationRelativeTo(null);
        this.usuarios = usuario;
        rellenarDatos(usuarios.getId());
        obtenerInformacionPerfil(usuarios);
        obtenerImagenPerfil(usuarios);
        btn_1.setBackground(Color.CYAN);
        text_btn1.setForeground(Color.BLACK);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        side_pane = new javax.swing.JPanel();
        btn_1 = new javax.swing.JPanel();
        ind_1 = new javax.swing.JPanel();
        text_btn1 = new javax.swing.JLabel();
        btn_5 = new javax.swing.JPanel();
        ind_2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        btn_2 = new javax.swing.JPanel();
        ind_3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        btn_6 = new javax.swing.JPanel();
        ind_4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        btn_4 = new javax.swing.JPanel();
        ind_5 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        btn_3 = new javax.swing.JPanel();
        ind_6 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        perfil_nombre = new javax.swing.JLabel();
        perfil_imagen = new javax.swing.JLabel();
        perfil_modificar = new javax.swing.JLabel();
        perfil_cerrar_sesion = new javax.swing.JLabel();
        perfil_balance = new javax.swing.JLabel();
        perfil_tareas_pendientes = new javax.swing.JLabel();
        perfil_tareas_terminadas = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(71, 120, 197));

        side_pane.setBackground(new java.awt.Color(0, 51, 153));
        side_pane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_1.setBackground(new java.awt.Color(0, 51, 153));

        ind_1.setOpaque(false);
        ind_1.setPreferredSize(new java.awt.Dimension(3, 43));

        javax.swing.GroupLayout ind_1Layout = new javax.swing.GroupLayout(ind_1);
        ind_1.setLayout(ind_1Layout);
        ind_1Layout.setHorizontalGroup(
            ind_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        ind_1Layout.setVerticalGroup(
            ind_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );

        text_btn1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        text_btn1.setForeground(new java.awt.Color(255, 255, 255));
        text_btn1.setText("Perfil");

        javax.swing.GroupLayout btn_1Layout = new javax.swing.GroupLayout(btn_1);
        btn_1.setLayout(btn_1Layout);
        btn_1Layout.setHorizontalGroup(
            btn_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_1Layout.createSequentialGroup()
                .addComponent(ind_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(text_btn1)
                .addGap(0, 99, Short.MAX_VALUE))
        );
        btn_1Layout.setVerticalGroup(
            btn_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_1Layout.createSequentialGroup()
                .addComponent(ind_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(btn_1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(text_btn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        side_pane.add(btn_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 150, -1));

        btn_5.setBackground(new java.awt.Color(0, 51, 153));
        btn_5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_5MouseClicked(evt);
            }
        });

        ind_2.setOpaque(false);
        ind_2.setPreferredSize(new java.awt.Dimension(3, 43));

        javax.swing.GroupLayout ind_2Layout = new javax.swing.GroupLayout(ind_2);
        ind_2.setLayout(ind_2Layout);
        ind_2Layout.setHorizontalGroup(
            ind_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        ind_2Layout.setVerticalGroup(
            ind_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Control de Gastos");

        javax.swing.GroupLayout btn_5Layout = new javax.swing.GroupLayout(btn_5);
        btn_5.setLayout(btn_5Layout);
        btn_5Layout.setHorizontalGroup(
            btn_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_5Layout.createSequentialGroup()
                .addComponent(ind_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addGap(0, 18, Short.MAX_VALUE))
        );
        btn_5Layout.setVerticalGroup(
            btn_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_5Layout.createSequentialGroup()
                .addComponent(ind_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(btn_5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        side_pane.add(btn_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 150, -1));

        btn_2.setBackground(new java.awt.Color(0, 51, 153));
        btn_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_2MouseClicked1(evt);
            }
        });

        ind_3.setOpaque(false);
        ind_3.setPreferredSize(new java.awt.Dimension(3, 43));

        javax.swing.GroupLayout ind_3Layout = new javax.swing.GroupLayout(ind_3);
        ind_3.setLayout(ind_3Layout);
        ind_3Layout.setHorizontalGroup(
            ind_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        ind_3Layout.setVerticalGroup(
            ind_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Tareas");

        javax.swing.GroupLayout btn_2Layout = new javax.swing.GroupLayout(btn_2);
        btn_2.setLayout(btn_2Layout);
        btn_2Layout.setHorizontalGroup(
            btn_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_2Layout.createSequentialGroup()
                .addComponent(ind_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addGap(0, 90, Short.MAX_VALUE))
        );
        btn_2Layout.setVerticalGroup(
            btn_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_2Layout.createSequentialGroup()
                .addComponent(ind_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(btn_2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        side_pane.add(btn_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 150, -1));

        btn_6.setBackground(new java.awt.Color(0, 51, 153));
        btn_6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_6MouseClicked(evt);
            }
        });

        ind_4.setOpaque(false);
        ind_4.setPreferredSize(new java.awt.Dimension(3, 43));

        javax.swing.GroupLayout ind_4Layout = new javax.swing.GroupLayout(ind_4);
        ind_4.setLayout(ind_4Layout);
        ind_4Layout.setHorizontalGroup(
            ind_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        ind_4Layout.setVerticalGroup(
            ind_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Informes");

        javax.swing.GroupLayout btn_6Layout = new javax.swing.GroupLayout(btn_6);
        btn_6.setLayout(btn_6Layout);
        btn_6Layout.setHorizontalGroup(
            btn_6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_6Layout.createSequentialGroup()
                .addComponent(ind_4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addGap(0, 75, Short.MAX_VALUE))
        );
        btn_6Layout.setVerticalGroup(
            btn_6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_6Layout.createSequentialGroup()
                .addComponent(ind_4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(btn_6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        side_pane.add(btn_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 150, -1));

        btn_4.setBackground(new java.awt.Color(0, 51, 153));
        btn_4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_4MouseClicked(evt);
            }
        });

        ind_5.setOpaque(false);
        ind_5.setPreferredSize(new java.awt.Dimension(3, 43));

        javax.swing.GroupLayout ind_5Layout = new javax.swing.GroupLayout(ind_5);
        ind_5.setLayout(ind_5Layout);
        ind_5Layout.setHorizontalGroup(
            ind_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        ind_5Layout.setVerticalGroup(
            ind_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Calendario");

        javax.swing.GroupLayout btn_4Layout = new javax.swing.GroupLayout(btn_4);
        btn_4.setLayout(btn_4Layout);
        btn_4Layout.setHorizontalGroup(
            btn_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_4Layout.createSequentialGroup()
                .addComponent(ind_5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addGap(0, 64, Short.MAX_VALUE))
        );
        btn_4Layout.setVerticalGroup(
            btn_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_4Layout.createSequentialGroup()
                .addComponent(ind_5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(btn_4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        side_pane.add(btn_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 150, -1));

        btn_3.setBackground(new java.awt.Color(0, 51, 153));
        btn_3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_3MouseClicked(evt);
            }
        });

        ind_6.setOpaque(false);
        ind_6.setPreferredSize(new java.awt.Dimension(3, 43));

        javax.swing.GroupLayout ind_6Layout = new javax.swing.GroupLayout(ind_6);
        ind_6.setLayout(ind_6Layout);
        ind_6Layout.setHorizontalGroup(
            ind_6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        ind_6Layout.setVerticalGroup(
            ind_6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Tareas Terminadas");

        javax.swing.GroupLayout btn_3Layout = new javax.swing.GroupLayout(btn_3);
        btn_3.setLayout(btn_3Layout);
        btn_3Layout.setHorizontalGroup(
            btn_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_3Layout.createSequentialGroup()
                .addComponent(ind_6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addGap(0, 16, Short.MAX_VALUE))
        );
        btn_3Layout.setVerticalGroup(
            btn_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_3Layout.createSequentialGroup()
                .addComponent(ind_6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(btn_3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        side_pane.add(btn_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 150, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\manue\\Downloads\\logo.png")); // NOI18N
        side_pane.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 440, -1, 100));

        jPanel2.setBackground(new java.awt.Color(0, 102, 153));
        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel2MouseDragged(evt);
            }
        });
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel2MousePressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel5.setText("PERFIL");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(351, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(348, 348, 348))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel5)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        perfil_nombre.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        perfil_nombre.setForeground(new java.awt.Color(255, 255, 255));
        perfil_nombre.setText("xxxxxxxx");
        jPanel4.add(perfil_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, 240, 30));

        perfil_imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/user.png"))); // NOI18N
        jPanel4.add(perfil_imagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 140, 140));

        perfil_modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ajustes.png"))); // NOI18N
        perfil_modificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                perfil_modificarMouseClicked(evt);
            }
        });
        jPanel4.add(perfil_modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 50, -1, -1));

        perfil_cerrar_sesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lock.png"))); // NOI18N
        perfil_cerrar_sesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                perfil_cerrar_sesionMouseClicked(evt);
            }
        });
        jPanel4.add(perfil_cerrar_sesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 120, -1, -1));

        perfil_balance.setFont(new java.awt.Font("Segoe UI", 0, 35)); // NOI18N
        perfil_balance.setForeground(new java.awt.Color(0, 0, 0));
        perfil_balance.setText("x");

        perfil_tareas_pendientes.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        perfil_tareas_pendientes.setForeground(new java.awt.Color(0, 0, 0));
        perfil_tareas_pendientes.setText("x");

        perfil_tareas_terminadas.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        perfil_tareas_terminadas.setForeground(new java.awt.Color(0, 0, 0));
        perfil_tareas_terminadas.setText("x");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("TAREAS PENDIENTES");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("TAREAS TERMINADAS");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("BALANCE");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 702, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel1)
                        .addGap(62, 62, 62)
                        .addComponent(jLabel3)
                        .addGap(73, 73, 73))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(perfil_tareas_pendientes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 249, Short.MAX_VALUE)
                        .addComponent(perfil_tareas_terminadas)
                        .addGap(166, 166, 166)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(perfil_balance, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(perfil_tareas_pendientes))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(perfil_tareas_terminadas)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(perfil_balance)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(210, 210, 210))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 165, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(side_pane, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 47, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addComponent(side_pane, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseDragged

    }//GEN-LAST:event_jPanel2MouseDragged

    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed

    }//GEN-LAST:event_jPanel2MousePressed

    private void btn_5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_5MouseClicked
        FinanzasUI finanzas = new FinanzasUI(usuarios);
        finanzas.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btn_5MouseClicked

    private void btn_4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_4MouseClicked
        CalendarioUI calendario = new CalendarioUI(usuarios);
        calendario.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btn_4MouseClicked

    private void btn_3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_3MouseClicked
        TareasTerminadasUI tareasTerminadasUI = new TareasTerminadasUI(usuarios);
        tareasTerminadasUI.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btn_3MouseClicked

    private void btn_2MouseClicked1(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_2MouseClicked1
        TareasUI tareasUI = new TareasUI(usuarios);
        tareasUI.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btn_2MouseClicked1

    private void perfil_modificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_perfil_modificarMouseClicked
        modificarPerfil moPerfil = new modificarPerfil(this, false, usuarios);
        moPerfil.setVisible(true);
    }//GEN-LAST:event_perfil_modificarMouseClicked

    private void perfil_cerrar_sesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_perfil_cerrar_sesionMouseClicked

        InicioSesionUI inicioSesionUI = new InicioSesionUI();
        inicioSesionUI.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_perfil_cerrar_sesionMouseClicked

    private void btn_6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_6MouseClicked
        InformesUI informesUI = new InformesUI(usuarios);
        informesUI.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btn_6MouseClicked
    /**
     * Método para rellenar distintos campos con los datos del usuario.
     *
     * @param idUsuario Identificador del Usuario
     */
    private void rellenarDatos(int idUsuario) {

        int tareas_pendientes = 0;
        int tareas_terminadas = 0;
        try {
            peticion.setConsulta(3);
            salida.writeObject(peticion);
            tareas.setIdUsuario(idUsuario);
            salida.writeObject(tareas);

            tareas = (Tareas) entrada.readObject();

            for (int i = 0; i < tareas.getResultados_tareas().size(); i++) {
                if (tareas.getResultados_tareas().get(i).getEstado() == 1) {
                    tareas_terminadas++;
                } else {
                    tareas_pendientes++;
                }
            }

            perfil_tareas_pendientes.setText(tareas_pendientes + "");
            perfil_tareas_terminadas.setText(tareas_terminadas + "");
            perfil_nombre.setText(usuarios.getNombre());
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Método para cargar el balance actual del usuario en pantalla.
     *
     * @param usuarios Objeto usuarios con los datos del usuario actual
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
            usuarios = (Usuarios) entrada.readObject();
            double res = Math.round(usuarios.getSalario() * 100.0) / 100.0;
            perfil_balance.setText(res + " €");
            //perfil_balance.setText(usuarios.getSalario() + "€");

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Método para cargar la imágen del usuario (carga una imagen user si no ha
     * indicado ninguna imágen de perfil)
     *
     * @param usuarios Objeto usuarios con los datos del usuario actual
     */
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
            java.util.logging.Logger.getLogger(PerfilUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PerfilUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PerfilUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PerfilUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PerfilUI(new Usuarios()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btn_1;
    private javax.swing.JPanel btn_2;
    private javax.swing.JPanel btn_3;
    private javax.swing.JPanel btn_4;
    private javax.swing.JPanel btn_5;
    private javax.swing.JPanel btn_6;
    private javax.swing.JPanel ind_1;
    private javax.swing.JPanel ind_2;
    private javax.swing.JPanel ind_3;
    private javax.swing.JPanel ind_4;
    private javax.swing.JPanel ind_5;
    private javax.swing.JPanel ind_6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel perfil_balance;
    private javax.swing.JLabel perfil_cerrar_sesion;
    private javax.swing.JLabel perfil_imagen;
    private javax.swing.JLabel perfil_modificar;
    private javax.swing.JLabel perfil_nombre;
    private javax.swing.JLabel perfil_tareas_pendientes;
    private javax.swing.JLabel perfil_tareas_terminadas;
    private javax.swing.JPanel side_pane;
    private javax.swing.JLabel text_btn1;
    // End of variables declaration//GEN-END:variables

}
