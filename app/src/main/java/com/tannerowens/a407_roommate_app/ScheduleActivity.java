package com.tannerowens.a407_roommate_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Tanner on 3/29/2017.
 */

public class ScheduleActivity extends AppCompatActivity{

    private DatabaseReference mDatabase;
    private String name;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_schedule);

        mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://roommateapp-a6d3a.firebaseio.com/");
        name = (String) getIntent().getSerializableExtra("name");
        email = (String) getIntent().getSerializableExtra("email");

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

        if (requestCode == 1) {
            if(resultCode == 1) {
                String start = data.getStringExtra("start");
                String end = data.getStringExtra("end");
                String day = data.getStringExtra("day");
                String description = data.getStringExtra("description");
                ScheduleEntry newEntry = new ScheduleEntry(start, end, day, description);
                //TODO mDatabase.child("account").child()
                //TODO set button to view this entry
                setScheduleEntry(start, end, day, description);
            }
        }
    }

    private void setScheduleEntry(String start, String end, String day, String description){
        Button button = new Button(this);
        button.setText(description);

        int row1;
        int row2;
        int column;
        switch(start){
            case "6am": row1 = 1;
                break;
            case "7am": row1 = 2;
                break;
            case "8am": row1 = 3;
                break;
            case "9am": row1 = 4;
                break;
            case "10am": row1 = 5;
                break;
            case "11am": row1 = 6;
                break;
            case "12pm": row1 = 7;
                break;
            case "1pm": row1 = 8;
                break;
            case "2pm": row1 = 9;
                break;
            case "3pm": row1 = 10;
                break;
            case "4pm": row1 = 11;
                break;
            case "5pm": row1 = 12;
                break;
            case "6pm": row1 = 13;
                break;
            default: row1 = 0;
                break;
        }
        switch(end){
            case "6am": row2 = 1;
                break;
            case "7am": row2 = 2;
                break;
            case "8am": row2 = 3;
                break;
            case "9am": row2 = 4;
                break;
            case "10am": row2 = 5;
                break;
            case "11am": row2 = 6;
                break;
            case "12pm": row2 = 7;
                break;
            case "1pm": row2 = 8;
                break;
            case "2pm": row2 = 9;
                break;
            case "3pm": row2 = 10;
                break;
            case "4pm": row2 = 11;
                break;
            case "5pm": row2 = 12;
                break;
            case "6pm": row2 = 13;
                break;
            default: row2 = 0;
                break;
        }
        switch(day){
            case "SUN": column = 1;
                break;
            case "MON": column = 2;
                break;
            case "TUES": column = 3;
                break;
            case "WED": column = 4;
                break;
            case "THURS": column = 5;
                break;
            case "FRI": column = 6;
                break;
            default: column = 0;
                break;
        }

    }
}
