package com.tannerowens.a407_roommate_app;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class AddCalendarEventActivity extends AppCompatActivity{

    private int day;
    private int month;
    private int year;
    private User user;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_calendar_activity);


        day = (Integer) getIntent().getSerializableExtra("day");
        month = (Integer) getIntent().getSerializableExtra("month");
        year = (Integer) getIntent().getSerializableExtra("year");
        user = (User) getIntent().getSerializableExtra("user");
        mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://roommateapp-a6d3a.firebaseio.com/");

        configureDateText();
        configureBackButton();
        configureAddButton();
    }

    private void configureDateText() {
        TextView date = (TextView) findViewById(R.id.dateText);
        date.setText(month + "/" + day + "/" + year);
    }

    private void configureBackButton() {
        Button button = (Button) findViewById(R.id.back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void configureAddButton() {
        Button button = (Button) findViewById(R.id.addEvent);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText event_txt = (EditText) findViewById(R.id.EventNameText);
                String name = event_txt.getText().toString();

                EditText location_txt = (EditText) findViewById(R.id.EventLocation);
                String location = location_txt.getText().toString();

                EditText start_txt = (EditText) findViewById(R.id.EventTimeStart);
                String startTime = start_txt.getText().toString();

                EditText end_txt = (EditText) findViewById(R.id.EventTimeEnd);
                String endTime = end_txt.getText().toString();

               /* Intent intent = new Intent();
                intent.putExtra("name", name);
                intent.putExtra("location", location);
                intent.putExtra("startTime", startTime);
                intent.putExtra("endTime", endTime);
                intent.putExtra("month", month);
                intent.putExtra("day", day);
                intent.putExtra("year", year);
                setResult(1, intent);*/
                CalendarEvent event = new CalendarEvent(name, location, startTime, endTime, month, day, year);
                user.addCalendarEvent(event);
                mDatabase.child("users").child(user.getUsername()).setValue(user);
                finish();

            }
        });
    }

}