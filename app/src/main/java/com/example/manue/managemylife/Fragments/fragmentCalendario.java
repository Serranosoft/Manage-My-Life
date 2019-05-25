package com.example.manue.managemylife.Fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.manue.managemylife.Activities.MainActivity;
import com.example.manue.managemylife.R;
import com.example.manue.managemylife.vo.SettingsClass;
import com.squareup.timessquare.CalendarPickerView;
import com.tapadoo.alerter.Alerter;
import com.tapadoo.alerter.OnHideAlertListener;
import com.tapadoo.alerter.OnShowAlertListener;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

import Compartir.Peticion;
import Compartir.Tareas;
import Compartir.Usuarios;
import vo.Tarea;

/**
 * Fragment encargado de cargar la pantalla del calendario
 */
public class fragmentCalendario extends Fragment{

    SettingsClass settings = null;
    Usuarios usuarios = new Usuarios();
    Peticion peticion = new Peticion();
    Tareas tareas = new Tareas();
    CalendarPickerView datePicker = null;
    private ArrayList<Tarea> listaTarea = new ArrayList<>();
    int cont_tareas = 0;
    public fragmentCalendario() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_calendario, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.Calendario);
        settings = new SettingsClass(getActivity().getApplicationContext());
        MainActivity mainActivity = (MainActivity) getActivity();

        usuarios = mainActivity.informacionUsuario();
        datePicker = view.findViewById(R.id.calendar);
        executeObtenerTareasTask();
        Date today = new Date();
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);
        datePicker.init(today, nextYear.getTime()).withSelectedDate(today);

        datePicker.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                java.sql.Date date1 = new java.sql.Date(date.getTime());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                for (int i = 0; i < listaTarea.size(); i++) {
                    if(sdf.format(date1).equals(sdf.format(listaTarea.get(i).getFecha_realizar()))) {
                        if(listaTarea.get(i).getEstado() == 0){
                            cont_tareas++;
                        }
                    }
                }
                //String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(date);
                Calendar calSelected = Calendar.getInstance();
                calSelected.setTime(date);
                String month = calSelected.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());

                String selectedDate = "Día " + calSelected.get(Calendar.DAY_OF_MONTH)
                        + " de " + month;

                Alerter.create(getActivity())
                        .setTitle(selectedDate)
                        .setText("Tienes " +cont_tareas+" tareas por entregar")
                        .setIcon(R.drawable.alerter_ic_face)
                        .setBackgroundColorRes(R.color.alerter1)
                        .setDuration(2650)
                        .enableSwipeToDismiss()
                        .enableProgress(true)
                        .setProgressColorRes(R.color.black)
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        })
                        .setOnShowListener(new OnShowAlertListener() {
                            @Override
                            public void onShow() {

                            }
                        })
                        .setOnHideListener(new OnHideAlertListener() {
                            @Override
                            public void onHide() {

                            }
                        })
                        .show();
                cont_tareas = 0;
            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });
        return view;
    }

    /**
     * Clase AsyncTask para obtener la lista de tareas y posteriormente filtrar aquellas en estado Pendiente e imprimiendolas
     * en el calendario
     */
    public class obtenerTareasTask extends AsyncTask<ArrayList<Tarea>, Void, ArrayList<Tarea>> {

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
        protected void onPostExecute(final ArrayList<Tarea> listaTarea) {
            super.onPostExecute(listaTarea);
            Calendar nextYear = Calendar.getInstance();
            nextYear.add(Calendar.YEAR, 1);

            final ArrayList<Date> dates = new ArrayList<>();
            for (int i = 0; i < listaTarea.size(); i++) {
                if(listaTarea.get(i).getEstado() == 0) {
                    dates.add(listaTarea.get(i).getFecha_realizar());
                }

            }

            Date dMin = Collections.min(dates);
            Date dMax = Collections.max(dates);
            nextYear.setTime(dMax);
            nextYear.add(Calendar.DATE, 1);
            datePicker.init(dMin, nextYear.getTime());
            datePicker.setOnInvalidDateSelectedListener(null);
            datePicker.selectDate(dMin, true);
            datePicker.highlightDates(dates);
        }
    }

    private void executeObtenerTareasTask() {
        obtenerTareasTask obtenerTareasTask = new obtenerTareasTask();
        obtenerTareasTask.execute();
    }


}
