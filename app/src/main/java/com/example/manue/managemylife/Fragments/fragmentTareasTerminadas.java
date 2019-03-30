package com.example.manue.managemylife.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.manue.managemylife.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentTareasTerminadas extends Fragment {


    public fragmentTareasTerminadas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tareas_terminadas, container, false);
    }

}
