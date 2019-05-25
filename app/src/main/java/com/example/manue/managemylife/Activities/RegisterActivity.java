package com.example.manue.managemylife.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.AsyncTask;

import android.os.Bundle;
import android.view.View;
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

import com.example.manue.managemylife.R;
import com.example.manue.managemylife.vo.SettingsClass;

import Compartir.Peticion;
import Compartir.Usuarios;

/**
 * Activity que permite registrarse en el sistema
 */
public class RegisterActivity extends AppCompatActivity{

    SettingsClass settings = null;
    Usuarios usuarios = new Usuarios();
    Peticion peticion = new Peticion();
    EditText usuario = null;
    EditText contraseña = null;
    EditText nombre = null;
    EditText salario = null;
    Button registrar_usuario = null;
    TextView registrar_iniciar_sesion = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        settings = new SettingsClass(this);
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
        registrar_iniciar_sesion = (TextView) findViewById(R.id.register_iniciar_sesion);
        registrar_iniciar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void registrar() {
        registerTask registerTask = new registerTask();
        registerTask.execute();
    }

    /**
     * Clase AsyncTask que permite registrar un usuario en el sistema
     */
    public class registerTask extends AsyncTask<String, Void, Void> {

        Socket cliente = null;
        ObjectOutputStream salida = null;
        ObjectInputStream entrada = null;

        @Override
        protected Void doInBackground(String... strings) {
            try {
                cliente = new Socket(settings.obtenerSettings().get(0).getAddress(), settings.obtenerSettings().get(0).getPort());
                salida = new ObjectOutputStream(cliente.getOutputStream());
                entrada = new ObjectInputStream(cliente.getInputStream());

                peticion.setConsulta(1);
                salida.writeObject(peticion);

                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(contraseña.getText().toString().getBytes(StandardCharsets.UTF_8));
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < hash.length; i++) {
                    sb.append(Integer.toString((hash[i] & 0xff) + 0x100,16).substring(1));
                }
                usuarios.setUsuario(usuario.getText().toString());
                usuarios.setContraseña(sb.toString());

                usuarios.setNombre(nombre.getText().toString());
                usuarios.setSalario(Integer.valueOf(salario.getText().toString()));

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

