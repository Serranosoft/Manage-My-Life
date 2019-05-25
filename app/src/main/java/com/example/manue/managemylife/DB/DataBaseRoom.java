package com.example.manue.managemylife.DB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.manue.managemylife.Dao.SettingsDAO;
import com.example.manue.managemylife.Entity.Settings;

@Database(entities = {Settings.class}, version=1, exportSchema = false)
public abstract class DataBaseRoom extends RoomDatabase {

    public abstract SettingsDAO settingsDAO();
    private static DataBaseRoom INSTANCE = null;

    /**
     * Método para obtener una instancia abierta de la base de datos Room
     * @param context Fragment o activity al que hace referencia
     * @return Devuelve la instancia de la base de datos de room activa
     */
    public static DataBaseRoom getINSTANCE(final Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DataBaseRoom.class, "managemylife.db").fallbackToDestructiveMigration().build();
        }
        return INSTANCE;
    }

    /**
     * Termina la instancia de room inicializandolo como nulo
     */
    public static void destroyInstance(){
        INSTANCE = null;
    }
}
