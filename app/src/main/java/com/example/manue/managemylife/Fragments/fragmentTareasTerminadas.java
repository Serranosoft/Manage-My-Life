package com.example.manue.managemylife.Fragments;



import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.manue.managemylife.Activities.MainActivity;
import com.example.manue.managemylife.Adapters.TareasTerminadasAdapter;
import com.example.manue.managemylife.R;
import com.example.manue.managemylife.vo.SettingsClass;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import Compartir.Peticion;
import Compartir.Tareas;
import Compartir.Usuarios;
import vo.Tarea;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentTareasTerminadas extends Fragment {

    SettingsClass settings = null;

    Usuarios usuarios = new Usuarios();
    Peticion peticion = new Peticion();
    Tareas tareas = new Tareas();

    private RecyclerView rList;
    private ArrayList<Tarea> listaTarea;
    private ArrayList<Tarea> listaTareasTerminadas = new ArrayList<>();
    private RecyclerView.Adapter adapter;
    int tareas_terminadas = 0;
    TextView tareas_terminadas_text = null;
    public fragmentTareasTerminadas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Tareas Terminadas");

        View view = inflater.inflate(R.layout.fragment_tareas_terminadas, container, false);

        tareas_terminadas_text = (TextView) view.findViewById(R.id.tareas_terminadas);

        MainActivity mainActivity = (MainActivity) getActivity();
        settings = new SettingsClass(getActivity().getApplicationContext());
        usuarios = mainActivity.informacionUsuario();

        rList = view.findViewById(R.id.lista_tareas_terminadas);
        rList.setHasFixedSize(true);
        rList.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        executeTareasTerminadasTask();
        return view;
    }
    public void executeTareasTerminadasTask() {
        mostrarTareasTerminadasTask mostrarTareasTask = new mostrarTareasTerminadasTask();
        mostrarTareasTask.execute();
    }

    public class mostrarTareasTerminadasTask extends AsyncTask<ArrayList<Tarea>, Void, ArrayList<Tarea>> {

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
        protected void onPostExecute(ArrayList<Tarea> tareas) {
            super.onPostExecute(tareas);

            for (int i = 0; i<tareas.size();i++) {
                System.out.println(tareas.get(i).getEstado());
                if(tareas.get(i).getEstado() == 1) {
                    listaTareasTerminadas.add(tareas.get(i));
                    tareas_terminadas++;
                }
            }
            tareas_terminadas_text.setText(tareas_terminadas+"");
            adapter = new TareasTerminadasAdapter(listaTareasTerminadas, getActivity().getApplicationContext());
            rList.setAdapter(adapter);
        }
    }

}
