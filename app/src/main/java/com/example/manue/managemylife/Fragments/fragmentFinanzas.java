package com.example.manue.managemylife.Fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.manue.managemylife.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentFinanzas extends Fragment {

    private AlertDialog.Builder builder_gasto;
    private AlertDialog dialog_gasto;
    public fragmentFinanzas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Control de Gastos");
        View view = inflater.inflate(R.layout.fragment_finanzas, container, false);

        FloatingActionButton fab_gasto = view.findViewById(R.id.finanzas_add);
        fab_gasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder_gasto = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.myDialog));
                View view_popup_gasto = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.popup_insertar_gasto, null);

                builder_gasto.setView(view_popup_gasto);

                dialog_gasto = builder_gasto.create();
                dialog_gasto.show();

                final TextView close_tarea = view_popup_gasto.findViewById(R.id.insertar_gasto_close);
                close_tarea.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog_gasto.dismiss();
                    }
                });
            }
        });
        return view;
    }

}
