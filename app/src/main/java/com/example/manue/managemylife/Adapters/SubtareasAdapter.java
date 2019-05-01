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

public class SubtareasAdapter extends RecyclerView.Adapter<SubtareasAdapter.ViewHolder> {

    private List<Subtarea> subtareas = new ArrayList<>();
    private Context context;
    private OnItemClickListener listener;

    public SubtareasAdapter(List<Subtarea> subtareas, Context context, OnItemClickListener listener) {
        this.subtareas = subtareas;
        this.context = context;
        this.listener = listener;
    }

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

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(subtarea, i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return subtareas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nombreSubtarea;
        public CheckBox checkBox;

        public ViewHolder(View itemView) {
            super(itemView);

            checkBox = (CheckBox) itemView.findViewById(R.id.checkboxSubTarea);
            nombreSubtarea = itemView.findViewById(R.id.nombreSubTarea);

        }
    }
    public interface OnItemClickListener {
        void onItemClick(Subtarea subtarea, int position);
    }
}


