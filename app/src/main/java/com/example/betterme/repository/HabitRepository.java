package com.example.betterme.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.betterme.database.Habit;
import com.example.betterme.database.HabitDao;
import com.example.betterme.database.HabitDatabase;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class HabitRepository {

    private HabitDao habitDao;
    private Executor executor;

    public HabitRepository(Application application) {
        HabitDatabase database = HabitDatabase.getInstance(application);
        habitDao = database.habitDao();
        executor = Executors.newSingleThreadExecutor();
    }

    public void insert(Habit habit) {
        executor.execute(() -> habitDao.insert(habit));
    }

    public void update(Habit habit) {
        executor.execute(() -> habitDao.update(habit));
    }

    public void delete(Habit habit) {
        executor.execute(() -> habitDao.delete(habit));
    }

    public LiveData<List<Habit>> getAllHabits() {
        return habitDao.getAllHabits();
    }
}
