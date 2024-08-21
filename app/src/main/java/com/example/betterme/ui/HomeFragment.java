package com.example.betterme.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.betterme.R;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Button checklistButton = view.findViewById(R.id.checklistButton);
        Button progressButton = view.findViewById(R.id.progressButton);
        Button habitsButton = view.findViewById(R.id.habitsButton);

        checklistButton.setOnClickListener(v -> navigateToFragment(new DailyChecklistFragment()));
        progressButton.setOnClickListener(v -> navigateToFragment(new ProgressFragment()));
        habitsButton.setOnClickListener(v -> navigateToFragment(new HabitsFragment()));

        return view;
    }

    private void navigateToFragment(Fragment fragment) {
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}
