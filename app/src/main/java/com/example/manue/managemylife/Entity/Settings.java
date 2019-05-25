package com.example.manue.managemylife.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Clase Objeto encargada de obtener la dirección IP y puerto del servidor al que se va a conectar
 */
@Entity(tableName = "settings")
public class Settings {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @NonNull
    private String address;
    @NonNull
    private int port;

    /**
     * Constructor de la clase Settings
     * @param address Dirección IP del servidor al que se va a conectar
     * @param port Puerto del servidor al que se va a conectar
     */
    public Settings(@NonNull String address, int port) {
        this.address = address;
        this.port = port;
    }

    /**
     * Constructor por defecto de la clase Settings
     */
    public Settings() {
    }

    /**
     * Getter del identificador de la clase Settings
     * @return
     */
    @NonNull
    public long getId() {
        return id;
    }

    /**
     * Setter del identificador de la clase Settings
     * @param id
     */
    public void setId(@NonNull long id) {
        this.id = id;
    }

    @NonNull
    public String getAddress() {
        return address;
    }

    public void setAddress(@NonNull String address) {
        this.address = address;
    }

    @NonNull
    public int getPort() {
        return port;
    }

    public void setPort(@NonNull int port) {
        this.port = port;
    }
}
