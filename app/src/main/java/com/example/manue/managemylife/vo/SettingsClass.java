package com.example.manue.managemylife.vo;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.manue.managemylife.DB.DataBaseRoom;
import com.example.manue.managemylife.Entity.Settings;

import java.util.List;

public class SettingsClass {

    private static DataBaseRoom db;

    public SettingsClass(@NonNull Context context) {
        db = DataBaseRoom.getINSTANCE(context);
    }

    public List<Settings> obtenerSettings() {
        return db.settingsDAO().getAll();
    }
}
