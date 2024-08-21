package com.example.betterme.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.room.migration.Migration;

@Database(entities = {Habit.class}, version = 2)  // Incremented version number
@TypeConverters({Converters.class})
public abstract class HabitDatabase extends RoomDatabase {

    private static HabitDatabase instance;

    public abstract HabitDao habitDao();

    public static synchronized HabitDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            HabitDatabase.class, "habit_database")
                    .fallbackToDestructiveMigration()  // This will reset the database and avoid the crash
                    .build();
        }
        return instance;
    }
}
