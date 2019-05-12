package com.example.manue.managemylife.Activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.manue.managemylife.R;
import com.example.manue.managemylife.vo.SettingsClass;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Compartir.Peticion;
import Compartir.Productos;
import Compartir.Usuarios;

public class ModificarPerfilActivity extends AppCompatActivity {

    Usuarios usuarios = new Usuarios();
    SettingsClass settings = null;
    Peticion peticion = new Peticion();

    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;

    public static final int GALLERY = 200;
    public static final int PICK_IMAGE = 1;
    EditText modificar_usuario;
    EditText modificar_nombre;
    EditText modificar_salario;
    ImageView modificar_perfil_aceptar;
    ImageView modificar_perfil_cancelar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_perfil);

        settings = new SettingsClass(this);

        modificar_nombre = this.findViewById(R.id.modificar_perfil_nombre);
        modificar_usuario = this.findViewById(R.id.modificar_perfil_usuario);
        modificar_salario = this.findViewById(R.id.modificar_perfil_salario);

        modificar_perfil_cancelar = this.findViewById(R.id.modificar_perfil_cancelar);
        modificar_perfil_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModificarPerfilActivity.this.finish();
            }
        });
        modificar_perfil_aceptar = this.findViewById(R.id.modificar_perfil_aceptar);

        modificar_perfil_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuarios.setNombre(modificar_nombre.getText().toString());
                usuarios.setUsuario(modificar_usuario.getText().toString());
                usuarios.setSalario(Integer.valueOf(modificar_salario.getText().toString()));

                executeUpdateUsuario();
            }
        });

        this.imageView = (ImageView)this.findViewById(R.id.imageView1);
        LinearLayout capturar_imagen = (LinearLayout) this.findViewById(R.id.modificar_imagen_camara);
        LinearLayout galeria = (LinearLayout) this.findViewById(R.id.modificar_imagen_galeria);
        capturar_imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                }
                else
                {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
        });

        galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);

            }
        });
    }

    public void executeUpdateUsuario() {
        actualizarUsuario actualizarUsuario = new actualizarUsuario();
        actualizarUsuario.execute();
    }

    public class actualizarUsuario extends AsyncTask<String, Void, Void> {

        Socket cliente = null;
        ObjectOutputStream salida = null;
        ObjectInputStream entrada = null;

        @Override
        protected Void doInBackground(String... strings) {
            try {

                cliente = new Socket(settings.obtenerSettings().get(0).getAddress(), settings.obtenerSettings().get(0).getPort());
                salida = new ObjectOutputStream(cliente.getOutputStream());
                entrada = new ObjectInputStream(cliente.getInputStream());

                peticion.setConsulta(11);
                salida.writeObject(peticion);

                salida.writeObject(usuarios);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return null;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }else if(requestCode == GALLERY) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            } else {
                Toast.makeText(this, "gallery permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }
    protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                Bitmap ScaledBmp = Bitmap.createScaledBitmap(photo, 400,450, true);
                imageView.setImageBitmap(ScaledBmp);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                String encoded_image = Base64.encodeToString(byteArray, Base64.NO_WRAP);

                usuarios.setImagen(encoded_image);



            }else if(requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
                InputStream inputStream = getApplicationContext().getContentResolver().openInputStream(data.getData());
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                Bitmap bmp = BitmapFactory.decodeStream(bufferedInputStream);

                Bitmap ScaledBmp = Bitmap.createScaledBitmap(bmp, 400,450, true);
                imageView.setImageBitmap(ScaledBmp);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                ScaledBmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                String encoded_image = Base64.encodeToString(byteArray, Base64.NO_WRAP);

                usuarios.setImagen(encoded_image);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
