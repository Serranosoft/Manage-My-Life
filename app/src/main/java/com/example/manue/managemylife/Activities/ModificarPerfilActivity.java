package com.example.manue.managemylife.Activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.manue.managemylife.R;

import java.text.ParseException;

public class ModificarPerfilActivity extends AppCompatActivity {

    /*private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_perfil);
        this.imageView = (ImageView)this.findViewById(R.id.imageView1);
        ImageView capturar_imagen = (ImageView) this.findViewById(R.id.modificar_imagen);
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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if(requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
                SharedPreferences prefs =
                        this.getSharedPreferences("userData", Context.MODE_PRIVATE);

                Bitmap photo = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(photo);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
