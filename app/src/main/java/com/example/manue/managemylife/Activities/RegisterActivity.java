package com.example.manue.managemylife.Activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import com.example.manue.managemylife.R;

import Compartir.Peticion;
import Compartir.Usuarios;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class RegisterActivity extends AppCompatActivity{

    final String server = "192.168.0.158";

    Usuarios usuarios = new Usuarios();
    Peticion peticion = new Peticion();
    EditText usuario = null;
    EditText contraseña = null;
    EditText nombre = null;
    EditText salario = null;
    Button registrar_usuario = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usuario = (EditText) findViewById(R.id.register_email);
        contraseña = (EditText) findViewById(R.id.register_pass);
        nombre = (EditText) findViewById(R.id.register_nombre);
        salario = (EditText) findViewById(R.id.register_salario);
        registrar_usuario = (Button) findViewById(R.id.register_boton);
        registrar_usuario.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                registrar();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void registrar() {

        registerTask registerTask = new registerTask();
        registerTask.execute();

    }

    public class registerTask extends AsyncTask<String, Void, Void> {

        Socket cliente = null;
        ObjectOutputStream salida = null;
        ObjectInputStream entrada = null;

        @Override
        protected Void doInBackground(String... strings) {
            try {
                System.out.println("AAA");
                cliente = new Socket(server, 4444);
                System.out.println("BBB");
                salida = new ObjectOutputStream(cliente.getOutputStream());
                entrada = new ObjectInputStream(cliente.getInputStream());

                peticion.setConsulta(1);
                salida.writeObject(peticion);

                MessageDigest digest = MessageDigest.getInstance("MD5");
                byte[] hash = digest.digest(contraseña.getText().toString().getBytes(StandardCharsets.UTF_8));
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < hash.length; i++) {
                    sb.append(Integer.toString((hash[i] & 0xff) + 0x100,16).substring(1));
                }
                usuarios.setUsuario(usuario.getText().toString());
                usuarios.setContraseña(sb.toString());

                usuarios.setNombre(nombre.getText().toString());
                usuarios.setSalario(Integer.valueOf(salario.getText().toString()));

                System.out.println("Envio el objeto usuarios con la información del usuario para registrarlo");
                salida.writeObject(usuarios);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (NoSuchAlgorithmException e){
                e.printStackTrace();
            }
            return null;
        }
    }

}

