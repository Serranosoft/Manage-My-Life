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

import vo.Subtarea;
/**
 * Clase Adapter encargada de gestionar la información para mostrarla en el RecyclerView
 */
public class SubtareasAdapter extends RecyclerView.Adapter<SubtareasAdapter.ViewHolder> {

    private List<Subtarea> subtareas = new ArrayList<>();
    private Context context;

    /**
     * Constructor del adaptador de subtareas
     * @param subtareas Lista de subtareas
     * @param context Activity o Fragment al que hace referencia
     */
    public SubtareasAdapter(List<Subtarea> subtareas, Context context) {
        this.subtareas = subtareas;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.subtarea, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final Subtarea subtarea = subtareas.get(i);

        if(subtarea.getNombre().length() > 30){
            viewHolder.nombreSubtarea.setText(subtarea.getNombre().substring(0,30)+"...");
        }else{
            viewHolder.nombreSubtarea.setText(subtarea.getNombre());
        }


        if(subtarea.getEstado() == 1){
            viewHolder.checkBox.setChecked(true);
        }else{
            viewHolder.checkBox.setChecked(false);
        }

    }

    @Override
    public int getItemCount() {
        return subtareas.size();
    }
    /**
     * Clase ViewHolder para obtener los distintos datos con los que se trabajará desde el adaptador
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nombreSubtarea;
        public CheckBox checkBox;

        public ViewHolder(View itemView) {
            super(itemView);

            checkBox = (CheckBox) itemView.findViewById(R.id.checkboxSubTarea);
            nombreSubtarea = itemView.findViewById(R.id.nombreSubTarea);

        }
    }
}


