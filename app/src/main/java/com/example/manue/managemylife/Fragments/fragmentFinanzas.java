package com.example.manue.managemylife.Fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.manue.managemylife.Activities.ProductosActivity;
import com.example.manue.managemylife.Adapters.GastosAdapter;
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
import Compartir.Usuarios;
import de.hdodenhof.circleimageview.CircleImageView;
import vo.Gasto;
import vo.Producto;

/**
 * Fragment encargado de cargar la pantalla de finanzas
 */
public class fragmentFinanzas extends Fragment {

    SettingsClass settings = null;

    Usuarios usuarios = new Usuarios();
    Peticion peticion = new Peticion();
    Gastos gastos = new Gastos();

    private RecyclerView rList;
    private ArrayList<Gasto> listaGasto;
    private RecyclerView.Adapter adapter;
    private ArrayList<Producto> listaProducto;

    private AlertDialog.Builder builder_gasto;
    private AlertDialog dialog_gasto;

    TextView finanzas_gastos = null;
    TextView finanzas_balance = null;
    CircleImageView imagen_perfil = null;

    double suma_productos_gasto = 0.0;
    private RadioGroup radio_categoria;

    private AlertDialog.Builder builder_insertar_categoria;
    private AlertDialog dialog_insertar_categoria;
    public fragmentFinanzas() {
        // Required empty public constructor
    }


    @Override
    public void onResume() {
        super.onResume();
        executeFinanzasTask();
        executeObtenerInformacion();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.ControlGastos);
        View view = inflater.inflate(R.layout.fragment_finanzas, container, false);

        settings = new SettingsClass(getActivity().getApplicationContext());
        usuarios = fragmentPerfil.getInstance().getUsuarios();

        finanzas_gastos = view.findViewById(R.id.finanzas_gastos);
        finanzas_balance = view.findViewById(R.id.finanzas_balance);
        imagen_perfil = view.findViewById(R.id.imagenPerfil);
        finanzas_balance.setText(usuarios.getSalario()+"€");

        rList = view.findViewById(R.id.listaGastos);
        rList.setHasFixedSize(true);
        rList.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        executeFinanzasTask();
        obtenerImagenPerfil(usuarios);
        FloatingActionButton fab_gasto = view.findViewById(R.id.gastos_add);
        fab_gasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder_gasto = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.myDialog));
                View view_popup_gasto = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.popup_insertar_gasto, null);

                builder_gasto.setView(view_popup_gasto);

                dialog_gasto = builder_gasto.create();
                dialog_gasto.show();


                final EditText nombre_gasto = view_popup_gasto.findViewById(R.id.insertar_nombre_gasto);

                //final EditText tipo_gasto = view_popup_gasto.findViewById(R.id.insertar_tipo_gasto);

                final TextView categoria_textview = (TextView) view_popup_gasto.findViewById(R.id.insertar_finanza_categoria_text);
                final LinearLayout categoria = (LinearLayout) view_popup_gasto.findViewById(R.id.insertar_finanza_categoria);
                final View view_popup_categoria = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.popup_insertar_finanzas_categoria, null);
                final Button categoria_aceptar = (Button) view_popup_categoria.findViewById(R.id.btnOkFinanzas);

                radio_categoria = (RadioGroup) view_popup_categoria.findViewById(R.id.radioScreenModeFinanzas);
                categoria.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        builder_insertar_categoria = new AlertDialog.Builder(getActivity(), R.style.AlertDialog);


                        builder_insertar_categoria.setView(view_popup_categoria);
                        dialog_insertar_categoria = builder_insertar_categoria.create();
                        if (view_popup_categoria.getParent() != null) {
                            ((ViewGroup) view_popup_categoria.getParent()).removeView(view_popup_categoria);
                        }
                        dialog_insertar_categoria.show();

                        radio_categoria.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup group, int checkedId) {
                                switch (radio_categoria.getCheckedRadioButtonId()) {
                                    case R.id.radio0:
                                        categoria_textview.setText("Producto");
                                        break;
                                    case R.id.radio1:
                                        categoria_textview.setText("Servicio");
                                        break;

                                }
                            }
                        });
                        categoria_aceptar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog_insertar_categoria.dismiss();
                            }
                        });
                    }
                });

                final TextView close_gasto = view_popup_gasto.findViewById(R.id.insertar_gasto_close);
                close_gasto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog_gasto.dismiss();
                    }
                });

                final ImageView aceptar_gasto = view_popup_gasto.findViewById(R.id.insertar_aceptar_gasto);
                aceptar_gasto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gastos.setNombre_gasto(nombre_gasto.getText().toString());
                        gastos.setTipo_gasto(categoria_textview.getText().toString());
                        gastos.setPrecio_gasto(0.0);
                        executeInsertarGastosTask();
                        dialog_gasto.dismiss();
                        adapter.notifyDataSetChanged();
                        executeFinanzasTask();
                    }
                });
            }
        });

        // Método que permite eliminar deslizando cada elemento del CardView
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
                                    final AlertDialog.Builder alert = new AlertDialog.Builder(getContext(), R.style.AlertDialog);
                                    alert.setTitle("Aviso");
                                    alert.setMessage("¿Quieres eliminar el gasto permanentemente?");
                                    alert.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            int idGasto = listaGasto.get(position).getId();
                                            gastos.setId(idGasto);

                                            // Obtengo todos los productos de ese gasto y lo sumo al salario.
                                            executeObtenerProductos();
                                            // Elimino el gasto
                                            executeDeleteFinanzasTask();

                                            listaGasto.remove(position);
                                            adapter.notifyItemRemoved(position);

                                            suma_productos_gasto = 0.0;
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
        //rList.setAdapter(adapter);
        return view;
    }

    private void executeFinanzasTask() {
        mostrarGastosTask mostrarGastosTask = new mostrarGastosTask();
        mostrarGastosTask.execute();
    }

    private void executeDeleteFinanzasTask() {
        eliminarGastosTask eliminarGastosTask = new eliminarGastosTask();
        eliminarGastosTask.execute();
    }

    private void executeInsertarGastosTask() {
        insertarGastosTask insertarGastosTask = new insertarGastosTask();
        insertarGastosTask.execute();
    }

    private void executeObtenerInformacion() {
        obtenerInformacionPerfil obtenerInformacionPerfil = new obtenerInformacionPerfil();
        obtenerInformacionPerfil.execute();
    }

    private void executeUpdateSalario() {
        actualizarSalarioTask actualizarSalarioTask = new actualizarSalarioTask();
        actualizarSalarioTask.execute();
    }

    private void executeObtenerProductos() {
        obtenerProductosTask obtenerProductosTask = new obtenerProductosTask();
        obtenerProductosTask.execute();
    }

    /**
     * Clase AsyncTask para obtener lista de gastos, imprimir cantidad de gastos en pantalla y configurar adapter
     * para navegación y envio de gastos a clase encargada de mostrar productos relacionados a los gastos
     */
    public class mostrarGastosTask extends AsyncTask<ArrayList<Gasto>, Void, ArrayList<Gasto>> {

        Socket cliente = null;
        ObjectOutputStream salida = null;
        ObjectInputStream entrada = null;

        @Override
        protected ArrayList<Gasto> doInBackground(ArrayList<Gasto>... arrayLists) {
            try {
                cliente = new Socket(settings.obtenerSettings().get(0).getAddress(), settings.obtenerSettings().get(0).getPort());
                salida = new ObjectOutputStream(cliente.getOutputStream());
                entrada = new ObjectInputStream(cliente.getInputStream());

                peticion.setConsulta(14);
                salida.writeObject(peticion);

                gastos.setIdUsuario(usuarios.getId());

                salida.writeObject(gastos);

                gastos = (Gastos) entrada.readObject();
                listaGasto = gastos.getResultados_gastos();

            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return listaGasto;
        }

        @Override
        protected void onPostExecute(final ArrayList<Gasto> listaGasto) {
            super.onPostExecute(listaGasto);
            finanzas_gastos.setText(listaGasto.size()+"");
            adapter = new GastosAdapter(listaGasto, getActivity().getApplicationContext(), new GastosAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(final Gasto gasto, int position) {
                    gastos.setId(gasto.getId());
                    gastos.setPrecio_gasto(gasto.getPrecio_gasto());
                    Intent intent = new Intent(getActivity(), ProductosActivity.class);
                    intent.putExtra("gastos_productos", gastos);
                    intent.putExtra("usuarios",usuarios);
                    startActivity(intent);
                }

            });
            rList.setAdapter(adapter);
        }
    }

    /**
     * Clase AsyncTask encargada de eliminar un gasto
     */
    public class eliminarGastosTask extends AsyncTask<String, Void, Void> {

        Socket cliente = null;
        ObjectOutputStream salida = null;
        ObjectInputStream entrada = null;

        @Override
        protected Void doInBackground(String... strings) {
            try {
                cliente = new Socket(settings.obtenerSettings().get(0).getAddress(), settings.obtenerSettings().get(0).getPort());
                salida = new ObjectOutputStream(cliente.getOutputStream());
                entrada = new ObjectInputStream(cliente.getInputStream());

                peticion.setConsulta(16);
                salida.writeObject(peticion);
                salida.writeObject(gastos);

            } catch (IOException ex) {
                ex.printStackTrace();
            }

            return null;
        }
    }

    /**
     * Clase AsyncTask encargada de insertar un gasto
     */
    public class insertarGastosTask extends AsyncTask<String, Void, Void> {

        Socket cliente = null;
        ObjectOutputStream salida = null;
        ObjectInputStream entrada = null;

        @Override
        protected Void doInBackground(String... strings) {
            try {
                cliente = new Socket(settings.obtenerSettings().get(0).getAddress(), settings.obtenerSettings().get(0).getPort());
                salida = new ObjectOutputStream(cliente.getOutputStream());
                entrada = new ObjectInputStream(cliente.getInputStream());

                gastos.setIdUsuario(usuarios.getId());

                peticion.setConsulta(15);
                salida.writeObject(peticion);
                salida.writeObject(gastos);

            } catch (IOException ex) {
                ex.printStackTrace();
            }

            return null;
        }
    }

    /**
     * Clase encargada de actualizar el salario del usuario con el precio total de todos los gastos
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
     * Clase AsyncTask encargada de obtener el salario actual del usuario y mostrarlo en pantalla
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

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            finanzas_balance.setText(usuarios.getSalario()+"€");
        }
    }

    /**
     * Clase AsyncTask encargada de obtener la lista de productos relacionados a un gasto y sumarlo al salario al eliminar un gasto
     * con distintos productos en su interior.
     */
    public class obtenerProductosTask extends AsyncTask<ArrayList<Producto>, Void, ArrayList<Producto>> {

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
        protected void onPostExecute(ArrayList<Producto> productos) {
            super.onPostExecute(productos);
            for (int i = 0; i < productos.size(); i++) {
                suma_productos_gasto += productos.get(i).getPrecio_producto();
            }
            usuarios.setSalario(usuarios.getSalario() + suma_productos_gasto);
            finanzas_balance.setText(usuarios.getSalario()+"€");
            finanzas_gastos.setText(listaGasto.size()+"");
            executeUpdateSalario();

        }
    }

    /**
     * Método para obtener la imágen de perfil del usuario
     * @param usuarios Objeto usuario con la imágen cifrada
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
