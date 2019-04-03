package com.example.manue.managemylife.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.manue.managemylife.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentFinanzas extends Fragment {


    public fragmentFinanzas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Control de Gastos");
        return inflater.inflate(R.layout.fragment_finanzas, container, false);
    }

}
