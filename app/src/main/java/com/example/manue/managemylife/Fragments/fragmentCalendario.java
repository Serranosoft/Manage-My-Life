package com.example.manue.managemylife.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.manue.managemylife.Activities.MainActivity;
import com.example.manue.managemylife.R;
import com.squareup.timessquare.CalendarPickerView;
import com.tapadoo.alerter.Alerter;
import com.tapadoo.alerter.OnHideAlertListener;
import com.tapadoo.alerter.OnShowAlertListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentCalendario extends Fragment {


    public fragmentCalendario() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_calendario, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Calendario");

        Date today = new Date();
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);

        CalendarPickerView datePicker = view.findViewById(R.id.calendar);
        datePicker.init(today, nextYear.getTime()).withSelectedDate(today);

        final ArrayList<Date> dates = new ArrayList<>();
        dates.add(new Date(2019 - 1900, 4, 3));
        dates.add(new Date(2019 - 1900, 4, 15));
        dates.add(new Date(2019 - 1900, 5, 6));
        dates.add(new Date(2019 - 1900, 5, 25));

        Date dMin = Collections.min(dates);
        Date dMax = Collections.max(dates);
        nextYear.setTime(dMax);
        nextYear.add(Calendar.DATE, 1);
        datePicker.init(dMin, nextYear.getTime());
        datePicker.setOnInvalidDateSelectedListener(null);
        datePicker.selectDate(dMin, true);
        datePicker.highlightDates(dates);
        datePicker.setDateSelectableFilter(new CalendarPickerView.DateSelectableFilter() {
            @Override
            public boolean isDateSelectable(Date date)
            {
                return dates.indexOf(date) >= 0;
            }
        });

        datePicker.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                //String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(date);

                Calendar calSelected = Calendar.getInstance();
                calSelected.setTime(date);
                String month = calSelected.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());

                String selectedDate = "Día " + calSelected.get(Calendar.DAY_OF_MONTH)
                        + " de " + month;

                showAlerter(selectedDate, "Tienes 5 tareas por entregar");
            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });

        return view;
    }

    public void showAlerter(String mensaje1, String mensaje2) {
        Alerter.create(getActivity())
                .setTitle(mensaje1)
                .setText(mensaje2)
                .setIcon(R.drawable.alerter_ic_face)
                .setBackgroundColorRes(R.color.alerter1)
                .setDuration(2650)
                .enableSwipeToDismiss() //seems to not work well with OnClickListener
                .enableProgress(true)
                .setProgressColorRes(R.color.black)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //do something when Alerter message was clicked
                    }
                })
                .setOnShowListener(new OnShowAlertListener() {
                    @Override
                    public void onShow() {
                        //do something when Alerter message shows
                    }
                })
                .setOnHideListener(new OnHideAlertListener() {
                    @Override
                    public void onHide() {
                        //do something when Alerter message hides
                    }
                })
                .show();
    }


}
