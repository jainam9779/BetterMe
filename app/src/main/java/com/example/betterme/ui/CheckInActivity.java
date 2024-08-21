package com.example.betterme.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.betterme.R;

public class CheckInActivity extends AppCompatActivity {

    private Spinner moodSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);

        moodSpinner = findViewById(R.id.moodSpinner);
        Button trackYourselfButton = findViewById(R.id.trackYourselfButton);

        trackYourselfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedMood = moodSpinner.getSelectedItem().toString();

                SharedPreferences sharedPreferences = getSharedPreferences("BetterMePrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("user_mood", selectedMood);
                editor.apply();

                // Start MainActivity after saving the user's mood
                Intent intent = new Intent(CheckInActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
