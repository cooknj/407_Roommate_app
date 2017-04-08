package com.tannerowens.a407_roommate_app;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Tanner on 3/29/2017.
 */

public class ScheduleActivity extends AppCompatActivity{

    private DatabaseReference mDatabase;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_schedule);

        mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://roommateapp-a6d3a.firebaseio.com/");
        user = (User) getIntent().getSerializableExtra("user");

        populateSchedule();
        configureAddActivityButton();
        configureBackButton();
    }

    private void configureBackButton() {
        Button button = (Button) findViewById(R.id.backButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void configureAddActivityButton(){
        Button button = (Button) findViewById(R.id.addActivityButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScheduleActivity.this, ScheduleEntryActivity.class);
                //intent.putExtra("table1", 1);
                startActivityForResult(intent, 1);//should receive text we wish to enter into table
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        ScheduleEntry e;
        if (requestCode == 1) {
            if(resultCode == 1) {
                String start = data.getStringExtra("start");
                String end = data.getStringExtra("end");
                String day = data.getStringExtra("day");
                String description = data.getStringExtra("description");
                e = new ScheduleEntry(start, end, day, description);
                addScheduleEntry(e);
                user.addScheduleEntry(e);
                mDatabase.child("users").child(user.getUsername()).setValue(user);
            }
        }
        else if (requestCode == 2){
            if(resultCode == 1){
                e = (ScheduleEntry) data.getSerializableExtra("entry");
                Button b = getButton(e);
                b.setBackgroundColor(Color.LTGRAY);
                user.removeScheduleEntry(e);
                mDatabase.child("users").child(user.getUsername()).setValue(user);            }
        }
    }

    //TODO
    private void populateSchedule(){
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReferenceFromUrl
                ("https://roommateapp-a6d3a.firebaseio.com/users/"+user.getUsername()+"/scheduleEntries");
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ScheduleEntry s;
                for (DataSnapshot entries: dataSnapshot.getChildren()) {
                    s = entries.getValue(ScheduleEntry.class);
                    addScheduleEntry(s);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("DBerror", "DATABASE ERROR WHILE POP SCHEDULE");
            }
        });
    }

    private void addScheduleEntry(ScheduleEntry e){
        Button b = getButton(e);
        e.setButton(1);//schedule activities are stored in the arrayList by these index values;
        final ScheduleEntry s = e;
        b.setBackgroundColor(Color.RED);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ScheduleActivity.this, ViewScheduleEntryActivity.class);
                i.putExtra("entry", s);
                startActivityForResult(i, 2);
            }
        });
    }

    //TODO add rest of buttons
    private Button getButton(ScheduleEntry e) {
        Button b = null;
        int row1;
        String day = e.getDay();
        switch (e.getStart()) {
            case "6am":
                row1 = 1;
                break;
            case "7am":
                row1 = 2;
                break;
            case "8am":
                row1 = 3;
                break;
            case "9am":
                row1 = 4;
                break;
            case "10am":
                row1 = 5;
                break;
            case "11am":
                row1 = 6;
                break;
            case "12pm":
                row1 = 7;
                break;
            case "1pm":
                row1 = 8;
                break;
            case "2pm":
                row1 = 9;
                break;
            case "3pm":
                row1 = 10;
                break;
            case "4pm":
                row1 = 11;
                break;
            case "5pm":
                row1 = 12;
                break;
            case "6pm":
                row1 = 13;
                break;
            default:
                row1 = 0;
                break;
        }
        switch ((row1)) {
            case 1:
                switch (day) {
                    case "SUN": b = (Button) findViewById(R.id.SUN6am);
                        break;
                    case "MON":// b = (Button) findViewById(R.id.SUN7am);
                        break;
                    case "TUES":
                        break;
                    case "WED":
                        break;
                    case "THURS":
                        break;
                    case "FRI":
                        break;
                    case "SAT":
                        break;
                    default: b = (Button) findViewById(R.id.SUN6am);
                        break;
                }
        }
        return b;
    }
}
