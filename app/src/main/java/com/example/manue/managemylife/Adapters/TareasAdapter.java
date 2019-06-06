package com.example.manue.managemylife.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import vo.Tarea;
import com.example.manue.managemylife.R;

import java.util.ArrayList;
import java.util.List;
/**
 * Clase Adapter encargada de gestionar la información para mostrarla en el RecyclerView
 */
public class TareasAdapter extends RecyclerView.Adapter<TareasAdapter.ViewHolder> {

    private List<Tarea> tareas = new ArrayList<>();
    private Context context;
    private OnItemClickListener listener;

    /**
     * Constructor del adaptador de tareas
     * @param tareas Lista de tareas
     * @param context Activity o Fragment al que hace referencia
     * @param listener Objeto click listener
     */
    public TareasAdapter(List<Tarea> tareas, Context context, OnItemClickListener listener) {
        this.tareas = tareas;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.tarea, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

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


        if(tarea.getCategoria().equals("Estudios")) {
            viewHolder.imagenTarea.setImageResource(R.mipmap.estudios);
        }else if(tarea.getCategoria().equals("Trabajo")) {
            viewHolder.imagenTarea.setImageResource(R.mipmap.trabajo);
        }else if (tarea.getCategoria().equals("Ocio")) {
            viewHolder.imagenTarea.setImageResource(R.mipmap.ocio);
        }else {
            viewHolder.imagenTarea.setImageResource(R.mipmap.clock);
        }

        if(tarea.getPrioritario() == 1) {
            viewHolder.imagenPrioritario.setImageResource(R.mipmap.prioritario);
        } else {

        }
        if(tarea.getEstado() == 1){
            viewHolder.checkBox.setChecked(true);
            viewHolder.imagenTarea.setImageResource(R.mipmap.checked);
        }else{
            viewHolder.checkBox.setChecked(false);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(tarea, i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return tareas.size();
    }
    /**
     * Clase ViewHolder para obtener los distintos datos con los que se trabajará desde el adaptador
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nombreTarea;
        public CheckBox checkBox;
        public ImageView imagenTarea;
        public ImageView imagenPrioritario;

        public ViewHolder(View itemView) {
            super(itemView);

            checkBox = (CheckBox) itemView.findViewById(R.id.checkboxTarea);
            nombreTarea = itemView.findViewById(R.id.nombreTarea);
            imagenTarea = itemView.findViewById(R.id.imagenTarea);
            imagenPrioritario = itemView.findViewById(R.id.imagen_prioritario);

        }
    }

    /**
     * Interfaz Click Listener
     */
    public interface OnItemClickListener {
        void onItemClick(Tarea tarea, int position);
    }
}
