package com.example.manue.managemylife.Activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.manue.managemylife.Fragments.fragmentCalendario;
import com.example.manue.managemylife.Fragments.fragmentFinanzas;
import com.example.manue.managemylife.Fragments.fragmentPerfil;
import com.example.manue.managemylife.Fragments.fragmentTareas;
import com.example.manue.managemylife.Fragments.fragmentTareasTerminadas;
import com.example.manue.managemylife.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_actual,
                new fragmentPerfil()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
            return true;
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
            case R.id.item_menu_gastos:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_actual,
                        new fragmentFinanzas()).commit();
            case R.id.item_menu_informes:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_actual,
                        new fragmentFinanzas()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}
