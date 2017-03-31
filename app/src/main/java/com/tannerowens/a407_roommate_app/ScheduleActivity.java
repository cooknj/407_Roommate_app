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
}
