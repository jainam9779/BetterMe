package com.example.betterme.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.betterme.R;
import com.example.betterme.database.Habit;
import com.example.betterme.viewmodel.HabitViewModel;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class ProgressFragment extends Fragment {

    private HabitViewModel habitViewModel;
    private PieChart pieChart;
    private BarChart barChart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_progress, container, false);

        pieChart = view.findViewById(R.id.pieChart);
        barChart = view.findViewById(R.id.barChart);

        setupPieChart();
        setupBarChart();

        habitViewModel = new ViewModelProvider(this).get(HabitViewModel.class);
        habitViewModel.getAllHabits().observe(getViewLifecycleOwner(), habits -> {
            if (habits != null && !habits.isEmpty()) {
                updateChart(habits);
                updateBarChart(habits);
            } else {
                pieChart.setNoDataText("No chart data available.");
                barChart.setNoDataText("No bar data available.");
            }
        });

        return view;
    }

    private void setupPieChart() {
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);
    }

    private void setupBarChart() {
        barChart.getDescription().setEnabled(false);
        barChart.setDrawGridBackground(false);
        barChart.setFitBars(true);
        barChart.animateY(1500);

        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f);  // this replaces setStartAtZero(true)

        YAxis rightAxis = barChart.getAxisRight();
        rightAxis.setEnabled(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);

        barChart.setPinchZoom(false);
        barChart.setScaleEnabled(false);
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);

        leftAxis.setEnabled(true);
        rightAxis.setEnabled(false);
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setLabelCount(7);

        barChart.getXAxis().setDrawGridLines(false);
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setGranularity(1f);
        barChart.getXAxis().setLabelRotationAngle(0);

        // Set the orientation to horizontal
        barChart.getXAxis().setDrawLabels(true);
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.TOP_INSIDE);
        barChart.setDrawValueAboveBar(false);
        barChart.setFitBars(true);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.setScaleEnabled(false);
    }

    private void updateChart(List<Habit> habits) {
        List<PieEntry> entries = new ArrayList<>();

        int totalDays = 7 * habits.size();
        int completedDays = 0;

        for (Habit habit : habits) {
            int habitCompletion = 0;
            for (boolean dayCompleted : habit.getDaysCompleted()) {
                if (dayCompleted) {
                    habitCompletion++;
                }
            }
            completedDays += habitCompletion;
            entries.add(new PieEntry((float) habitCompletion / 7, habit.getHabitName()));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Habits Completion");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        PieData data = new PieData(dataSet);
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.YELLOW);

        pieChart.setData(data);
        pieChart.invalidate();  // refresh

        int totalProgress = (int) ((float) completedDays / totalDays * 100);
        pieChart.setCenterText("Total Progress: " + totalProgress + "%");
    }

    // Inside your ProgressFragment.java file
    private void updateBarChart(List<Habit> habits) {
        List<BarEntry> entries = new ArrayList<>();
        List<String> labels = new ArrayList<>();

        for (int i = 0; i < habits.size(); i++) {
            Habit habit = habits.get(i);
            int habitCompletion = 0;
            for (boolean dayCompleted : habit.getDaysCompleted()) {
                if (dayCompleted) {
                    habitCompletion++;
                }
            }
            entries.add(new BarEntry(i, habitCompletion));
            labels.add(habit.getHabitName() + " (" + habitCompletion + "/7)");
        }

        BarDataSet dataSet = new BarDataSet(entries, "Habit Progress");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        BarData data = new BarData(dataSet);
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.BLUE);

        barChart.setData(data);
        barChart.getXAxis().setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                int index = (int) value;
                if (index >= 0 && index < labels.size()) {
                    return labels.get(index);
                } else {
                    return "";  // Return an empty string or some default value if the index is out of bounds
                }
            }
        });

        barChart.invalidate();  // refresh
    }

}
