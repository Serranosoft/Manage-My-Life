
import Compartir.Gastos;
import Compartir.Peticion;
import Compartir.Productos;
import Compartir.Subtareas;
import Compartir.Tareas;
import Compartir.Usuarios;
import dao.GastosOP;
import dao.TareasOP;
import dao.UsuariosOP;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import vo.Gasto;
import vo.Producto;
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
class HiloServidor extends Thread {

    ObjectOutputStream salida;
    ObjectInputStream entrada;

    Socket usuario = null;

    Tareas tareas = new Tareas();
    Usuarios usuarios = new Usuarios();
    Peticion peticion = new Peticion();
    Subtareas subtareas = new Subtareas();
    Gastos gastos = new Gastos();
    Productos productos = new Productos();
    int consulta = 0;

    HiloServidor(Socket usuario, ObjectOutputStream salida) {

        this.usuario = usuario;

        this.salida = salida;
        try {
            this.entrada = new ObjectInputStream(usuario.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {

        try {
            // Se queda esperando a que el cliente mande una consulta a traves del objeto peticion
            peticion = (Peticion) entrada.readObject();
            consulta = peticion.getConsulta();
            switch (consulta) {
                case 1:
                    usuarios = (Usuarios) entrada.readObject();
                    insertarUsuario(usuarios);
                    break;
                case 2:
                    usuarios = (Usuarios) entrada.readObject();
                    comprobarUsuario(usuarios);
                    salida.writeObject(usuarios);
                    break;
                case 3:
                    tareas = (Tareas) entrada.readObject();
                    obtenerTareas(tareas);
                    salida.writeObject(tareas);
                    break;
                case 4:
                    tareas = (Tareas) entrada.readObject();
                    insertarTarea(tareas);
                    break;
                case 5:
                    tareas = (Tareas) entrada.readObject();
                    obtenerTarea(tareas);
                    salida.writeObject(tareas);
                    break;
                case 6:
                    tareas = (Tareas) entrada.readObject();
                    eliminarTarea(tareas);
                    break;
                case 7:
                    tareas = (Tareas) entrada.readObject();
                    actualizarTarea(tareas);
                    break;
                case 8:
                    tareas = (Tareas) entrada.readObject();
                    obtenerSubtareas(tareas);
                    salida.writeObject(tareas);
                    break;
                case 9:
                    subtareas = (Subtareas) entrada.readObject();
                    insertarSubtarea(subtareas);
                    break;
                case 10:
                    subtareas = (Subtareas) entrada.readObject();
                    eliminarSubtarea(subtareas);
                    break;
                case 11:
                    usuarios = (Usuarios) entrada.readObject();
                    modificarUsuario(usuarios);
                    break;
                case 12:
                    tareas = (Tareas) entrada.readObject();
                    actualizarEstadoTarea(tareas);
                    break;
                case 13:
                    subtareas = (Subtareas) entrada.readObject();
                    actualizarEstadoSubtarea(subtareas);
                    break;
                case 14:
                    gastos = (Gastos) entrada.readObject();
                    obtenerGastos(gastos);
                    salida.writeObject(gastos);
                    break;
                case 15:
                    gastos = (Gastos) entrada.readObject();
                    insertarGasto(gastos);
                    break;
                case 16:
                    gastos = (Gastos) entrada.readObject();
                    eliminarGasto(gastos);
                    break;
                case 17:
                    gastos = (Gastos) entrada.readObject();
                    obtenerProductos(gastos);
                    salida.writeObject(gastos);
                    break;
                case 18:
                    usuarios = (Usuarios) entrada.readObject();
                    comprobarExisteUsuario(usuarios);
                    salida.writeObject(usuarios);
                    break;
                case 19:
                    productos = (Productos) entrada.readObject();
                    insertarProducto(productos);
                    break;
                case 20:
                    productos = (Productos) entrada.readObject();
                    eliminarProducto(productos);
                    break;
                case 21:
                    usuarios = (Usuarios) entrada.readObject();
                    actualizarSalario(usuarios);
                    break;
                case 22:
                    usuarios = (Usuarios) entrada.readObject();
                    obtenerInformacionPerfil(usuarios);
                    salida.writeObject(usuarios);
                    break;
                case 23:
                    gastos = (Gastos) entrada.readObject();
                    actualizarGasto(gastos);
                    break;

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }

    private void obtenerTareas(Tareas tareas) {

        ArrayList<Tarea> listado_tareas = null;
        try {

            TareasOP top = new TareasOP();
            listado_tareas = top.selectTareas(tareas);

        } catch (Exception e) {
            e.printStackTrace();
        }
        tareas.setResultados_tareas(listado_tareas);
    }

    private void insertarUsuario(Usuarios usuarios) {

        UsuariosOP uop = new UsuariosOP();
        uop.insertarUsuario(usuarios);
    }

    private void comprobarUsuario(Usuarios usuarios) {

        UsuariosOP uop = new UsuariosOP();
        usuarios = uop.comprobarUsuario(usuarios);
    }

    private void insertarTarea(Tareas tareas) {
        TareasOP top = new TareasOP();
        top.insertarTarea(tareas);
    }

    private void obtenerTarea(Tareas tareas) {
        try {

            TareasOP top = new TareasOP();
            tareas = top.selectTarea(tareas);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void eliminarTarea(Tareas tareas) {

        try {
            TareasOP top = new TareasOP();
            top.eliminarTarea(tareas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void actualizarTarea(Tareas tareas) {

        try {
            TareasOP top = new TareasOP();
            top.actualizarTarea(tareas);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void obtenerSubtareas(Tareas tareas) {

        try {
            ArrayList<Subtarea> listado_subtareas = null;
            TareasOP top = new TareasOP();
            listado_subtareas = top.obtenerSubtareas(tareas);
            tareas.setSubtareas(listado_subtareas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertarSubtarea(Subtareas subtareas) {

        try {
            TareasOP top = new TareasOP();
            top.insertarSubtarea(subtareas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void eliminarSubtarea(Subtareas subtareas) {

        try {
            TareasOP top = new TareasOP();
            top.eliminarSubtarea(subtareas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void modificarUsuario(Usuarios usuarios) {

        UsuariosOP uop = new UsuariosOP();
        uop.modificarUsuario(usuarios);
    }

    private void actualizarEstadoTarea(Tareas tareas) {
        try {
            TareasOP top = new TareasOP();
            top.actualizarEstadoTarea(tareas);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void actualizarEstadoSubtarea(Subtareas subtareas) {
        try {
            TareasOP top = new TareasOP();
            top.actualizarEstadoSubtarea(subtareas);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void obtenerGastos(Gastos gastos) {

        ArrayList<Gasto> listado_gastos = null;
        try {

            GastosOP gop = new GastosOP();
            listado_gastos = gop.selectGastos(gastos);

        } catch (Exception e) {
            e.printStackTrace();
        }
        gastos.setResultados_gastos(listado_gastos);
    }

    private void insertarGasto(Gastos gastos) {
        GastosOP gop = new GastosOP();
        gop.insertarGasto(gastos);
    }

    private void eliminarGasto(Gastos gastos) {

        try {
            GastosOP gop = new GastosOP();
            gop.eliminarGasto(gastos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void obtenerProductos(Gastos gastos) {

        try {
            ArrayList<Producto> listado_productos = null;
            GastosOP gop = new GastosOP();
            listado_productos = gop.obtenerProductos(gastos);
            gastos.setProductos(listado_productos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void comprobarExisteUsuario(Usuarios usuarios) {

        UsuariosOP uop = new UsuariosOP();
        usuarios = uop.comprobarExisteUsuario(usuarios);
    }

    private void insertarProducto(Productos productos) {

        try {
            GastosOP gop = new GastosOP();
            gop.insertarProducto(productos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void eliminarProducto(Productos productos) {

        try {
            GastosOP gop = new GastosOP();
            gop.eliminarProducto(productos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void actualizarSalario(Usuarios usuarios) {
        
        try {
            UsuariosOP uop = new UsuariosOP();
            uop.actualizarSalario(usuarios);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void obtenerInformacionPerfil(Usuarios usuarios) {
        
        try {
            UsuariosOP uop = new UsuariosOP();
            uop.obtenerInformacion(usuarios);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    private void actualizarGasto(Gastos gastos) {
        
        try {
            GastosOP gop = new GastosOP();
            gop.actualizarGasto(gastos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
