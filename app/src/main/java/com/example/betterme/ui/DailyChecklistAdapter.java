package com.example.betterme.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.betterme.R;

import java.util.ArrayList;

public class DailyChecklistAdapter extends ArrayAdapter<String> {

    private ArrayList<Boolean> checkedStates;

    public DailyChecklistAdapter(@NonNull Context context, ArrayList<String> tasks) {
        super(context, 0, tasks);
        checkedStates = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            checkedStates.add(false);  // Initially, all tasks are unchecked
        }
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_checklist, parent, false);
        }

        String task = getItem(position);
        TextView taskText = convertView.findViewById(R.id.taskText);
        CheckBox checkBox = convertView.findViewById(R.id.checkBox);

        taskText.setText(task);
        checkBox.setChecked(checkedStates.get(position));

        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            checkedStates.set(position, isChecked);
        });

        return convertView;
    }

    public ArrayList<Boolean> getCheckedStates() {
        return checkedStates;
    }
}
