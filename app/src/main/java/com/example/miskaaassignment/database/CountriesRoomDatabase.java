package com.example.miskaaassignment.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Countries.class} ,version = 1,exportSchema = false)
public abstract class CountriesRoomDatabase extends RoomDatabase {

    public abstract CountriesDao getDao();
    public static volatile CountriesRoomDatabase INSTANCE;
    public static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static CountriesRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (CountriesRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),CountriesRoomDatabase.class, "countries_database").build();
                }
            }
        }
        return INSTANCE;
    }
}
