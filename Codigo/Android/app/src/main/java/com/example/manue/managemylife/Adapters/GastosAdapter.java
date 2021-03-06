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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import vo.Gasto;
import vo.Tarea;

/**
 * Clase Adapter encargada de gestionar la información para mostrarla en el RecyclerView
 */
public class GastosAdapter extends RecyclerView.Adapter<GastosAdapter.ViewHolder> {

    private List<Gasto> gastos = new ArrayList<>();
    private Context context;
    private OnItemClickListener listener;

    /**
     * Constructor del Adaptador de gastos
     * @param gastos Lista de gastos pasado por parámetro
     * @param context Fragment o Activity al que hace referencia
     * @param listener Objeto listener de tipo click
     */
    public GastosAdapter(List<Gasto> gastos, Context context, OnItemClickListener listener) {
        this.gastos = gastos;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.gasto, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final Gasto gasto = gastos.get(i);

        if(gasto.getNombre_gasto().length() > 30){
            viewHolder.nombreGasto.setText(gasto.getNombre_gasto().substring(0,30)+"...");
        }else{
            viewHolder.nombreGasto.setText(gasto.getNombre_gasto());
        }

        double res = Math.round(gasto.getPrecio_gasto()*100.0)/100.0;
        viewHolder.precioGasto.setText(res+"€");

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(gasto, i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return gastos.size();
    }

    /**
     * Clase ViewHolder para obtener los distintos datos con los que se trabajará desde el adaptador
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nombreGasto;
        public TextView precioGasto;

        public ViewHolder(View itemView) {
            super(itemView);

            nombreGasto = itemView.findViewById(R.id.nombreGasto);
            precioGasto = itemView.findViewById(R.id.precioGasto);

        }
    }

    /**
     * Interfaz Click Listener
     */
    public interface OnItemClickListener {
        void onItemClick(Gasto gasto, int position);
    }
}
