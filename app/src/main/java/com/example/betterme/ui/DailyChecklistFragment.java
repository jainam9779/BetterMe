package com.example.betterme.ui;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.betterme.R;

import java.util.ArrayList;

public class DailyChecklistFragment extends Fragment {

    private ArrayList<String> taskList;
    private DailyChecklistAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily_checklist, container, false);

        ListView listView = view.findViewById(R.id.checklistListView);
        Button addButton = view.findViewById(R.id.addChecklistItemButton);

        // Initialize task list and adapter
        taskList = new ArrayList<>();
        adapter = new DailyChecklistAdapter(requireContext(), taskList);
        listView.setAdapter(adapter);

        // Set up the add button to create new tasks
        addButton.setOnClickListener(v -> showAddTaskDialog());

        return view;
    }

    private void showAddTaskDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Add New Task");

        final EditText input = new EditText(requireContext());
        builder.setView(input);

        builder.setPositiveButton("Add", (dialog, which) -> {
            String task = input.getText().toString();
            if (!task.isEmpty()) {
                taskList.add(task);
                adapter.getCheckedStates().add(false);  // Add a new unchecked state
                adapter.notifyDataSetChanged();  // Notify the adapter of data changes
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

}
