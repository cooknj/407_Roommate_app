package com.tannerowens.a407_roommate_app;



import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class OurCalendarActivity extends AppCompatActivity {

    CalendarView CalendarView;
    private User user;
    private int selectedMonth;
    private int selectedDay;
    private int selectedYear;
    //private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_main);
        CalendarView = (CalendarView) findViewById(R.id.simpleCalendarView); // get the reference of CalendarView
        //mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://roommateapp-a6d3a.firebaseio.com/");
        user = (User) getIntent().getSerializableExtra("user");


        // perform setOnDateChangeListener event on CalendarView
       CalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                selectedMonth = month + 1;
                selectedDay = dayOfMonth;
                selectedYear = year;
            }
        });


        configureBackButton();
        configureAddEventButton();
        configureViewEventButton();

    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

                CalendarEvent s;
                String name = data.getStringExtra("name");
                String location = data.getStringExtra("location");
                String start = data.getStringExtra("startTime");
                String end = data.getStringExtra("endTime");
                int month = data.getIntExtra("month", 0);
                int day = data.getIntExtra("day", 0);
                int year = data.getIntExtra("year", 0);

                s = new CalendarEvent(name, location, start, end, month, day, year);
                user.addCalendarEvent(s);
                mDatabase.child("users").child(user.getUsername()).setValue(user);
    }*/

    private void configureBackButton() {
        Button button = (Button) findViewById(R.id.back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void configureAddEventButton() {
        Button button = (Button) findViewById(R.id.addEvent);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OurCalendarActivity.this, AddCalendarEventActivity.class);
                intent.putExtra("user", user);
                intent.putExtra("day", selectedDay);
                intent.putExtra("month", selectedMonth );
                intent.putExtra("year", selectedYear );
                startActivity(intent);
            }
        });
    }

    private void configureViewEventButton() {
        Button button = (Button) findViewById(R.id.viewEvent);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OurCalendarActivity.this, ViewCalendarEventActivity.class);
                intent.putExtra("user", user);
                intent.putExtra("day", selectedDay);
                intent.putExtra("month", selectedMonth );
                intent.putExtra("year", selectedYear );
                startActivity(intent);
            }
        });
    }

}

