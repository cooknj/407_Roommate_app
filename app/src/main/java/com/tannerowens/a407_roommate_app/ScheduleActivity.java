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

    private Button getButton(ScheduleEntry e) {
        Button b = null;
        int row1 = 1;
        String day = e.getDay();
        Log.v("SSSSSSSSSSSSSSSSS", e.getStart());
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
        switch (row1) {
            case 1:
                switch (day) {
                    case "SUN": b = (Button) findViewById(R.id.SUN6am);
                        break;
                    case "MON": b = (Button) findViewById(R.id.MON6am);
                        break;
                    case "TUES": b = (Button) findViewById(R.id.TUES6am);
                        break;
                    case "WED": b = (Button) findViewById(R.id.WED6am);
                        break;
                    case "THURS": b = (Button) findViewById(R.id.THURS6am);
                        break;
                    case "FRI": b = (Button) findViewById(R.id.FRI6am);
                        break;
                    case "SAT": b = (Button) findViewById(R.id.SAT6am);
                        break;
                    default: b = (Button) findViewById(R.id.SUN6am);
                        break;
                }
            case 2:
                switch (day) {
                    case "SUN": b = (Button) findViewById(R.id.SUN7am);
                        break;
                    case "MON": b = (Button) findViewById(R.id.MON7am);
                        break;
                    case "TUES": b = (Button) findViewById(R.id.TUES7am);
                        break;
                    case "WED": b = (Button) findViewById(R.id.WED7am);
                        break;
                    case "THURS": b = (Button) findViewById(R.id.THURS7am);
                        break;
                    case "FRI": b = (Button) findViewById(R.id.FRI7am);
                        break;
                    case "SAT": b = (Button) findViewById(R.id.SAT7am);
                        break;
                    default: b = (Button) findViewById(R.id.SUN7am);
                        break;
                }
            case 3:
                switch (day) {
                    case "SUN": b = (Button) findViewById(R.id.SUN8am);
                        break;
                    case "MON": b = (Button) findViewById(R.id.MON8am);
                        break;
                    case "TUES": b = (Button) findViewById(R.id.TUES8am);
                        break;
                    case "WED": b = (Button) findViewById(R.id.WED8am);
                        break;
                    case "THURS": b = (Button) findViewById(R.id.THURS8am);
                        break;
                    case "FRI": b = (Button) findViewById(R.id.FRI8am);
                        break;
                    case "SAT": b = (Button) findViewById(R.id.SAT8am);
                        break;
                    default: b = (Button) findViewById(R.id.SUN8am);
                        break;
                }
            case 4:
                switch (day) {
                    case "SUN": b = (Button) findViewById(R.id.SUN9am);
                        break;
                    case "MON": b = (Button) findViewById(R.id.MON9am);
                        break;
                    case "TUES": b = (Button) findViewById(R.id.TUES9am);
                        break;
                    case "WED": b = (Button) findViewById(R.id.WED9am);
                        break;
                    case "THURS": b = (Button) findViewById(R.id.THURS9am);
                        break;
                    case "FRI": b = (Button) findViewById(R.id.FRI9am);
                        break;
                    case "SAT": b = (Button) findViewById(R.id.SAT9am);
                        break;
                    default: b = (Button) findViewById(R.id.SUN9am);
                        break;
                }
            case 5:
                switch (day) {
                    case "SUN": b = (Button) findViewById(R.id.SUN10am);
                        break;
                    case "MON": b = (Button) findViewById(R.id.MON10am);
                        break;
                    case "TUES": b = (Button) findViewById(R.id.TUES10am);
                        break;
                    case "WED": b = (Button) findViewById(R.id.WED10am);
                        break;
                    case "THURS": b = (Button) findViewById(R.id.THURS10am);
                        break;
                    case "FRI": b = (Button) findViewById(R.id.FRI10am);
                        break;
                    case "SAT": b = (Button) findViewById(R.id.SAT10am);
                        break;
                    default: b = (Button) findViewById(R.id.SUN10am);
                        break;
                }
            case 6:
                switch (day) {
                    case "SUN": b = (Button) findViewById(R.id.SUN11am);
                        break;
                    case "MON": b = (Button) findViewById(R.id.MON11am);
                        break;
                    case "TUES": b = (Button) findViewById(R.id.TUES11am);
                        break;
                    case "WED": b = (Button) findViewById(R.id.WED11am);
                        break;
                    case "THURS": b = (Button) findViewById(R.id.THURS11am);
                        break;
                    case "FRI": b = (Button) findViewById(R.id.FRI11am);
                        break;
                    case "SAT": b = (Button) findViewById(R.id.SAT11am);
                        break;
                    default: b = (Button) findViewById(R.id.SUN11am);
                        break;
                }
            case 7:
                switch (day) {
                    case "SUN": b = (Button) findViewById(R.id.SUN12pm);
                        break;
                    case "MON": b = (Button) findViewById(R.id.MON12pm);
                        break;
                    case "TUES": b = (Button) findViewById(R.id.TUES12pm);
                        break;
                    case "WED": b = (Button) findViewById(R.id.WED12pm);
                        break;
                    case "THURS": b = (Button) findViewById(R.id.THURS12pm);
                        break;
                    case "FRI": b = (Button) findViewById(R.id.FRI12pm);
                        break;
                    case "SAT": b = (Button) findViewById(R.id.SAT12pm);
                        break;
                    default: b = (Button) findViewById(R.id.SUN12pm);
                        break;
                }
            case 8:
                switch (day) {
                    case "SUN": b = (Button) findViewById(R.id.SUN1pm);
                        break;
                    case "MON": b = (Button) findViewById(R.id.MON1pm);
                        break;
                    case "TUES": b = (Button) findViewById(R.id.TUES1pm);
                        break;
                    case "WED": b = (Button) findViewById(R.id.WED1pm);
                        break;
                    case "THURS": b = (Button) findViewById(R.id.THURS1pm);
                        break;
                    case "FRI": b = (Button) findViewById(R.id.FRI1pm);
                        break;
                    case "SAT": b = (Button) findViewById(R.id.SAT1pm);
                        break;
                    default: b = (Button) findViewById(R.id.SUN1pm);
                        break;
                }
            case 9:
                switch (day) {
                    case "SUN": b = (Button) findViewById(R.id.SUN2pm);
                        break;
                    case "MON": b = (Button) findViewById(R.id.MON2pm);
                        break;
                    case "TUES": b = (Button) findViewById(R.id.TUES2pm);
                        break;
                    case "WED": b = (Button) findViewById(R.id.WED2pm);
                        break;
                    case "THURS": b = (Button) findViewById(R.id.THURS2pm);
                        break;
                    case "FRI": b = (Button) findViewById(R.id.FRI2pm);
                        break;
                    case "SAT": b = (Button) findViewById(R.id.SAT2pm);
                        break;
                    default: b = (Button) findViewById(R.id.SUN2pm);
                        break;
                }
            case 10:
                switch (day) {
                    case "SUN": b = (Button) findViewById(R.id.SUN3pm);
                        break;
                    case "MON": b = (Button) findViewById(R.id.MON3pm);
                        break;
                    case "TUES": b = (Button) findViewById(R.id.TUES3pm);
                        break;
                    case "WED": b = (Button) findViewById(R.id.WED3pm);
                        break;
                    case "THURS": b = (Button) findViewById(R.id.THURS3pm);
                        break;
                    case "FRI": b = (Button) findViewById(R.id.FRI3pm);
                        break;
                    case "SAT": b = (Button) findViewById(R.id.SAT3pm);
                        break;
                    default: b = (Button) findViewById(R.id.SUN3pm);
                        break;
                }
            case 11:
                switch (day) {
                    case "SUN": b = (Button) findViewById(R.id.SUN4pm);
                        break;
                    case "MON": b = (Button) findViewById(R.id.MON4pm);
                        break;
                    case "TUES": b = (Button) findViewById(R.id.TUES4pm);
                        break;
                    case "WED": b = (Button) findViewById(R.id.WED4pm);
                        break;
                    case "THURS": b = (Button) findViewById(R.id.THURS4pm);
                        break;
                    case "FRI": b = (Button) findViewById(R.id.FRI4pm);
                        break;
                    case "SAT": b = (Button) findViewById(R.id.SAT4pm);
                        break;
                    default: b = (Button) findViewById(R.id.SUN4pm);
                        break;
                }
            case 12:
                switch (day) {
                    case "SUN": b = (Button) findViewById(R.id.SUN5pm);
                        break;
                    case "MON": b = (Button) findViewById(R.id.MON5pm);
                        break;
                    case "TUES": b = (Button) findViewById(R.id.TUES5pm);
                        break;
                    case "WED": b = (Button) findViewById(R.id.WED5pm);
                        break;
                    case "THURS": b = (Button) findViewById(R.id.THURS5pm);
                        break;
                    case "FRI": b = (Button) findViewById(R.id.FRI5pm);
                        break;
                    case "SAT": b = (Button) findViewById(R.id.SAT5pm);
                        break;
                    default: b = (Button) findViewById(R.id.SUN5pm);
                        break;
                }
            case 13:
                switch (day) {
                    case "SUN": b = (Button) findViewById(R.id.SUN6pm);
                        break;
                    case "MON": b = (Button) findViewById(R.id.MON6pm);
                        break;
                    case "TUES": b = (Button) findViewById(R.id.TUES6pm);
                        break;
                    case "WED": b = (Button) findViewById(R.id.WED6pm);
                        break;
                    case "THURS": b = (Button) findViewById(R.id.THURS6pm);
                        break;
                    case "FRI": b = (Button) findViewById(R.id.FRI6pm);
                        break;
                    case "SAT": b = (Button) findViewById(R.id.SAT6pm);
                        break;
                    default: b = (Button) findViewById(R.id.SUN6pm);
                        break;
                }
        }
        return b;
    }
}
