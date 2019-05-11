package com.example.manue.managemylife.Fragments;


import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.manue.managemylife.Activities.MainActivity;
import com.example.manue.managemylife.Activities.ProductosActivity;
import com.example.manue.managemylife.Activities.SubtareasActivity;
import com.example.manue.managemylife.Adapters.GastosAdapter;
import com.example.manue.managemylife.Adapters.TareasAdapter;
import com.example.manue.managemylife.R;
import com.example.manue.managemylife.Util.SwipeableRecyclerViewTouchListener;
import com.example.manue.managemylife.vo.SettingsClass;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import Compartir.Gastos;
import Compartir.Peticion;
import Compartir.Tareas;
import Compartir.Usuarios;
import vo.Gasto;
import vo.Tarea;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentFinanzas extends Fragment {

    SettingsClass settings = null;

    Usuarios usuarios = new Usuarios();
    Peticion peticion = new Peticion();
    Gastos gastos = new Gastos();

    private RecyclerView rList;
    private ArrayList<Gasto> listaGasto;
    private RecyclerView.Adapter adapter;

    private AlertDialog.Builder builder_gasto;
    private AlertDialog dialog_gasto;

    TextView finanzas_gastos;
    public fragmentFinanzas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Control de Gastos");
        View view = inflater.inflate(R.layout.fragment_finanzas, container, false);

        MainActivity mainActivity = (MainActivity) getActivity();
        settings = new SettingsClass(getActivity().getApplicationContext());
        usuarios = mainActivity.informacionUsuario();
        System.out.println("USUARIOS QUE RECIBE FRAGMENT FINANZAS SALARIOOO: "+usuarios.getSalario());

        finanzas_gastos = view.findViewById(R.id.finanzas_gastos);
        TextView finanzas_balance = view.findViewById(R.id.finanzas_balance);
        finanzas_balance.setText(usuarios.getSalario()+"€");

        rList = view.findViewById(R.id.listaGastos);
        rList.setHasFixedSize(true);
        rList.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        executeFinanzasTask();
        FloatingActionButton fab_gasto = view.findViewById(R.id.gastos_add);
        fab_gasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder_gasto = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.myDialog));
                View view_popup_gasto = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.popup_insertar_gasto, null);

                builder_gasto.setView(view_popup_gasto);

                dialog_gasto = builder_gasto.create();
                dialog_gasto.show();


                // Nombre del gasto

                final EditText nombre_gasto = view_popup_gasto.findViewById(R.id.insertar_nombre_gasto);
                final EditText tipo_gasto = view_popup_gasto.findViewById(R.id.insertar_tipo_gasto);

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
                        gastos.setTipo_gasto(tipo_gasto.getText().toString());
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
                                return true;
                            }

                            @Override
                            public boolean canSwipeRight(int position) {
                                return true;
                            }

                            @Override
                            public void onDismissedBySwipeLeft(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (final int position : reverseSortedPositions) {
                                    int idTarea = listaGasto.get(position).getId();
                                    // Modificar gasto

                                }
                                adapter.notifyDataSetChanged();

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
                                            // llamo al metodo eliminar
                                            executeDeleteFinanzasTask();
                                            listaGasto.remove(position);
                                            adapter.notifyItemRemoved(position);
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
        return view;
    }

    public void executeFinanzasTask() {
        mostrarGastosTask mostrarGastosTask = new mostrarGastosTask();
        mostrarGastosTask.execute();
    }

    public void executeDeleteFinanzasTask() {
        eliminarGastosTask eliminarGastosTask = new eliminarGastosTask();
        eliminarGastosTask.execute();
    }

    public void executeInsertarGastosTask() {
        insertarGastosTask insertarGastosTask = new insertarGastosTask();
        insertarGastosTask.execute();
    }

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
            // Adapter + ClickListener para visualizar los productos de un gasto
            adapter = new GastosAdapter(listaGasto, getActivity().getApplicationContext(), new GastosAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(final Gasto gasto, int position) {
                    gastos.setId(gasto.getId());
                    Intent intent = new Intent(getActivity(), ProductosActivity.class);
                    intent.putExtra("gastos_productos", gastos);
                    intent.putExtra("usuarios", usuarios);
                    startActivity(intent);
                }

            });
            rList.setAdapter(adapter);
        }
    }

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

}
