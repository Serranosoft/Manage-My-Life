package com.example.manue.managemylife.Activities;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.manue.managemylife.Adapters.SubtareasAdapter;
import com.example.manue.managemylife.R;
import com.example.manue.managemylife.Util.SwipeableRecyclerViewTouchListener;
import com.example.manue.managemylife.vo.SettingsClass;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import Compartir.Peticion;
import Compartir.Subtareas;
import Compartir.Tareas;
import vo.Subtarea;

public class SubtareasActivity extends AppCompatActivity {

    //final String server = IP;
    SettingsClass settings = null;
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
        settings = new SettingsClass(this);
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
                                try {
                                    Thread.sleep(200);

                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
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
                                return true;
                            }

                            @Override
                            public boolean canSwipeRight(int position) {
                                return true;
                            }

                            @Override
                            public void onDismissedBySwipeLeft(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (final int position : reverseSortedPositions) {
                                    int idSubtarea = listaSubtarea.get(position).getId();
                                    if(listaSubtarea.get(position).getEstado() == 1){
                                        subtareas.setId(idSubtarea);
                                        subtareas.setEstado(0);
                                    }else{
                                        subtareas.setId(idSubtarea);
                                        subtareas.setEstado(1);
                                    }
                                    executeStatusSubtareasTask();
                                    executeMostrarSubtareasTask();

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
    private void executeMostrarSubtareasTask() {
        mostrarSubtareasTask mostrarSubtareasTask = new mostrarSubtareasTask();
        mostrarSubtareasTask.execute();
    }
    private void executeInsertarSubtareasTask() {
        insertarSubtareasTask insertarSubtareasTask = new insertarSubtareasTask();
        insertarSubtareasTask.execute();
    }
    private void executeDeleteSubtareasTask() {
        eliminarSubtareasTask eliminarSubtareasTask = new eliminarSubtareasTask();
        eliminarSubtareasTask.execute();
    }
    private void executeStatusSubtareasTask() {
        actualizarEstadoSubtarea actualizarEstadoSubtarea = new actualizarEstadoSubtarea();
        actualizarEstadoSubtarea.execute();
    }

    /**
     * Clase AsyncTask que devuelve las subtareas relacionadas a una tarea
     */
    public class mostrarSubtareasTask extends AsyncTask<ArrayList<Subtarea>, Void, ArrayList<Subtarea>> {

        Socket cliente = null;
        ObjectOutputStream salida = null;
        ObjectInputStream entrada = null;

        @Override
        protected ArrayList<Subtarea> doInBackground(ArrayList<Subtarea>... arrayLists) {
            try {
                cliente = new Socket(settings.obtenerSettings().get(0).getAddress(), settings.obtenerSettings().get(0).getPort());
                salida = new ObjectOutputStream(cliente.getOutputStream());
                entrada = new ObjectInputStream(cliente.getInputStream());

                peticion.setConsulta(8);
                salida.writeObject(peticion);

                salida.writeObject(tareas);

                tareas = (Tareas) entrada.readObject();
                listaSubtarea = tareas.getSubtareas();

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

    /**
     * Clase AsyncTask que permite insertar subtareas en el sistema
     */
    public class insertarSubtareasTask extends AsyncTask<String, Void, Void> {

        Socket cliente = null;
        ObjectOutputStream salida = null;
        ObjectInputStream entrada = null;

        @Override
        protected Void doInBackground(String... strings) {
            try {
                cliente = new Socket(settings.obtenerSettings().get(0).getAddress(), settings.obtenerSettings().get(0).getPort());
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

    /**
     * Clase AsyncTask que permite eliminar subtareas del sistema
     */
    public class eliminarSubtareasTask extends AsyncTask<String, Void, Void> {

        Socket cliente = null;
        ObjectOutputStream salida = null;
        ObjectInputStream entrada = null;

        @Override
        protected Void doInBackground(String... strings) {
            try {
                cliente = new Socket(settings.obtenerSettings().get(0).getAddress(), settings.obtenerSettings().get(0).getPort());
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

    /**
     * Clase AsyncTask que permite actualizar el estado de Pendiente/Terminado o viceversa de una subtarea
     */
    public class actualizarEstadoSubtarea extends AsyncTask<String, Void, Void> {

        Socket cliente = null;
        ObjectOutputStream salida = null;
        ObjectInputStream entrada = null;

        @Override
        protected Void doInBackground(String... strings) {
            try {
                cliente = new Socket(settings.obtenerSettings().get(0).getAddress(), settings.obtenerSettings().get(0).getPort());
                salida = new ObjectOutputStream(cliente.getOutputStream());
                entrada = new ObjectInputStream(cliente.getInputStream());

                peticion.setConsulta(13);
                salida.writeObject(peticion);
                salida.writeObject(subtareas);

            } catch (IOException ex) {
                ex.printStackTrace();
            }

            return null;
        }
    }
}
