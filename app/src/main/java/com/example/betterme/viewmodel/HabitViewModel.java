package com.example.betterme.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.betterme.database.Habit;
import com.example.betterme.repository.HabitRepository;

import java.util.List;

public class HabitViewModel extends AndroidViewModel {

    private HabitRepository repository;
    private LiveData<List<Habit>> allHabits;

    public HabitViewModel(Application application) {
        super(application);
        repository = new HabitRepository(application);
        allHabits = repository.getAllHabits();
    }

    public void insert(Habit habit) {
        repository.insert(habit);
    }

    public void update(Habit habit) {
        repository.update(habit);
    }

    public void delete(Habit habit) {
        repository.delete(habit);
    }

    public LiveData<List<Habit>> getAllHabits() {
        return allHabits;
    }
}
