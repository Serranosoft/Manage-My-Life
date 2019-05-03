package com.example.manue.managemylife.Activities;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import com.example.manue.managemylife.Adapters.SubtareasAdapter;
import com.example.manue.managemylife.Adapters.TareasAdapter;
import com.example.manue.managemylife.Fragments.TimePickerFragment;
import com.example.manue.managemylife.Fragments.fragmentTareas;
import com.example.manue.managemylife.R;
import com.example.manue.managemylife.ServerIP.ServerIP;
import com.example.manue.managemylife.Util.SwipeableRecyclerViewTouchListener;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import Compartir.Peticion;
import Compartir.Subtareas;
import Compartir.Tareas;
import Compartir.Usuarios;
import vo.Subtarea;
import vo.Tarea;

public class SubtareasActivity extends AppCompatActivity implements ServerIP {

    final String server = IP;

    Usuarios usuarios = new Usuarios();
    Peticion peticion = new Peticion();
    Tareas tareas = new Tareas();
    Subtareas subtareas = new Subtareas();

    private RecyclerView rList;
    private ArrayList<Subtarea> listaSubtarea;
    private RecyclerView.Adapter adapter;
    FloatingActionButton fab = null;

    private AlertDialog.Builder builder_tarea;
    private AlertDialog dialog_tarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtareas);

        tareas = (Tareas) getIntent().getSerializableExtra("tarea_subtarea");

        rList = findViewById(R.id.lista_subtareas);
        rList.setHasFixedSize(true);
        rList.setLayoutManager(new LinearLayoutManager(this));

        fab = findViewById(R.id.btn_insertar_subtarea);

        executeMostrarSubtareasTask();

        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                builder_tarea = new AlertDialog.Builder(new ContextThemeWrapper(SubtareasActivity.this, R.style.myDialog));
                View view_popup_insertar = LayoutInflater.from(SubtareasActivity.this).inflate(R.layout.popup_insertar_subtarea, null);

                builder_tarea.setView(view_popup_insertar);

                dialog_tarea = builder_tarea.create();
                dialog_tarea.show();

                // Nombre y descripcion de la tarea
                final EditText nombre_tarea = view_popup_insertar.findViewById(R.id.insertar_tarea_nombre);

                // Boton para insertar subtarea
                final ImageView insertar_tarea_aceptar = view_popup_insertar.findViewById(R.id.insertar_tarea_aceptar);
                insertar_tarea_aceptar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            if(nombre_tarea.getText().toString().length() < 1){
                                final AlertDialog.Builder alert = new AlertDialog.Builder(SubtareasActivity.this, R.style.AlertDialog);
                                alert.setTitle("Aviso");
                                alert.setMessage("¡Rellena todos los campos!");
                                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                });
                                alert.create().show();
                            }else{
                                subtareas.setNombre(nombre_tarea.getText().toString());
                                subtareas.setEstado(0);
                                subtareas.setID_Tarea(tareas.getId());

                                executeInsertarSubtareasTask();
                                dialog_tarea.dismiss();
                                executeMostrarSubtareasTask();
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
                                for (int position : reverseSortedPositions) {
                                    listaSubtarea.remove(position);
                                    adapter.notifyItemRemoved(position);
                                }
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (final int position : reverseSortedPositions) {
                                    final AlertDialog.Builder alert = new AlertDialog.Builder(SubtareasActivity.this, R.style.AlertDialog);
                                    alert.setTitle("Aviso");
                                    alert.setMessage("¿Quieres eliminar la subtarea permanentemente?");
                                    alert.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            int idSubtarea = listaSubtarea.get(position).getId();
                                            subtareas.setId(idSubtarea);
                                            // llamo al metodo eliminar
                                            executeDeleteSubtareasTask();
                                            listaSubtarea.remove(position);
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
    }
    public void executeMostrarSubtareasTask() {
        mostrarSubtareasTask mostrarSubtareasTask = new mostrarSubtareasTask();
        mostrarSubtareasTask.execute();
    }
    public void executeInsertarSubtareasTask() {
        insertarSubtareasTask insertarSubtareasTask = new insertarSubtareasTask();
        insertarSubtareasTask.execute();
    }
    public void executeDeleteSubtareasTask() {
        eliminarSubtareasTask eliminarSubtareasTask = new eliminarSubtareasTask();
        eliminarSubtareasTask.execute();
    }
    public class mostrarSubtareasTask extends AsyncTask<ArrayList<Subtarea>, Void, ArrayList<Subtarea>> {

        Socket cliente = null;
        ObjectOutputStream salida = null;
        ObjectInputStream entrada = null;

        @Override
        protected ArrayList<Subtarea> doInBackground(ArrayList<Subtarea>... arrayLists) {
            try {
                cliente = new Socket(server, 4444);
                salida = new ObjectOutputStream(cliente.getOutputStream());
                entrada = new ObjectInputStream(cliente.getInputStream());

                peticion.setConsulta(8);
                salida.writeObject(peticion);
                System.out.println("id tarea que mando :"+tareas.getId());

                salida.writeObject(tareas);

                tareas = (Tareas) entrada.readObject();
                listaSubtarea = tareas.getSubtareas();
                System.out.println("Tamaño lista subtareas :"+listaSubtarea.size());
                System.out.println("Tamaño lista subtareas :"+listaSubtarea.size());

            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return listaSubtarea;
        }

        @Override
        protected void onPostExecute(final ArrayList<Subtarea> listaSubtarea) {
            super.onPostExecute(listaSubtarea);

            adapter = new SubtareasAdapter(listaSubtarea, getApplicationContext());

            rList.setAdapter(adapter);

        }
    }

    public class insertarSubtareasTask extends AsyncTask<String, Void, Void> {

        Socket cliente = null;
        ObjectOutputStream salida = null;
        ObjectInputStream entrada = null;

        @Override
        protected Void doInBackground(String... strings) {
            try {
                cliente = new Socket(server, 4444);
                salida = new ObjectOutputStream(cliente.getOutputStream());
                entrada = new ObjectInputStream(cliente.getInputStream());


                peticion.setConsulta(9);
                salida.writeObject(peticion);

                salida.writeObject(subtareas);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return null;
        }
    }
    public class eliminarSubtareasTask extends AsyncTask<String, Void, Void> {

        Socket cliente = null;
        ObjectOutputStream salida = null;
        ObjectInputStream entrada = null;

        @Override
        protected Void doInBackground(String... strings) {
            try {
                cliente = new Socket(server, 4444);
                salida = new ObjectOutputStream(cliente.getOutputStream());
                entrada = new ObjectInputStream(cliente.getInputStream());

                peticion.setConsulta(10);
                salida.writeObject(peticion);
                salida.writeObject(subtareas);

            } catch (IOException ex) {
                ex.printStackTrace();
            }

            return null;
        }
    }
}
