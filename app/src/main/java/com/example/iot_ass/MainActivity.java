package com.example.iot_ass;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_SETUP = 1;
    private TextView scheduleTextView;
    private Button setupButton;
    private List<String> schedules;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find id of schedule and setup button
        scheduleTextView = findViewById(R.id.scheduleTextView);
        setupButton=findViewById(R.id.setupButton);

        // Retrieve and display schedules
        schedules = retrieveSchedules();
        displaySchedules(schedules);

        setupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, set_schedule.class);
                startActivityForResult(intent, REQUEST_CODE_SETUP);
            }

        });
    }

    private List<String> retrieveSchedules() {
        SharedPreferences sharedPreferences = getSharedPreferences("MySchedules", Context.MODE_PRIVATE);
        List<String> schedules = new ArrayList<>();
        int i = 1; // Start with index 1 for schedule1
        String schedule;
        while ((schedule = sharedPreferences.getString("schedule" + i, null)) != null) {
            schedules.add(schedule);
            i++;
        }
        return schedules;
    }

    private void displaySchedules(List<String> schedules) {
        // Implement this method to display schedules in the scheduleTextView
        LinearLayout scheduleLayout = findViewById(R.id.scheduleLayout);
        scheduleLayout.removeAllViews(); // Clear existing views
        for(final String schedule : schedules){
            TextView scheduleTextView = new TextView(this);
            scheduleTextView.setText(schedule);
            scheduleLayout.addView(scheduleTextView);

            Button deleteButton = new Button(this);
            deleteButton.setText("Delete");
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Remove the schedule from the list and update UI
                    schedules.remove(schedule);
                    displaySchedules(schedules);
                }
            });
            scheduleLayout.addView(deleteButton);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SETUP && resultCode == RESULT_OK && data != null) {
            String schedule = data.getStringExtra("schedule");
            if (schedule != null && !schedule.isEmpty()) {
                schedules.add(schedule);
                displaySchedules(schedules);
            }
        }
    }
}