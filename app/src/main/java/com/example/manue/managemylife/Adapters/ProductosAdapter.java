package com.example.manue.managemylife.Adapters;

import android.content.Context;
import android.graphics.Color;
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

import vo.Producto;
import vo.Subtarea;
/**
 * Clase Adapter encargada de gestionar la información para mostrarla en el RecyclerView
 */
public class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ViewHolder> {

    private List<Producto> productos = new ArrayList<>();
    private Context context;

    /**
     * Constructor del adaptador de productos
     * @param productos Lista de productos pasado por parámetro
     * @param context Activity o Fragment al que hace referencia
     */
    public ProductosAdapter(List<Producto> productos, Context context) {
        this.productos = productos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.producto, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final Producto producto = productos.get(i);

        if(producto.getNombre_producto().length() > 30){
            viewHolder.nombreProducto.setText(producto.getNombre_producto().substring(0,30)+"...");
        }else{
            viewHolder.nombreProducto.setText(producto.getNombre_producto());
        }

        viewHolder.precioProducto.setText(producto.getPrecio_producto()+"€");

    }

    @Override
    public int getItemCount() {
        return productos.size();
    }
    /**
     * Clase ViewHolder para obtener los distintos datos con los que se trabajará desde el adaptador
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nombreProducto;
        public TextView precioProducto;
        public TextView balanceUsuario;

        public ViewHolder(View itemView) {
            super(itemView);

            precioProducto = itemView.findViewById(R.id.precioProducto);
            nombreProducto = itemView.findViewById(R.id.nombreProducto);
            balanceUsuario = itemView.findViewById(R.id.productos_balance);

        }
    }
}


