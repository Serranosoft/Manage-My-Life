package com.example.manue.managemylife.Activities;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.manue.managemylife.Adapters.ProductosAdapter;
import com.example.manue.managemylife.Adapters.SubtareasAdapter;
import com.example.manue.managemylife.Fragments.fragmentPerfil;
import com.example.manue.managemylife.R;
import com.example.manue.managemylife.Util.SwipeableRecyclerViewTouchListener;
import com.example.manue.managemylife.vo.SettingsClass;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import Compartir.Gastos;
import Compartir.Peticion;
import Compartir.Productos;
import Compartir.Usuarios;
import de.hdodenhof.circleimageview.CircleImageView;
import vo.Producto;
import vo.Subtarea;

/**
 * Activity que permite insertar productos relacionados a un gasto
 */
public class ProductosActivity extends AppCompatActivity {

    Gastos gastos = new Gastos();
    SettingsClass settings = null;
    Peticion peticion = new Peticion();
    Productos productos = new Productos();
    Usuarios usuarios = new Usuarios();

    private RecyclerView rList;
    private ArrayList<Producto> listaProducto;
    private RecyclerView.Adapter adapter;
    FloatingActionButton fab = null;
    FloatingActionButton eliminar_gasto = null;

    private AlertDialog.Builder builder_producto;
    private AlertDialog dialog_producto;

    TextView productos_cantidad = null;
    TextView productos_balance = null;
    CircleImageView imagen_perfil = null;
    int balance = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        settings = new SettingsClass(this);
        //getActionBar().setDisplayHomeAsUpEnabled(true);

        productos_cantidad = (TextView) findViewById(R.id.productos_cantidad);
        productos_balance = (TextView) findViewById(R.id.productos_balance);
        imagen_perfil = (CircleImageView) findViewById(R.id.imagenPerfil);
        productos_balance.setText(balance+"");

        gastos = (Gastos) getIntent().getSerializableExtra("gastos_productos");
        usuarios = (Usuarios) getIntent().getSerializableExtra("usuarios");
        balance = usuarios.getSalario();


        rList = findViewById(R.id.listaProducto);
        rList.setHasFixedSize(true);
        rList.setLayoutManager(new LinearLayoutManager(this));

        obtenerImagenPerfil(usuarios);
        executeActualizarProductos();
        fab = findViewById(R.id.productos_add);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder_producto = new AlertDialog.Builder(new ContextThemeWrapper(ProductosActivity.this, R.style.myDialog));
                View view_popup_producto = LayoutInflater.from(getApplicationContext()).inflate(R.layout.popup_insertar_producto, null);

                builder_producto.setView(view_popup_producto);

                dialog_producto = builder_producto.create();
                dialog_producto.show();


                // Nombre del gasto

                final EditText nombre_producto = view_popup_producto.findViewById(R.id.insertar_nombre_producto);
                final EditText precio_producto = view_popup_producto.findViewById(R.id.insertar_precio_producto);

                final TextView close_gasto = view_popup_producto.findViewById(R.id.insertar_producto_close);
                close_gasto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog_producto.dismiss();
                    }
                });

                final ImageView aceptar_producto = view_popup_producto.findViewById(R.id.insertar_aceptar_producto);
                aceptar_producto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        try{
                            productos.setNombre_Producto(nombre_producto.getText().toString());
                            productos.setPrecio_Producto(Integer.valueOf(precio_producto.getText().toString()));
                            productos.setID_Gasto(gastos.getId());
                            gastos.setPrecio_gasto(gastos.getPrecio_gasto() + productos.getPrecio_Producto());
                            executeInsertarProductos();
                            dialog_producto.dismiss();
                            adapter.notifyDataSetChanged();
                            balance-=Integer.valueOf(precio_producto.getText().toString());
                            usuarios.setSalario(balance);
                            executeUpdateSalario();
                            executeActualizarProductos();
                            executeActualizarGasto();

                            executeObtenerInformacion();

                        }catch (NumberFormatException nfe) {

                            nfe.printStackTrace();
                            final AlertDialog.Builder alert = new AlertDialog.Builder(ProductosActivity.this, R.style.AlertDialog);
                            alert.setTitle("Aviso");
                            alert.setMessage("¡Introduce campos válidos!");
                            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                            alert.create().show();
                        }

                    }
                });
            }
        });



        SwipeableRecyclerViewTouchListener swipeTouchListener =
                new SwipeableRecyclerViewTouchListener(rList,
                        new SwipeableRecyclerViewTouchListener.SwipeListener() {

                            @Override
                            public boolean canSwipeLeft(int position) {
                                return false;
                            }

                            @Override
                            public boolean canSwipeRight(int position) {
                                return true;
                            }

                            @Override
                            public void onDismissedBySwipeLeft(RecyclerView recyclerView, int[] reverseSortedPositions) {

                            }

                            @Override
                            public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (final int position : reverseSortedPositions) {
                                    final AlertDialog.Builder alert = new AlertDialog.Builder(ProductosActivity.this, R.style.AlertDialog);
                                    alert.setTitle("Aviso");
                                    alert.setMessage("¿Quieres eliminar el producto permanentemente?");
                                    alert.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            int idProducto = listaProducto.get(position).getId();
                                            int precio = listaProducto.get(position).getPrecio_producto();
                                            productos.setID(idProducto);
                                            System.out.println("PRECIO DEL GASTO ACTUAL: "+gastos.getPrecio_gasto());
                                            System.out.println("PRECIO A RESTAR: "+precio);
                                            gastos.setPrecio_gasto(gastos.getPrecio_gasto() - precio);
                                            // llamo al metodo eliminar
                                            executeDeleteProductos();
                                            listaProducto.remove(position);
                                            adapter.notifyItemRemoved(position);
                                            balance += precio;
                                            usuarios.setSalario(balance);
                                            executeUpdateSalario();
                                            executeActualizarProductos();
                                            executeActualizarGasto();
                                            executeObtenerInformacion();

                                        }
                                    });
                                    alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.dismiss();
                                        }
                                    });
                                    alert.create().show();

                                }
                                adapter.notifyDataSetChanged();
                            }
                        });

        rList.addOnItemTouchListener(swipeTouchListener);


    }

    private void executeInsertarProductos() {
        insertarProductossTask insertarProductossTask = new insertarProductossTask();
        insertarProductossTask.execute();
    }

    private void executeActualizarProductos() {
        actualizarProductosTask actualizarProductosTask = new actualizarProductosTask();
        actualizarProductosTask.execute();
    }
    private void executeDeleteProductos() {
        eliminarProductosTask eliminarProductosTask = new eliminarProductosTask();
        eliminarProductosTask.execute();
    }
    private void executeUpdateSalario() {
        actualizarSalarioTask actualizarSalarioTask = new actualizarSalarioTask();
        actualizarSalarioTask.execute();
    }
    private void executeObtenerInformacion() {
        obtenerInformacionPerfil obtenerInformacionPerfil = new obtenerInformacionPerfil();
        obtenerInformacionPerfil.execute();
    }
    private void executeActualizarGasto() {
        actualizarGastoTask actualizarGastoTask = new actualizarGastoTask();
        actualizarGastoTask.execute();
    }

    /**
     * Clase AsyncTask para insertar productos en el sistema
     */
    public class insertarProductossTask extends AsyncTask<String, Void, Void> {

        Socket cliente = null;
        ObjectOutputStream salida = null;
        ObjectInputStream entrada = null;

        @Override
        protected Void doInBackground(String... strings) {
            try {
                cliente = new Socket(settings.obtenerSettings().get(0).getAddress(), settings.obtenerSettings().get(0).getPort());
                salida = new ObjectOutputStream(cliente.getOutputStream());
                entrada = new ObjectInputStream(cliente.getInputStream());


                peticion.setConsulta(19);
                salida.writeObject(peticion);

                salida.writeObject(productos);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return null;
        }
    }

    /**
     * Clase AsyncTask para obtener los productos y actualizar la cantidad de productos en pantalla
     */
    public class actualizarProductosTask extends AsyncTask<ArrayList<Producto>, Void, ArrayList<Producto>> {

        Socket cliente = null;
        ObjectOutputStream salida = null;
        ObjectInputStream entrada = null;

        @Override
        protected ArrayList<Producto> doInBackground(ArrayList<Producto>... arrayLists) {
            try {
                cliente = new Socket(settings.obtenerSettings().get(0).getAddress(), settings.obtenerSettings().get(0).getPort());
                salida = new ObjectOutputStream(cliente.getOutputStream());
                entrada = new ObjectInputStream(cliente.getInputStream());

                peticion.setConsulta(17);
                salida.writeObject(peticion);


                salida.writeObject(gastos);

                gastos = (Gastos) entrada.readObject();
                listaProducto = gastos.getProductos();

            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return listaProducto;
        }

        @Override
        protected void onPostExecute(final ArrayList<Producto> listaProducto) {
            super.onPostExecute(listaProducto);

            productos_cantidad.setText(listaProducto.size()+"");
            productos_balance.setText(balance+"€");
            adapter = new ProductosAdapter(listaProducto, getApplicationContext());
            rList.setAdapter(adapter);

        }
    }

    /**
     * Clase AsyncTask para eliminar los productos del sistema
     */
    public class eliminarProductosTask extends AsyncTask<String, Void, Void> {

        Socket cliente = null;
        ObjectOutputStream salida = null;
        ObjectInputStream entrada = null;

        @Override
        protected Void doInBackground(String... strings) {
            try {
                cliente = new Socket(settings.obtenerSettings().get(0).getAddress(), settings.obtenerSettings().get(0).getPort());
                salida = new ObjectOutputStream(cliente.getOutputStream());
                entrada = new ObjectInputStream(cliente.getInputStream());

                peticion.setConsulta(20);
                salida.writeObject(peticion);
                salida.writeObject(productos);

            } catch (IOException ex) {
                ex.printStackTrace();
            }

            return null;
        }
    }

    /**
     * Clase AsyncTask para actualizar el salario del usuario tras la inserción/eliminación de algún producto
     */
    public class actualizarSalarioTask extends AsyncTask<String, Void, Void> {

        Socket cliente = null;
        ObjectOutputStream salida = null;
        ObjectInputStream entrada = null;

        @Override
        protected Void doInBackground(String... strings) {
            try {
                cliente = new Socket(settings.obtenerSettings().get(0).getAddress(), settings.obtenerSettings().get(0).getPort());
                salida = new ObjectOutputStream(cliente.getOutputStream());
                entrada = new ObjectInputStream(cliente.getInputStream());

                peticion.setConsulta(21);
                salida.writeObject(peticion);
                salida.flush();
                salida.writeObject(usuarios);
                salida.flush();

            } catch (IOException ex) {
                ex.printStackTrace();
            }

            return null;
        }
    }

    /**
     * Clase AsyncTask para actualizar el precio del gasto actual al insertar/eliminar un producto relacionado al gasto
     */
    public class actualizarGastoTask extends AsyncTask<String, Void, Void> {

        Socket cliente = null;
        ObjectOutputStream salida = null;
        ObjectInputStream entrada = null;

        @Override
        protected Void doInBackground(String... strings) {
            try {
                cliente = new Socket(settings.obtenerSettings().get(0).getAddress(), settings.obtenerSettings().get(0).getPort());
                salida = new ObjectOutputStream(cliente.getOutputStream());
                entrada = new ObjectInputStream(cliente.getInputStream());

                peticion.setConsulta(23);
                salida.writeObject(peticion);
                salida.flush();
                salida.writeObject(gastos);
                salida.flush();

            } catch (IOException ex) {
                ex.printStackTrace();
            }

            return null;
        }
    }

    /**
     * Clase AsyncTask para obtener toda la información del usuario
     */
    public class obtenerInformacionPerfil extends AsyncTask<String, Void, Void> {

        Socket cliente = null;
        ObjectOutputStream salida = null;
        ObjectInputStream entrada = null;

        @Override
        protected Void doInBackground(String... strings) {
            try {

                cliente = new Socket(settings.obtenerSettings().get(0).getAddress(), settings.obtenerSettings().get(0).getPort());
                salida = new ObjectOutputStream(cliente.getOutputStream());
                entrada = new ObjectInputStream(cliente.getInputStream());

                peticion.setConsulta(22);
                salida.writeObject(peticion);

                salida.writeObject(usuarios);
                usuarios = (Usuarios) entrada.readObject();

            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /**
     * Método para obtener la imagen de perfil del usuario
     * @param usuarios Objeto usuarios con la imagen de perfil cifrada
     */
    public void obtenerImagenPerfil(Usuarios usuarios) {
        try {
            if (usuarios.getImagen().equals("null") || usuarios.getImagen().length() == 0) {
                imagen_perfil.setImageResource(R.mipmap.user);
            } else {
                byte[] imageByte = Base64.decode(usuarios.getImagen(), Base64.NO_WRAP);
                ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
                Bitmap photo = BitmapFactory.decodeStream(bis);
                imagen_perfil.setImageBitmap(photo);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
