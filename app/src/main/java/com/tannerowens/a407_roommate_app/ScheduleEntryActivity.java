package com.tannerowens.a407_roommate_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by Tanner on 3/30/2017.
 */

public class ScheduleEntryActivity extends AppCompatActivity {

    private Spinner startTime;
    private Spinner endTime;
    private Spinner day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_entry);

        Spinner startTime = (Spinner) findViewById(R.id.startTime);
        Spinner endTime = (Spinner) findViewById(R.id.endTime);
        Spinner day = (Spinner) findViewById(R.id.day);

        configureBackButton();
        configureSpinners();
        configureAddActivityButton();
    }

    private void configureSpinners(){
        String[] days = new String[]{"SUN", "MON", "TUES", "WED", "THURS", "FRI", "SAT"};
        String[] times = new String[]{"6am", "7am", "8am", "9am", "10am", "11am", "12pm", "1pm", "2pm", "3pm", "4pm", "5pm", "6pm"};
        ArrayAdapter<String> a = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, times);
        ArrayAdapter<String> b = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, days);
        startTime.setAdapter(a);
        endTime.setAdapter(a);
        day.setAdapter(b);
    }

    private void configureBackButton() {
        Button button = (Button) findViewById(R.id.backButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScheduleEntryActivity.this, ScheduleActivity.class);
                startActivity(intent);
            }
        });
    }

    //adds activity to appropriate location in schedule
    private void configureAddActivityButton() {
        Button button = (Button) findViewById(R.id.addActivityButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText description = (EditText) findViewById(R.id.entryDescription);
                Intent intent = new Intent();
                intent.putExtra("start", startTime.getSelectedItem().toString());
                intent.putExtra("end", endTime.getSelectedItem().toString());
                intent.putExtra("day", day.getSelectedItem().toString());
                intent.putExtra("description", description.getText().toString());
                //intent.putExtra(startTime.getText().toString()+endTime.getText().toString()+description.getText().toString(), 1);
                setResult(1, intent);
                finish();
            }
        });
    }

}
