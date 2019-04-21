package com.example.manue.managemylife.Activities;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.manue.managemylife.R;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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

        usuario = (EditText) findViewById(R.id.login_email);
        contraseña = (EditText) findViewById(R.id.login_pass);
    }

    public void login() {

        loginTask loginTask = new loginTask();
        loginTask.execute();

    }

    public class loginTask extends AsyncTask<String, Void, Void> {

        Socket cliente = null;
        ObjectOutputStream salida = null;
        ObjectInputStream entrada = null;

        @Override
        protected Void doInBackground(String... strings) {
            try {
                System.out.println("AAA");
                cliente = new Socket("172.16.1.17", 4444);
                System.out.println("BBB");
                salida = new ObjectOutputStream(cliente.getOutputStream());
                entrada = new ObjectInputStream(cliente.getInputStream());

                peticion.setConsulta(2);
                salida.writeObject(peticion);
                System.out.println("USUARIO : "+usuario.getText().toString());
                System.out.println("CONTRASEÑA : "+contraseña.getText().toString());

                MessageDigest digest = MessageDigest.getInstance("MD5");
                byte[] hash = digest.digest(contraseña.getText().toString().getBytes(StandardCharsets.UTF_8));
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < hash.length; i++) {
                    sb.append(Integer.toString((hash[i] & 0xff) + 0x100,16).substring(1));
                }
                usuarios.setUsuario(usuario.getText().toString());
                usuarios.setContraseña(sb.toString());

                System.out.println("Envio el objeto usuarios con la información del usuario");
                salida.writeObject(usuarios);
                System.out.println("Leo el resultado de la consulta");
                usuarios = (Usuarios) entrada.readObject();
                System.out.println("Lo ha leido");
                Handler handler =  new Handler(Looper.getMainLooper());
                handler.post( new Runnable(){
                    public void run(){
                        if (usuarios.isExiste()) {

                            System.out.println("Dentro del if");
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("login", usuarios);
                            startActivity(intent);
                            System.out.println("Ha hecho el intent");
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
}

