package com.rui.demo.data.source.local.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//singleton
@Database(entities = {Person.class}, version = 1, exportSchema = false)
public abstract class PersonDatabase extends RoomDatabase {
    private static PersonDatabase INSTANCE;

    public static synchronized PersonDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), PersonDatabase.class, "database")
                    .build();
        }
        return INSTANCE;
    }

    public abstract PersonDao getWordDao();
}
