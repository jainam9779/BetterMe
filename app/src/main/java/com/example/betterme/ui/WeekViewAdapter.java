package com.example.betterme.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.betterme.R;

import java.util.List;

public class WeekViewAdapter extends RecyclerView.Adapter<WeekViewAdapter.WeekViewHolder> {

    private List<String> dayNames;
    private List<String> dayDates;

    public WeekViewAdapter(List<String> dayNames, List<String> dayDates) {
        this.dayNames = dayNames;
        this.dayDates = dayDates;
    }

    @NonNull
    @Override
    public WeekViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_week_day, parent, false);
        return new WeekViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeekViewHolder holder, int position) {
        holder.dayName.setText(dayNames.get(position));
        holder.dayDate.setText(dayDates.get(position));
    }

    @Override
    public int getItemCount() {
        return dayNames.size();
    }

    static class WeekViewHolder extends RecyclerView.ViewHolder {
        TextView dayName, dayDate;

        WeekViewHolder(View itemView) {
            super(itemView);
            dayName = itemView.findViewById(R.id.day_name);
            dayDate = itemView.findViewById(R.id.day_date);
        }
    }
}
