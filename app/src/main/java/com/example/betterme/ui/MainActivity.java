package com.example.betterme.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.betterme.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        // Set up the toolbar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set up the navigation drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        // Load HomeFragment by default
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

        // Update navigation drawer header with user mood and motivation message
        SharedPreferences sharedPreferences = getSharedPreferences("BetterMePrefs", MODE_PRIVATE);
        String userMood = sharedPreferences.getString("user_mood", "How are you feeling today?");
        String motivationMessage = getMotivationMessage(userMood);

        View headerView = navigationView.getHeaderView(0);
        TextView moodTextView = headerView.findViewById(R.id.nav_header_mood);
        TextView motivationTextView = headerView.findViewById(R.id.nav_header_motivation);

        moodTextView.setText(userMood);
        motivationTextView.setText(motivationMessage);
    }

    private String getMotivationMessage(String mood) {
        switch (mood) {
            case "Very happy 😄":
                return "Keep shining!";
            case "Happy 😊":
                return "Spread the joy!";
            case "Sad 😢":
                return "It's okay to feel sad. Better days are ahead!";
            case "Angry 😠":
                return "Take a deep breath. You got this!";
            case "Tired 😴":
                return "Rest up, tomorrow is another day!";
            case "Excited 😆":
                return "Channel that excitement into something great!";
            default:
                return "Stay positive!";
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;

        if (item.getItemId() == R.id.nav_home) {
            selectedFragment = new HomeFragment();
        } else if (item.getItemId() == R.id.nav_checklist) {
            selectedFragment = new DailyChecklistFragment();
        } else if (item.getItemId() == R.id.nav_progress) {
            selectedFragment = new ProgressFragment();
        } else if (item.getItemId() == R.id.nav_habits) {
            selectedFragment = new HabitsFragment();
        }

        if (selectedFragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
//