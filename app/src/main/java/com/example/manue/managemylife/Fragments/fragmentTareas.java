package com.example.manue.managemylife.Fragments;


import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manue.managemylife.Activities.MainActivity;
import com.example.manue.managemylife.Adapters.TareasAdapter;
import com.example.manue.managemylife.R;
import com.example.manue.managemylife.Util.SwipeableRecyclerViewTouchListener;
import com.example.manue.managemylife.Vo.Tarea;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentTareas extends Fragment {

    private RecyclerView rList;
    private List<Tarea> listaTarea;
    private RecyclerView.Adapter adapter;
    private AlertDialog.Builder builder;
    private AlertDialog dialog;
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

        adapter = new TareasAdapter(listaTarea, getActivity().getApplicationContext(), new TareasAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Tarea tarea, int position) {

                builder = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.myDialog));
                View view_popup = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.popup_subtarea, null);

                builder.setView(view_popup);

                dialog = builder.create();
                dialog.show();

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
                        dialog.dismiss();
                    }
                });
                /*final TextView descripcion_tarea = view_popup.findViewById(R.id.tarea_descripcion);
                descripcion_tarea.setText(tarea.get);*/
            }
        });
        rList.setAdapter(adapter);


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
