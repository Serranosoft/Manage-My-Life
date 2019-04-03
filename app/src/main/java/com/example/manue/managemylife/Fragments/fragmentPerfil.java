package com.example.manue.managemylife.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.manue.managemylife.Activities.LoginActivity;
import com.example.manue.managemylife.Activities.SliderActivity;
import com.example.manue.managemylife.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentPerfil extends Fragment {


    public fragmentPerfil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Perfil");
        Button cerrar_sesion = (Button) view.findViewById(R.id.cerrar_sesion);
        cerrar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        RelativeLayout btn_tareas_pendientes = view.findViewById(R.id.cont_perfil_tareas_pendientes);
        btn_tareas_pendientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_actual,
                        new fragmentTareas()).commit();
            }
        });
        RelativeLayout btn_tareas_terminadas = view.findViewById(R.id.cont_perfil_tareas_terminadas);
        btn_tareas_terminadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_actual,
                        new fragmentTareasTerminadas()).commit();
            }
        });
        RelativeLayout btn_balance_actual = view.findViewById(R.id.cont_perfil_balance);
        btn_balance_actual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_actual,
                        new fragmentFinanzas()).commit();
            }
        });

        return view;

    }



}
