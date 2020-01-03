package com.example.manue.managemylife.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.manue.managemylife.Entity.Settings;

import java.util.List;

@Dao
public interface SettingsDAO {

    @Query("SELECT * FROM settings")
    List<Settings> getAll();
    @Insert
    long insertSettings(Settings settings);
    @Update
    int updateSettings(Settings settings);
}
