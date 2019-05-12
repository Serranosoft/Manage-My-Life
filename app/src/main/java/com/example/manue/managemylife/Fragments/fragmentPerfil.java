package com.example.manue.managemylife.Fragments;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.manue.managemylife.Activities.LoginActivity;
import com.example.manue.managemylife.Activities.MainActivity;
import com.example.manue.managemylife.Activities.ModificarPerfilActivity;
import com.example.manue.managemylife.Activities.ProductosActivity;
import com.example.manue.managemylife.R;
import com.example.manue.managemylife.vo.SettingsClass;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Compartir.Peticion;
import Compartir.Tareas;
import Compartir.Usuarios;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentPerfil extends Fragment{

    Usuarios usuarios = new Usuarios();
    Peticion peticion = new Peticion();
    Tareas tareas = new Tareas();

    int tareas_terminadas = 0;
    int tareas_pendientes = 0;
    TextView perfil_tareas_pendientes = null;
    TextView perfil_tareas_terminadas = null;
    TextView perfil_balance = null;
    TextView perfil_nombre = null;
    TextView perfil_usuario = null;
    Button modificar_perfil = null;

    SettingsClass settings = null;

    private static fragmentPerfil instance = null;
    public fragmentPerfil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        instance = this;
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Perfil");
        modificar_perfil = view.findViewById(R.id.modificar_perfil);

        modificar_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ModificarPerfilActivity.class);
                startActivity(intent);
            }
        });
        settings = new SettingsClass(getActivity().getApplicationContext());
        MainActivity mainActivity = (MainActivity) getActivity();

        usuarios = mainActivity.informacionUsuario();
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

        perfil_tareas_pendientes = (TextView) view.findViewById(R.id.perfil_tareas_pendientes);
        perfil_tareas_terminadas = (TextView) view.findViewById(R.id.perfil_tareas_terminadas);
        perfil_balance = (TextView) view.findViewById(R.id.perfil_balance);
        perfil_nombre = (TextView) view.findViewById(R.id.perfil_nombre);
        perfil_usuario = (TextView) view.findViewById(R.id.perfil_usuario);

        executeTareasTask();
        executeObtenerInfo();
        return view;

    }

    public void executeTareasTask() {
        informacionTareasTask informacionTareasTask = new informacionTareasTask();
        informacionTareasTask.execute();
    }

    public void executeObtenerInfo() {
        obtenerInformacionPerfil obtenerInformacionPerfil = new obtenerInformacionPerfil();
        obtenerInformacionPerfil.execute();
    }

    public class informacionTareasTask extends AsyncTask<Tareas, Integer, Tareas> {

        Socket cliente = null;
        ObjectOutputStream salida = null;
        ObjectInputStream entrada = null;

        @Override
        protected Tareas doInBackground(Tareas... strings) {
            try {
                cliente = new Socket(settings.obtenerSettings().get(0).getAddress(), settings.obtenerSettings().get(0).getPort());
                salida = new ObjectOutputStream(cliente.getOutputStream());
                entrada = new ObjectInputStream(cliente.getInputStream());

                peticion.setConsulta(3);

                salida.writeObject(peticion);

                tareas.setIdUsuario(usuarios.getId());
                salida.writeObject(tareas);


                tareas = (Tareas) entrada.readObject();

                for (int i = 0; i < tareas.getResultados_tareas().size(); i++) {
                    System.out.println(tareas.getResultados_tareas().get(i).getNombre());
                    if (tareas.getResultados_tareas().get(i).getEstado() == 1) {
                        tareas_terminadas++;
                    } else {
                        tareas_pendientes++;
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return tareas;
        }

        @Override
        protected void onPostExecute(Tareas tareas) {
            super.onPostExecute(tareas);
            perfil_tareas_pendientes.setText(tareas_pendientes+"");
            perfil_tareas_terminadas.setText(tareas_terminadas+"");
            /*perfil_balance.setText(usuarios.getSalario()+"");
            perfil_nombre.setText(usuarios.getNombre());
            perfil_usuario.setText(usuarios.getUsuario());*/
        }
    }

    public class obtenerInformacionPerfil extends AsyncTask<String, Void, Void> {

        Socket cliente = null;
        ObjectOutputStream salida = null;
        ObjectInputStream entrada = null;

        @Override
        protected Void doInBackground(String... strings) {
            try {

                cliente = new Socket(settings.obtenerSettings().get(0).getAddress(), settings.obtenerSettings().get(0).getPort());
                salida = new ObjectOutputStream(cliente.getOutputStream());
                entrada = new ObjectInputStream(cliente.getInputStream());

                peticion.setConsulta(22);
                salida.writeObject(peticion);

                salida.writeObject(usuarios);
                usuarios = (Usuarios) entrada.readObject();

            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            perfil_balance.setText(usuarios.getSalario()+"€");
            perfil_nombre.setText(usuarios.getNombre());
            perfil_usuario.setText(usuarios.getUsuario());

        }
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public static fragmentPerfil getInstance() {
        return instance;
    }



}
