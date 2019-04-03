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
        View view = inflater.inflate(R.layout.fragment_calendario, container, false);
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
                        + " de " + month
                        + " tienes 5 tareas por entregar";

                Toast.makeText(getActivity().getApplicationContext(), selectedDate, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });

        return view;
    }

}
