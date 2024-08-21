package com.example.betterme.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "habit_table")
public class Habit {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String habitName;
    private boolean[] daysCompleted = new boolean[7];  // Array to track completion for each day of the week

    // Constructor
    public Habit(String habitName) {
        this.habitName = habitName;
        this.daysCompleted = new boolean[7];  // Initialize all days to not completed
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getHabitName() { return habitName; }
    public void setHabitName(String habitName) { this.habitName = habitName; }

    public boolean isDayCompleted(int dayIndex) {
        return daysCompleted[dayIndex];
    }

    public void toggleDayCompleted(int dayIndex) {
        daysCompleted[dayIndex] = !daysCompleted[dayIndex];
    }

    public boolean[] getDaysCompleted() {
        return daysCompleted;
    }

    public void setDaysCompleted(boolean[] daysCompleted) {
        this.daysCompleted = daysCompleted;
    }
}
