package com.example.manue.managemylife.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.manue.managemylife.Activities.LoginActivity;
import com.example.manue.managemylife.Activities.SliderActivity;
import com.example.manue.managemylife.R;

public class SliderAdapter extends PagerAdapter {

    Context context;

    LayoutInflater inflater;

    // Imagenes

    int right_arrow = R.mipmap.right_arrow;

    public int[] imagenes = {
            R.mipmap.slider_1, R.mipmap.slider_2, R.mipmap.slider_3
    };
    public String[] titulos = {
            "RENDIMIENTO", "CONTROL", "AHORRA"
    };
    public String[] descripciones = {
            "Impulsa tu rendimiento ahorrando tiempo gestionando correctamente tus tareas!",
            "Controla que has logrado y que te queda por hacer con una sencilla lista de tareas dinámicas!",
            "Gestiona tu dinero! Manage My Life te permite controlar en que gastas tu dinero. ¿A qué esperas? Únete ya!"
    };

    public int[] background_colors = {
            Color.rgb(55,55,55), Color.rgb(122, 13, 17), Color.rgb(86, 81, 7)

    };

    public SliderAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide, container, false);
        LinearLayout layoutSlide = view.findViewById(R.id.slider);
        ImageView slide_imagen = (ImageView) view.findViewById(R.id.slider_imagen);
        TextView slide_titulo = (TextView) view.findViewById(R.id.slider_titulo);
        TextView slide_descripcion = (TextView) view.findViewById(R.id.slider_descripcion);
        ImageView slider_next = (ImageView) view.findViewById(R.id.slider_next);
        Button slider_start = (Button) view.findViewById(R.id.slider_start);
        layoutSlide.setBackgroundColor(background_colors[position]);
        slide_imagen.setImageResource(imagenes[position]);
        slide_titulo.setText(titulos[position]);
        slide_descripcion.setText(descripciones[position]);
        if(position == 2){
            slider_next.setImageResource(0);
            slider_start.setVisibility(View.VISIBLE);
            slider_start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, LoginActivity.class);
                    view.getContext().startActivity(intent);}
            });
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }

    @Override
    public int getCount() {
        return titulos.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return (view==(LinearLayout)o);
    }
}
