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
import com.example.manue.managemylife.Activities.SubtareasActivity;
import com.example.manue.managemylife.Adapters.TareasAdapter;
import com.example.manue.managemylife.R;
import com.example.manue.managemylife.Util.SwipeableRecyclerViewTouchListener;
import com.example.manue.managemylife.vo.SettingsClass;

import Compartir.Peticion;
import Compartir.Tareas;
import Compartir.Usuarios;
import vo.Tarea;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentTareas extends Fragment {

    SettingsClass settings = null;

    Usuarios usuarios = new Usuarios();
    Peticion peticion = new Peticion();
    Tareas tareas = new Tareas();

    private RecyclerView rList;
    private ArrayList<Tarea> listaTarea;
    private RecyclerView.Adapter adapter;
    private AlertDialog.Builder builder_tarea;
    private AlertDialog dialog_tarea;
    private AlertDialog.Builder builder_modificar_tarea;
    private AlertDialog dialog_modificar_tarea;
    private AlertDialog.Builder builder_insertar_categoria;
    private AlertDialog dialog_insertar_categoria;



    private Calendar c;
    private DatePickerDialog dpd;
    private RadioGroup radio_categoria;

    FloatingActionButton fab = null;
    int prioritario = 0;

    public fragmentTareas() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tareas, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Tareas");

        MainActivity mainActivity = (MainActivity) getActivity();
        settings = new SettingsClass(getActivity().getApplicationContext());
        usuarios = mainActivity.informacionUsuario();


        rList = view.findViewById(R.id.lista);
        rList.setHasFixedSize(true);
        rList.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        fab = view.findViewById(R.id.btn_insertar_tarea);

        executeTareasTask();


        // Floating action button que muestra dialog para insertar tarea

        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                builder_tarea = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.myDialog));
                View view_popup_insertar = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.popup_insertar_tarea, null);

                builder_tarea.setView(view_popup_insertar);

                dialog_tarea = builder_tarea.create();
                dialog_tarea.show();

                // Nombre y descripcion de la tarea
                final EditText nombre_tarea = view_popup_insertar.findViewById(R.id.insertar_tarea_nombre);
                final EditText descripcion_tarea = view_popup_insertar.findViewById(R.id.insertar_tarea_descripcion);

                // Insertar Prioridad
                final ImageView prioritario_tarea = view_popup_insertar.findViewById(R.id.insertar_tarea_prioritario);
                prioritario_tarea.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (prioritario_tarea.getDrawable().getConstantState() == getResources().getDrawable(R.mipmap.no_prioritario).getConstantState()) {
                            prioritario_tarea.setImageResource(R.mipmap.prioritario);
                            prioritario = 1;
                        } else {
                            prioritario_tarea.setImageResource(R.mipmap.no_prioritario);
                            prioritario = 0;
                        }

                    }
                });

                // fecha en la que se debe terminar
                final TextView fecha_textview = (TextView) view_popup_insertar.findViewById(R.id.insertar_tarea_fecha_text);
                final LinearLayout fecha = (LinearLayout) view_popup_insertar.findViewById(R.id.insertar_tarea_fecha);
                fecha.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        c = Calendar.getInstance();
                        int day = c.get(Calendar.DAY_OF_MONTH);
                        int month = c.get(Calendar.MONTH);
                        int year = c.get(Calendar.YEAR);

                        dpd = new DatePickerDialog(getActivity(), R.style.AlertDialog, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                //fecha_textview.setText(dayOfMonth + "-" + month + 1 + "-" + year);
                                fecha_textview.setText(year + "-" + month +1 +"-" +dayOfMonth);
                            }
                        }, day, month, year);
                        dpd.show();
                    }
                });

                // Categoria
                final TextView categoria_textview = (TextView) view_popup_insertar.findViewById(R.id.insertar_tarea_categoria_text);
                final LinearLayout categoria = (LinearLayout) view_popup_insertar.findViewById(R.id.insertar_tarea_categoria);
                final View view_popup_categoria = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.popup_insertar_tarea_categoria, null);
                final Button categoria_aceptar = (Button) view_popup_categoria.findViewById(R.id.btnOk);

                radio_categoria = (RadioGroup) view_popup_categoria.findViewById(R.id.radioScreenMode);
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
                                        categoria_textview.setText("Ocio");

                                        break;
                                    case R.id.radio1:
                                        categoria_textview.setText("Trabajo");

                                        break;
                                    case R.id.radio2:
                                        categoria_textview.setText("Estudios");

                                        break;
                                    case R.id.radio3:
                                        categoria_textview.setText("Otros");

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

                // Boton para insertar tarea
                final ImageView insertar_tarea_aceptar = view_popup_insertar.findViewById(R.id.insertar_tarea_aceptar);
                insertar_tarea_aceptar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        try {
                            tareas.setNombre(nombre_tarea.getText().toString());
                            tareas.setDescripcion(descripcion_tarea.getText().toString());
                            tareas.setCategoria(categoria_textview.getText().toString());
                            tareas.setEstado(0);
                            tareas.setPrioritario(prioritario);
                            java.sql.Date inscrita = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                            tareas.setFecha_inscrita(inscrita);
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

                            java.util.Date terminar_temp = simpleDateFormat.parse(fecha_textview.getText().toString());
                            java.sql.Date terminar = new java.sql.Date(terminar_temp.getTime());
                            tareas.setFecha_realizar(terminar);

                            executeInsertarTareasTask();
                            dialog_tarea.dismiss();
                            adapter.notifyDataSetChanged();
                            executeTareasTask();

                        } catch (ParseException e) {
                            e.printStackTrace();
                            final AlertDialog.Builder alert = new AlertDialog.Builder(getContext(), R.style.AlertDialog);
                            alert.setTitle("Aviso");
                            alert.setMessage("¡Rellena todos los campos!");
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
                                    int idTarea = listaTarea.get(position).getId();
                                    if(listaTarea.get(position).getEstado() == 1){
                                        tareas.setId(idTarea);
                                        tareas.setEstado(0);
                                    }else{
                                        tareas.setId(idTarea);
                                        tareas.setEstado(1);
                                    }
                                    executeStatusTareasTask();
                                    executeTareasTask();

                                }
                                adapter.notifyDataSetChanged();

                            }

                            @Override
                            public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (final int position : reverseSortedPositions) {
                                    final AlertDialog.Builder alert = new AlertDialog.Builder(getContext(), R.style.AlertDialog);
                                    alert.setTitle("Aviso");
                                    alert.setMessage("¿Quieres eliminar la tarea permanentemente?");
                                    alert.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            int idTarea = listaTarea.get(position).getId();
                                            tareas.setId(idTarea);
                                            // llamo al metodo eliminar
                                            executeDeleteTareasTask();
                                            listaTarea.remove(position);
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

    public void executeTareasTask() {
        mostrarTareasTask mostrarTareasTask = new mostrarTareasTask();
        mostrarTareasTask.execute();
    }

    public void executeInsertarTareasTask() {
        insertarTareasTask insertarTareasTask = new insertarTareasTask();
        insertarTareasTask.execute();
    }
    public void executeDeleteTareasTask() {
        eliminarTareasTask eliminarTareasTask = new eliminarTareasTask();
        eliminarTareasTask.execute();
    }
    public void executeUpdateTareasTask() {
        modificarTareasTask modificarTareasTask = new modificarTareasTask();
        modificarTareasTask.execute();
    }
    public void executeStatusTareasTask() {

        actualizarEstadoTarea actualizarEstadoTarea = new actualizarEstadoTarea();
        actualizarEstadoTarea.execute();
    }


    public class mostrarTareasTask extends AsyncTask<ArrayList<Tarea>, Void, ArrayList<Tarea>> {

        Socket cliente = null;
        ObjectOutputStream salida = null;
        ObjectInputStream entrada = null;

        @Override
        protected ArrayList<Tarea> doInBackground(ArrayList<Tarea>... arrayLists) {
            try {
                cliente = new Socket(settings.obtenerSettings().get(0).getAddress(), settings.obtenerSettings().get(0).getPort());
                salida = new ObjectOutputStream(cliente.getOutputStream());
                entrada = new ObjectInputStream(cliente.getInputStream());

                peticion.setConsulta(3);
                salida.writeObject(peticion);

                tareas.setIdUsuario(usuarios.getId());

                salida.writeObject(tareas);

                tareas = (Tareas) entrada.readObject();
                listaTarea = tareas.getResultados_tareas();

            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return listaTarea;
        }

        @Override
        protected void onPostExecute(final ArrayList<Tarea> listaTarea) {
            super.onPostExecute(listaTarea);

            // Adapter + ClickListener para cada ver información de cada tarea.
            adapter = new TareasAdapter(listaTarea, getActivity().getApplicationContext(), new TareasAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(final Tarea tarea, int position) {

                    builder_tarea = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.myDialog));
                    View view_popup = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.popup_infotarea, null);

                    builder_tarea.setView(view_popup);

                    dialog_tarea = builder_tarea.create();
                    dialog_tarea.show();

                    tareas.setId(tarea.getId());

                    // Identificadores
                    final TextView nombre_tarea = view_popup.findViewById(R.id.tarea_tarea);
                    nombre_tarea.setText(tarea.getNombre());
                    final TextView descripcion_tarea = view_popup.findViewById(R.id.tarea_descripcion);
                    descripcion_tarea.setText(tarea.getDescripcion());
                    final TextView categoria_tarea = view_popup.findViewById(R.id.tarea_categoria);
                    categoria_tarea.setText(tarea.getCategoria());
                    final TextView tarea_fecha = view_popup.findViewById(R.id.tarea_fecha);
                    tarea_fecha.setText(tarea.getFecha_realizar().toString());
                    final ImageView prioritario_tarea = view_popup.findViewById(R.id.tarea_prioritario);
                    if (tarea.getPrioritario() == 1) {
                        prioritario_tarea.setImageResource(R.mipmap.prioritario);
                    }
                    final TextView close_tarea = view_popup.findViewById(R.id.tarea_close);
                    close_tarea.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog_tarea.dismiss();
                        }
                    });
                    final ImageView alarma = view_popup.findViewById(R.id.tarea_alarma);
                    alarma.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DialogFragment newFragment = new TimePickerFragment();
                            newFragment.show(getChildFragmentManager(), "DIALOG TIME PICKER");

                            if (alarma.getDrawable().getConstantState() == getResources().getDrawable(R.mipmap.notifications_inactive).getConstantState()) {
                                alarma.setImageResource(R.mipmap.notification_active);
                            } else {
                                alarma.setImageResource(R.mipmap.notifications_inactive);
                            }
                        }
                    });
                    final Button subtareas = view_popup.findViewById(R.id.tarea_subtareas);
                    subtareas.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent intent = new Intent(getActivity(), SubtareasActivity.class);
                            intent.putExtra("tarea_subtarea", tareas);
                            startActivity(intent);
                        }
                    });


                    // FUNCIONALIDAD DE MODIFICAR TAREA

                    final ImageView modificar_tarea = view_popup.findViewById(R.id.tarea_edit);
                    modificar_tarea.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            builder_modificar_tarea = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.myDialog));
                            View view_modificar_tarea_popup = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.popup_modificar_tarea, null);

                            builder_modificar_tarea.setView(view_modificar_tarea_popup);

                            dialog_modificar_tarea = builder_modificar_tarea.create();
                            dialog_modificar_tarea.show();

                            final EditText nombre_tarea = view_modificar_tarea_popup.findViewById(R.id.modificar_tarea_nombre);
                            nombre_tarea.setText(tarea.getNombre());
                            final EditText descripcion_tarea = view_modificar_tarea_popup.findViewById(R.id.modificar_tarea_descripcion);
                            descripcion_tarea.setText(tarea.getDescripcion());
                            final ImageView prioritario_tarea = view_modificar_tarea_popup.findViewById(R.id.modificar_tarea_prioritario);

                            if (tarea.getPrioritario() == 1) {
                                prioritario_tarea.setImageResource(R.mipmap.prioritario);
                            }

                            prioritario_tarea.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (prioritario_tarea.getDrawable().getConstantState() == getResources().getDrawable(R.mipmap.no_prioritario).getConstantState()) {
                                        prioritario_tarea.setImageResource(R.mipmap.prioritario);
                                        prioritario = 1;
                                    } else {
                                        prioritario_tarea.setImageResource(R.mipmap.no_prioritario);
                                        prioritario = 0;
                                    }

                                }
                            });
                            // fecha
                            final TextView fecha_textview = (TextView) view_modificar_tarea_popup.findViewById(R.id.modificar_tarea_fecha_text);
                            fecha_textview.setText(tarea.getFecha_realizar().toString());
                            final LinearLayout fecha = (LinearLayout) view_modificar_tarea_popup.findViewById(R.id.modificar_tarea_fecha);
                            fecha.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    c = Calendar.getInstance();
                                    int day = c.get(Calendar.DAY_OF_MONTH);
                                    int month = c.get(Calendar.MONTH);
                                    int year = c.get(Calendar.YEAR);

                                    dpd = new DatePickerDialog(getActivity(), R.style.AlertDialog, new DatePickerDialog.OnDateSetListener() {
                                        @Override
                                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                            //fecha_textview.setText(dayOfMonth + "-" + month + 1 + "-" + year);
                                            fecha_textview.setText(year + "-" + month +1 +"-" +dayOfMonth);
                                        }
                                    }, day, month, year);
                                    dpd.show();
                                }
                            });

                            // Categoria
                            final TextView categoria_textview = (TextView) view_modificar_tarea_popup.findViewById(R.id.modificar_tarea_categoria_text);
                            categoria_textview.setText(tarea.getCategoria());
                            final LinearLayout categoria = (LinearLayout) view_modificar_tarea_popup.findViewById(R.id.modificar_tarea_categoria);
                            final View view_popup_categoria = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.popup_insertar_tarea_categoria, null);
                            final Button categoria_aceptar = (Button) view_popup_categoria.findViewById(R.id.btnOk);
                            radio_categoria = (RadioGroup) view_popup_categoria.findViewById(R.id.radioScreenMode);
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
                                                    categoria_textview.setText("Ocio");

                                                    break;
                                                case R.id.radio1:
                                                    categoria_textview.setText("Trabajo");

                                                    break;
                                                case R.id.radio2:
                                                    categoria_textview.setText("Estudios");

                                                    break;
                                                case R.id.radio3:
                                                    categoria_textview.setText("Otros");
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
                            final TextView close_tarea = view_modificar_tarea_popup.findViewById(R.id.modificar_tarea_close);
                            close_tarea.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog_tarea.dismiss();
                                }
                            });

                            final ImageView modificar_tarea_aceptar = view_modificar_tarea_popup.findViewById(R.id.modificar_tarea_aceptar);
                            modificar_tarea_aceptar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    try {
                                        tareas.setNombre(nombre_tarea.getText().toString());
                                        tareas.setDescripcion(descripcion_tarea.getText().toString());
                                        tareas.setCategoria(categoria_textview.getText().toString());
                                        tareas.setEstado(0);
                                        tareas.setPrioritario(prioritario);
                                        java.sql.Date inscrita = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                                        tareas.setFecha_inscrita(inscrita);
                                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

                                        java.util.Date terminar_temp = null;
                                        terminar_temp = simpleDateFormat.parse(fecha_textview.getText().toString());
                                        java.sql.Date terminar = new java.sql.Date(terminar_temp.getTime());
                                        tareas.setFecha_realizar(terminar);

                                        executeUpdateTareasTask();
                                        dialog_modificar_tarea.dismiss();
                                        dialog_tarea.dismiss();
                                        executeTareasTask();

                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                        final AlertDialog.Builder alert = new AlertDialog.Builder(getContext(), R.style.AlertDialog);
                                        alert.setTitle("Aviso");
                                        alert.setMessage("¡Rellena todos los campos!");
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


                }

            });
            rList.setAdapter(adapter);
        }
    }

    public class insertarTareasTask extends AsyncTask<String, Void, Void> {

        Socket cliente = null;
        ObjectOutputStream salida = null;
        ObjectInputStream entrada = null;

        @Override
        protected Void doInBackground(String... strings) {
            try {
                cliente = new Socket(settings.obtenerSettings().get(0).getAddress(), settings.obtenerSettings().get(0).getPort());
                salida = new ObjectOutputStream(cliente.getOutputStream());
                entrada = new ObjectInputStream(cliente.getInputStream());

                tareas.setIdUsuario(usuarios.getId());

                peticion.setConsulta(4);
                salida.writeObject(peticion);
                salida.writeObject(tareas);

            } catch (IOException ex) {
                ex.printStackTrace();
            }

            return null;
        }
    }
    public class eliminarTareasTask extends AsyncTask<String, Void, Void> {

        Socket cliente = null;
        ObjectOutputStream salida = null;
        ObjectInputStream entrada = null;

        @Override
        protected Void doInBackground(String... strings) {
            try {
                cliente = new Socket(settings.obtenerSettings().get(0).getAddress(), settings.obtenerSettings().get(0).getPort());
                salida = new ObjectOutputStream(cliente.getOutputStream());
                entrada = new ObjectInputStream(cliente.getInputStream());

                peticion.setConsulta(6);
                salida.writeObject(peticion);
                salida.writeObject(tareas);

            } catch (IOException ex) {
                ex.printStackTrace();
            }

            return null;
        }
    }

    public class modificarTareasTask extends AsyncTask<String, Void, Void> {

        Socket cliente = null;
        ObjectOutputStream salida = null;
        ObjectInputStream entrada = null;

        @Override
        protected Void doInBackground(String... strings) {
            try {
                cliente = new Socket(settings.obtenerSettings().get(0).getAddress(), settings.obtenerSettings().get(0).getPort());
                salida = new ObjectOutputStream(cliente.getOutputStream());
                entrada = new ObjectInputStream(cliente.getInputStream());

                tareas.setIdUsuario(usuarios.getId());

                peticion.setConsulta(7);
                salida.writeObject(peticion);
                salida.writeObject(tareas);

            } catch (IOException ex) {
                ex.printStackTrace();
            }

            return null;
        }
    }
    public class actualizarEstadoTarea extends AsyncTask<String, Void, Void> {

        Socket cliente = null;
        ObjectOutputStream salida = null;
        ObjectInputStream entrada = null;

        @Override
        protected Void doInBackground(String... strings) {
            try {
                cliente = new Socket(settings.obtenerSettings().get(0).getAddress(), settings.obtenerSettings().get(0).getPort());
                salida = new ObjectOutputStream(cliente.getOutputStream());
                entrada = new ObjectInputStream(cliente.getInputStream());

                peticion.setConsulta(12);
                salida.writeObject(peticion);
                salida.writeObject(tareas);

            } catch (IOException ex) {
                ex.printStackTrace();
            }

            return null;
        }
    }
}
