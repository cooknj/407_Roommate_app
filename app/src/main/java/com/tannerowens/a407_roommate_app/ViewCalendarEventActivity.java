package com.tannerowens.a407_roommate_app;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class ViewCalendarEventActivity extends AppCompatActivity{
    private User user;
    private int month;
    private int day;
    private int year;
    private LinearLayout myLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_calendar_activity);
        myLinearLayout = (LinearLayout) findViewById(R.id.CalendarActivityLayout);
        TextView date = (TextView) findViewById(R.id.viewDate);

        user = (User) getIntent().getSerializableExtra("user");
        day = (Integer) getIntent().getSerializableExtra("day");
        month = (Integer) getIntent().getSerializableExtra("month");
        year = (Integer) getIntent().getSerializableExtra("year");
        date.setText(month + "/" + day + "/" + year);

        configureBackButton();
        configureText();
    }



    private void configureText() {
        ArrayList<CalendarEvent> events = new ArrayList<>();
        if (!user.getCalendarEvents().isEmpty()) {
            for (int i = 0; i < user.getCalendarEvents().size(); i++) {
                if (user.getCalendarEvents().get(i).getDate() == day) {
                    if (user.getCalendarEvents().get(i).getMonth() == month) {
                        if (user.getCalendarEvents().get(i).getYear() == year) {
                            events.add(user.getCalendarEvents().get(i));
                        }
                    }
                }
            }
        }

        if (events.isEmpty()) {
            TextView view = new TextView(this);
            view.setText("No Events On This Date");
            view.setTextSize(25);
            view.setHorizontallyScrolling(true);
            view.setGravity(Gravity.CENTER);
            view.setTextColor(Color.RED);
            myLinearLayout.addView(view);
        }
        else {
            int nextCol = 1;
            TextView view;
            for(int i = 0; i < events.size(); i++) {
                view = new TextView(this);
                view.setText(events.get(i).getName()+"\n" + events.get(i).getLocation() +"\n");
                view.append(events.get(i).getStart() + "\n" + events.get(i).getEnd() + "\n");
                view.setTextSize(25);
                view.setHorizontallyScrolling(true);
                view.setGravity(Gravity.CENTER);
                if(nextCol == 1) {
                    view.setTextColor(Color.BLUE);
                    nextCol++;
                }
                else if(nextCol == 2) {
                    view.setTextColor(Color.RED);
                    nextCol++;
                }
                else {
                    view.setTextColor(Color.MAGENTA);
                    nextCol = 1;
                }
                myLinearLayout.addView(view);
            }
        }
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

}
