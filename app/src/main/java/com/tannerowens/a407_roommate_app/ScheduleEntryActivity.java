package com.tannerowens.a407_roommate_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.tannerowens.a407_roommate_app.R.id.homeButton;

/**
 * Created by Tanner on 3/30/2017.
 */

public class ScheduleEntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_entry);

        configureHomeButton();
        configureBackButton();
        configureAddActivityButton();
    }

    private void configureHomeButton() {
        Button button = (Button) findViewById(homeButton);
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
                EditText startTime = (EditText) findViewById(R.id.startTime);
                EditText endTime = (EditText) findViewById(R.id.endTime);
                EditText day = (EditText) findViewById(R.id.day);
                Intent intent = new Intent();
                Bundle b = new Bundle();
                b.putString("start", startTime.getText().toString());
                b.putString("end", endTime.getText().toString());
                b.putString("day", day.getText().toString());
                b.putString("description", description.getText().toString());
                //intent.putExtra(startTime.getText().toString()+endTime.getText().toString()+description.getText().toString(), 1);
                intent.putExtras(b);
                setResult(1, intent);
                finish();
            }
        });
    }

}
