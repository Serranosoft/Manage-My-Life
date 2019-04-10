package com.example.manue.managemylife.Fragments;


import android.app.DatePickerDialog;
import android.content.DialogInterface;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.manue.managemylife.Adapters.TareasAdapter;
import com.example.manue.managemylife.R;
import com.example.manue.managemylife.Util.SwipeableRecyclerViewTouchListener;
import com.example.manue.managemylife.Vo.Tarea;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentTareas extends Fragment{

    private RecyclerView rList;
    private List<Tarea> listaTarea;
    private RecyclerView.Adapter adapter;
    private AlertDialog.Builder builder_tarea;
    private AlertDialog dialog_tarea;
    private AlertDialog.Builder builder_insertar_categoria;
    private AlertDialog dialog_insertar_categoria;

    private Calendar c;
    private DatePickerDialog dpd;
    private RadioGroup radio_categoria;
    public fragmentTareas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tareas, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Tareas");
        rList = view.findViewById(R.id.lista);
        rList.setHasFixedSize(true);
        rList.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        listaTarea = new ArrayList<>();
        listaTarea.add(new Tarea("Estudiar para acceso a datos", true, false));
        listaTarea.add(new Tarea("Hacer la compra", false, true));
        listaTarea.add(new Tarea("Presentar exposición de Inglés",true, false));
        listaTarea.add(new Tarea("Llamar a Miguel", false, false));
        listaTarea.add(new Tarea("Imprimir documento de identidad", true, false));
        listaTarea.add(new Tarea("Entregar documentación a José", false, true));
        listaTarea.add(new Tarea("Felicitar a Miguel por su cumpleaños",false, false));

        // Adapter + ClickListener para cada ver información de cada tarea.
        adapter = new TareasAdapter(listaTarea, getActivity().getApplicationContext(), new TareasAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Tarea tarea, int position) {

                builder_tarea = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.myDialog));
                View view_popup = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.popup_subtarea, null);

                builder_tarea.setView(view_popup);

                dialog_tarea = builder_tarea.create();
                dialog_tarea.show();

                // Identificadores
                final TextView nombre_tarea = view_popup.findViewById(R.id.tarea_tarea);
                nombre_tarea.setText(tarea.getNombre());
                final ImageView prioritario_tarea = view_popup.findViewById(R.id.tarea_prioritario);
                prioritario_tarea.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(prioritario_tarea.getDrawable().getConstantState() == getResources().getDrawable(R.mipmap.no_prioritario).getConstantState()){
                            prioritario_tarea.setImageResource(R.mipmap.prioritario);
                        }else{
                            prioritario_tarea.setImageResource(R.mipmap.no_prioritario);
                        }

                    }
                });
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
                        DialogFragment newFragment  = new TimePickerFragment();
                        newFragment.show(getChildFragmentManager(), "DIALOG TIME PICKER");

                        if(alarma.getDrawable().getConstantState() == getResources().getDrawable(R.mipmap.notification_inactive).getConstantState()){
                            alarma.setImageResource(R.mipmap.notification_active);
                        }else{
                            alarma.setImageResource(R.mipmap.notification_inactive);
                        }
                    }
                });


            }
        });
        rList.setAdapter(adapter);

        // Floating action button que muestra dialog para insertar tarea
        FloatingActionButton fab = view.findViewById(R.id.btn_insertar_tarea);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder_tarea = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.myDialog));
                View view_popup_insertar = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.popup_insertar_tarea, null);

                builder_tarea.setView(view_popup_insertar);

                dialog_tarea = builder_tarea.create();
                dialog_tarea.show();

                // prioridad
                final ImageView prioritario_tarea = view_popup_insertar.findViewById(R.id.tarea_prioritario);
                prioritario_tarea.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(prioritario_tarea.getDrawable().getConstantState() == getResources().getDrawable(R.mipmap.no_prioritario).getConstantState()){
                            prioritario_tarea.setImageResource(R.mipmap.prioritario);
                        }else{
                            prioritario_tarea.setImageResource(R.mipmap.no_prioritario);
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
                                fecha_textview.setText(dayOfMonth+"/"+month+"/"+year);
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
                        if(view_popup_categoria.getParent() != null){
                            ((ViewGroup)view_popup_categoria.getParent()).removeView(view_popup_categoria);
                        }
                        dialog_insertar_categoria.show();

                        radio_categoria.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup group, int checkedId) {
                                switch (radio_categoria.getCheckedRadioButtonId()){
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
                        dialog_tarea.dismiss();
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
                                for (int position : reverseSortedPositions) {
                                    listaTarea.remove(position);
                                    adapter.notifyItemRemoved(position);
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
}
