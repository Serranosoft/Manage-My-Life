package com.example.manue.managemylife.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.manue.managemylife.R;

import java.util.ArrayList;
import java.util.List;

import vo.Tarea;

public class TareasTerminadasAdapter extends RecyclerView.Adapter<TareasTerminadasAdapter.ViewHolder> {
    private List<Tarea> tareas = new ArrayList<>();
    private Context context;

    public TareasTerminadasAdapter(List<Tarea> tareas, Context context) {
        this.tareas = tareas;
        this.context = context;
    }

    @NonNull
    @Override
    public TareasTerminadasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.tarea_terminada, viewGroup, false);
        TareasTerminadasAdapter.ViewHolder viewHolder = new TareasTerminadasAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final Tarea tarea = tareas.get(i);

        if(tarea.getNombre().length() > 30){
            viewHolder.nombreTarea.setText(tarea.getNombre().substring(0,30)+"...");
        }else{
            viewHolder.nombreTarea.setText(tarea.getNombre());
        }

    }

    @Override
    public int getItemCount() {
        return tareas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nombreTarea;

        public ViewHolder(View itemView) {
            super(itemView);
            nombreTarea = itemView.findViewById(R.id.nombreTareaTerminada);

        }
    }
}
