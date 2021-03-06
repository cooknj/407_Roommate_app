package com.tannerowens.a407_roommate_app;



import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Calendar;


public class OurCalendarActivity extends AppCompatActivity {

    CalendarView CalendarView;
    private User user;
    private int selectedMonth;
    private int selectedDay;
    private int selectedYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_main);
        user = (User) getIntent().getSerializableExtra("user");


        configureCalendarView();
        configureBackButton();
        configureAddEventButton();
        configureViewEventButton();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK) {
            user = (User) data.getSerializableExtra("user");
        }
        configureCalendarView();
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
                startActivityForResult(intent, 1);
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

    private void configureCalendarView() {

        CalendarView c = (CalendarView) findViewById(R.id.simpleCalendarView);
        long date = c.getDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        selectedYear = calendar.get(Calendar.YEAR);
        selectedMonth = calendar.get(Calendar.MONTH) + 1;
        selectedDay = calendar.get(Calendar.DAY_OF_MONTH);
        configureEventDates(selectedMonth);
        c.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                selectedMonth = month + 1;
                selectedDay = dayOfMonth;
                selectedYear = year;
                configureEventDates(selectedMonth);
            }
        });


    }

    private void configureEventDates(int month) {
        TextView view = (TextView) findViewById(R.id.EventsThisMonth);
        if(!user.getCalendarEvents().isEmpty()) {
            view.setTextSize(12);
            String text = "  This month, events on:";
            ArrayList<Integer> dates = new ArrayList<>();
            for (int i = 0; i < user.getCalendarEvents().size(); i++) {
                if (user.getCalendarEvents().get(i).getMonth() == month) {
                    if (!dates.contains(user.getCalendarEvents().get(i).getDate())) {
                        text = text + " " + user.getCalendarEvents().get(i).getDate() + ",";
                        dates.add(user.getCalendarEvents().get(i).getDate());
                    }
                }
            }
            view.setText(text);
        }
        else {
            view.setGravity(Gravity.CENTER);
        }
    }

}

