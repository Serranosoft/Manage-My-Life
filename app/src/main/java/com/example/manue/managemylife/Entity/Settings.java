package com.example.manue.managemylife.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "settings")
public class Settings {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @NonNull
    private String address;
    @NonNull
    private int port;

    public Settings(@NonNull String address, int port) {
        this.address = address;
        this.port = port;
    }
    public Settings() {
    }

    @NonNull
    public long getId() {
        return id;
    }

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
