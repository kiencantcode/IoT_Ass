package com.example.iot_ass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class set_schedule extends AppCompatActivity {
    private Button move;
    private EditText editTextStartTime, editTextEndTime, editTextN, editTextP, editTextK;
    private Button submitButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_schedule);

        editTextStartTime = findViewById(R.id.editTextStartTime);
        editTextEndTime = findViewById(R.id.editTextEndTime);
        editTextN = findViewById(R.id.editTextN);
        editTextP = findViewById(R.id.editTextP);
        editTextK = findViewById(R.id.editTextK);

        submitButton = findViewById(R.id.submitButton);
        cancelButton = findViewById(R.id.cancelButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitSchedule();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelSetup();
            }
        });

        move=findViewById(R.id.button_back);
        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void submitSchedule() {
        String startTime = editTextStartTime.getText().toString();
        String endTime = editTextEndTime.getText().toString();
        String n = editTextN.getText().toString();
        String p = editTextP.getText().toString();
        String k = editTextK.getText().toString();

        // Validate input fields
        if (startTime.isEmpty() || endTime.isEmpty() || n.isEmpty() || p.isEmpty() || k.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Process the submitted schedule (e.g., save it, send it to MainActivity)
        String schedule = "Start Time: " + startTime + ", End Time: " + endTime +
                ", N: " + n + " ml, P: " + p + " ml, K: " + k + " ml";

        // Pass the schedule data back to MainActivity
        Intent resultIntent = new Intent();
        resultIntent.putExtra("schedule", schedule);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
    private void cancelSetup() {
        setResult(RESULT_CANCELED);
        finish();
    }
}