package com.example.manue.managemylife.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.manue.managemylife.Vo.Tarea;
import com.example.manue.managemylife.R;

import java.util.List;

public class TareasAdapter extends RecyclerView.Adapter<TareasAdapter.ViewHolder> {

    private List<Tarea> tareas;
    private Context context;
    private OnItemClickListener listener;

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


        if(tarea.isEstado()){
            viewHolder.checkBox.setChecked(true);
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

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nombreTarea;
        public CheckBox checkBox;

        public ViewHolder(View itemView) {
            super(itemView);

            checkBox = (CheckBox) itemView.findViewById(R.id.checkboxTarea);
            nombreTarea = itemView.findViewById(R.id.nombreTarea);

        }
    }
    public interface OnItemClickListener {
        void onItemClick(Tarea tarea, int position);
    }
}
