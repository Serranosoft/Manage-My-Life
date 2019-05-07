package com.example.manue.managemylife.Activities;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.manue.managemylife.DB.DataBaseRoom;
import com.example.manue.managemylife.Entity.Settings;
import com.example.manue.managemylife.Fragments.fragmentTareas;
import com.example.manue.managemylife.R;
import com.example.manue.managemylife.vo.SettingsClass;
import com.tapadoo.alerter.Alerter;
import com.tapadoo.alerter.OnHideAlertListener;
import com.tapadoo.alerter.OnShowAlertListener;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import Compartir.Peticion;
import Compartir.Usuarios;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    EditText usuario = null;
    EditText contraseña = null;
    Usuarios usuarios = new Usuarios();
    Peticion peticion = new Peticion();
    private AlertDialog.Builder builder_tarea;
    private AlertDialog dialog_tarea;
    SettingsClass settings = null;
    String direccionIP = "";
    int puerto = 0;
    Settings settings1 = null;
    DataBaseRoom db = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        settings = new SettingsClass(this);
        db = DataBaseRoom.getINSTANCE(this);
        //settings1 = new Settings();
        Button button1 = (Button) findViewById(R.id.login_boton);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        TextView registrarse = (TextView) findViewById(R.id.login_registrarse);
        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                view.getContext().startActivity(intent);}
        });

        ImageView information_slider = (ImageView) findViewById(R.id.information_slider);
        information_slider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SliderActivity.class);
                view.getContext().startActivity(intent);
            }
        });

        final ImageView settings_button = (ImageView) findViewById(R.id.settings);
        settings_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder_tarea = new AlertDialog.Builder(new ContextThemeWrapper(LoginActivity.this, R.style.myDialog));
                View view_popup = LayoutInflater.from(getApplicationContext()).inflate(R.layout.popup_settings, null);

                builder_tarea.setView(view_popup);

                dialog_tarea = builder_tarea.create();
                dialog_tarea.show();
                obtenerSettings();

                final EditText direccionIP_editText = (EditText) view_popup.findViewById(R.id.direccionIP);
                final EditText puerto_editText = (EditText) view_popup.findViewById(R.id.puerto);

                ImageView confirmar_settings = (ImageView) view_popup.findViewById(R.id.aceptar_settings);
                confirmar_settings.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if (settings1 == null) {
                            direccionIP = direccionIP_editText.getText().toString();
                            puerto = Integer.valueOf(puerto_editText.getText().toString());

                            settings1 = new Settings();
                            settings1.setAddress(direccionIP);
                            settings1.setPort(puerto);
                            addSettings();
                        }else {
                            direccionIP = direccionIP_editText.getText().toString();
                            puerto = Integer.valueOf(puerto_editText.getText().toString());
                            settings1.setAddress(direccionIP);
                            settings1.setPort(puerto);
                            editarSettings();
                        }
                        Alerter.create(LoginActivity.this)
                                .setTitle("Conexion establecida!")
                                .setText("Se ha establecido conexión con "+direccionIP +" a traves del puerto "+puerto)
                                .setIcon(R.drawable.alerter_ic_face)
                                .setBackgroundColorRes(R.color.alerter_login)
                                .setDuration(2100)
                                .enableSwipeToDismiss() //seems to not work well with OnClickListener
                                .enableProgress(true)
                                .setProgressColorRes(R.color.black)
                                .setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        //do something when Alerter message was clicked

                                    }
                                })
                                .setOnShowListener(new OnShowAlertListener() {
                                    @Override
                                    public void onShow() {
                                        //do something when Alerter message shows
                                    }
                                })
                                .setOnHideListener(new OnHideAlertListener() {
                                    @Override
                                    public void onHide() {
                                        //do something when Alerter message hides
                                    }
                                })
                                .show();

                    }
                });

            }
        });

        usuario = (EditText) findViewById(R.id.login_email);
        contraseña = (EditText) findViewById(R.id.login_pass);
    }

    public void login() {

        loginTask loginTask = new loginTask();
        loginTask.execute();

    }
    public void addSettings() {
        insertarSettingsTask insertarSettingsTask = new insertarSettingsTask();
        insertarSettingsTask.execute();
    }
    public void obtenerSettings() {
        obtenerSettingsTask obtenerSettingsTask = new obtenerSettingsTask();
        obtenerSettingsTask.execute();
    }
    public void editarSettings() {
        updateSettingsTask updateSettingsTask = new updateSettingsTask();
        updateSettingsTask.execute();
    }

    public class loginTask extends AsyncTask<String, Void, Void> {

        Socket cliente = null;
        ObjectOutputStream salida = null;
        ObjectInputStream entrada = null;

        @Override
        protected Void doInBackground(String... strings) {
            try {

                System.out.println("Settings configurado ;:" +settings.obtenerSettings().get(0).getAddress()+" "+settings.obtenerSettings().get(0).getPort());
                cliente = new Socket(settings.obtenerSettings().get(0).getAddress(), settings.obtenerSettings().get(0).getPort());

                salida = new ObjectOutputStream(cliente.getOutputStream());
                entrada = new ObjectInputStream(cliente.getInputStream());

                peticion.setConsulta(2);
                salida.writeObject(peticion);

                MessageDigest digest = MessageDigest.getInstance("MD5");
                byte[] hash = digest.digest(contraseña.getText().toString().getBytes(StandardCharsets.UTF_8));
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < hash.length; i++) {
                    sb.append(Integer.toString((hash[i] & 0xff) + 0x100,16).substring(1));
                }
                usuarios.setUsuario(usuario.getText().toString());
                usuarios.setContraseña(sb.toString());


                salida.writeObject(usuarios);
                usuarios = (Usuarios) entrada.readObject();
                Handler handler =  new Handler(Looper.getMainLooper());
                handler.post( new Runnable(){
                    public void run(){
                        if (usuarios.isExiste()) {

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("login", usuarios);
                            startActivity(intent);
                        }else{
                            final AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this, R.style.AlertDialog);

                            alert.setTitle("Aviso");
                            alert.setMessage("Usuario / Contraseña incorrecto! ");
                            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });

                            alert.create().show();
                        }
                    }
                });
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public class insertarSettingsTask extends AsyncTask<String, Void, Long> {

        @Override
        protected Long doInBackground(String...strings) {
            long id = 0;

            id = db.settingsDAO().insertSettings(settings1);


            System.out.println("RESULTADO INSERT: "+id);
            return id;
        }
    }

    public class obtenerSettingsTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String...strings) {
            try{
                settings1 = db.settingsDAO().getAll().get(0);
            }catch (Exception e){

            }
            return null;
        }
    }
    public class updateSettingsTask extends AsyncTask<String, Void, Long> {

        @Override
        protected Long doInBackground(String...strings) {
            long id = 0;

            id = db.settingsDAO().updateSettings(settings1);
            System.out.println("RESULTADO UPDATE: "+id);
            return id;
        }
    }

}

