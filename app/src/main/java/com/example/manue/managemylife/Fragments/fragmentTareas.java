package com.example.manue.managemylife.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        listaTarea.add(new Tarea("Estudiar", true));
        listaTarea.add(new Tarea("Trabajar", false));
        listaTarea.add(new Tarea("Estudiar2",false));
        listaTarea.add(new Tarea("Trabajar2", false));
        listaTarea.add(new Tarea("Estudiar", true));
        listaTarea.add(new Tarea("Trabajar", false));
        listaTarea.add(new Tarea("Estudiar2",false));

        adapter = new TareasAdapter(listaTarea, getActivity().getApplicationContext());
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
