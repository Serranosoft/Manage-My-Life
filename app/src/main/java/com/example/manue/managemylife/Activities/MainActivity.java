package com.example.manue.managemylife.Activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.manue.managemylife.Fragments.TimePickerFragment;
import com.example.manue.managemylife.Fragments.fragmentCalendario;
import com.example.manue.managemylife.Fragments.fragmentFinanzas;
import com.example.manue.managemylife.Fragments.fragmentPerfil;
import com.example.manue.managemylife.Fragments.fragmentTareas;
import com.example.manue.managemylife.Fragments.fragmentTareasTerminadas;
import com.example.manue.managemylife.R;
import com.example.manue.managemylife.Util.AlarmReceiver;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Calendar;

import Compartir.Peticion;
import Compartir.Tareas;
import Compartir.Usuarios;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, TimePickerDialog.OnTimeSetListener {

    Usuarios usuarios = new Usuarios();
    ImageView nav_imagen = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        usuarios = (Usuarios) getIntent().getSerializableExtra("login");

        Toast.makeText(this, "NOMBRE DEL USUARIO: "+usuarios.getNombre(),
                Toast.LENGTH_LONG).show();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);

        //ImageView nav_imagen = (ImageView) headerView.findViewById(R.id.nav_imagenPerfil);
        TextView nav_nombre = (TextView) headerView.findViewById(R.id.nav_nombreUsuario);
        TextView nav_usuario = (TextView) headerView.findViewById(R.id.nav_usuario);
        nav_imagen = (ImageView) headerView.findViewById(R.id.nav_imagenPerfil);

        // Configuracion de parámetros del menú lateral
        nav_nombre.setText(usuarios.getNombre());
        nav_usuario.setText(usuarios.getUsuario());
        obtenerImagenPerfil(usuarios);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_actual,
                new fragmentPerfil()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            DialogFragment newFragment = new TimePickerFragment();
            newFragment.show(getSupportFragmentManager(), "DIALOG TIME PICKER");


        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()){
            case R.id.item_menu_tareas:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_actual,
                        new fragmentTareas()).commit();
                break;
            case R.id.item_menu_perfil:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_actual,
                        new fragmentPerfil()).commit();
                break;
            case R.id.item_menu_tareas_terminadas:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_actual,
                        new fragmentTareasTerminadas()).commit();
                break;
            case R.id.item_menu_calendario:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_actual,
                        new fragmentCalendario()).commit();
                break;
            case R.id.item_menu_gastos:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_actual,
                        new fragmentFinanzas()).commit();
                break;
            case R.id.item_menu_informes:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_actual,
                        new fragmentFinanzas()).commit();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    public Usuarios informacionUsuario() {

        return usuarios;

    }
    public void obtenerImagenPerfil(Usuarios usuarios) {
        try {
            if (usuarios.getImagen().equals("null") || usuarios.getImagen().length() == 0) {
                nav_imagen.setImageResource(R.mipmap.user);
            } else {
                byte[] imageByte = Base64.decode(usuarios.getImagen(), Base64.NO_WRAP);
                ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
                Bitmap photo = BitmapFactory.decodeStream(bis);
                nav_imagen.setImageBitmap(photo);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        System.out.println("HORA QUE SE REGISTRARÁ LA NOTIFICACION: "+hourOfDay +" minuto: "+minute);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay );
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 1);

        Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager)this.getSystemService(this.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }
}
