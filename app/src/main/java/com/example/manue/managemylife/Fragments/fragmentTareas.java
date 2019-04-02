package com.example.manue.managemylife.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

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
        rList = view.findViewById(R.id.lista);
        rList.setHasFixedSize(true);
        rList.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        listaTarea = new ArrayList<>();
        listaTarea.add(new Tarea("Estudiar para acceso a datos", true));
        listaTarea.add(new Tarea("Hacer la compra", false));
        listaTarea.add(new Tarea("Presentar exposición de Inglés",true));
        listaTarea.add(new Tarea("Llamar a Miguel", false));
        listaTarea.add(new Tarea("Imprimir documento de identidad", true));
        listaTarea.add(new Tarea("Entregar documentación a José", false));
        listaTarea.add(new Tarea("Felicitar a Miguel por su cumpleaños",false));

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
                                for (int position : reverseSortedPositions) {
                                    listaTarea.remove(position);
                                    adapter.notifyItemRemoved(position);
                                }
                                adapter.notifyDataSetChanged();
                            }
                        });

        rList.addOnItemTouchListener(swipeTouchListener);
        return view;
    }

}
