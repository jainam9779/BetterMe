package com.example.betterme.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.betterme.R;
import com.example.betterme.database.Habit;
import com.example.betterme.viewmodel.HabitViewModel;

import java.util.ArrayList;
import java.util.List;

public class HabitsFragment extends Fragment {

    private HabitViewModel habitViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_habits, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.habitsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        HabitsAdapter adapter = new HabitsAdapter();
        recyclerView.setAdapter(adapter);

        habitViewModel = new ViewModelProvider(this).get(HabitViewModel.class);
        habitViewModel.getAllHabits().observe(getViewLifecycleOwner(), habits -> {
            adapter.submitList(habits);
        });

        view.findViewById(R.id.addHabitButton).setOnClickListener(v -> showAddHabitDialog());

        return view;
    }

    private void showAddHabitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Add New Habit");

        final EditText input = new EditText(requireContext());
        builder.setView(input);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String habitName = input.getText().toString();
                if (!habitName.isEmpty()) {
                    Habit newHabit = new Habit(habitName); // Only pass the habit name as the argument
                    habitViewModel.insert(newHabit);
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    class HabitsAdapter extends RecyclerView.Adapter<HabitsAdapter.HabitViewHolder> {

        private List<Habit> habitList = new ArrayList<>();

        @NonNull
        @Override
        public HabitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_habit, parent, false);
            return new HabitViewHolder(view);
        }

        // Replace the existing onBindViewHolder method with this one
        @Override
        public void onBindViewHolder(@NonNull HabitViewHolder holder, int position) {
            Habit habit = habitList.get(position);
            holder.habitName.setText(habit.getHabitName());

            holder.sun.setChecked(habit.isDayCompleted(0));
            holder.mon.setChecked(habit.isDayCompleted(1));
            holder.tue.setChecked(habit.isDayCompleted(2));
            holder.wed.setChecked(habit.isDayCompleted(3));
            holder.thu.setChecked(habit.isDayCompleted(4));
            holder.fri.setChecked(habit.isDayCompleted(5));
            holder.sat.setChecked(habit.isDayCompleted(6));

            holder.sun.setOnClickListener(v -> {
                habit.toggleDayCompleted(0);
                habitViewModel.update(habit);  // Save to the database
            });
            holder.mon.setOnClickListener(v -> {
                habit.toggleDayCompleted(1);
                habitViewModel.update(habit);  // Save to the database
            });
            holder.tue.setOnClickListener(v -> {
                habit.toggleDayCompleted(2);
                habitViewModel.update(habit);  // Save to the database
            });
            holder.wed.setOnClickListener(v -> {
                habit.toggleDayCompleted(3);
                habitViewModel.update(habit);  // Save to the database
            });
            holder.thu.setOnClickListener(v -> {
                habit.toggleDayCompleted(4);
                habitViewModel.update(habit);  // Save to the database
            });
            holder.fri.setOnClickListener(v -> {
                habit.toggleDayCompleted(5);
                habitViewModel.update(habit);  // Save to the database
            });
            holder.sat.setOnClickListener(v -> {
                habit.toggleDayCompleted(6);
                habitViewModel.update(habit);  // Save to the database
            });
        }

        @Override
        public int getItemCount() {
            return habitList.size();
        }

        public void submitList(List<Habit> habits) {
            this.habitList = habits;
            notifyDataSetChanged();
        }

        class HabitViewHolder extends RecyclerView.ViewHolder {

            TextView habitName;
            CheckBox sun, mon, tue, wed, thu, fri, sat;

            HabitViewHolder(@NonNull View itemView) {
                super(itemView);
                habitName = itemView.findViewById(R.id.habitName);
                sun = itemView.findViewById(R.id.sun);
                mon = itemView.findViewById(R.id.mon);
                tue = itemView.findViewById(R.id.tue);
                wed = itemView.findViewById(R.id.wed);
                thu = itemView.findViewById(R.id.thu);
                fri = itemView.findViewById(R.id.fri);
                sat = itemView.findViewById(R.id.sat);
            }
        }
    }

}
