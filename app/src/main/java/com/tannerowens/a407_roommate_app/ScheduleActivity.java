package com.tannerowens.a407_roommate_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Tanner on 3/29/2017.
 */

public class ScheduleActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_schedule);

        configureHomeButton();
        configureAddActivityButton();
        configureBackButton();
    }

    private void configureHomeButton() {
        Button button = (Button) findViewById(R.id.homeButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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
            }
        }
    }

    private void setScheduleEntry(String start, String end, String day, String description){
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
            case "11am": row1 = 1;
                break;
            case "12pm": row1 = 1;
                break;
            case "1pm": row1 = 1;
                break;
            case "2pm": row1 = 1;
                break;
            case "3pm": row1 = 1;
                break;
        }


    }
}
