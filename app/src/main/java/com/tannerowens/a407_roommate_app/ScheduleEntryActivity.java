package com.tannerowens.a407_roommate_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

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
                Intent intent = new Intent(view.getContext(), ChoresActivity.class);
                startActivity(intent);
            }
        });
    }

    private void configureAddActivityButton() {
        Button button = (Button) findViewById(R.id.addActivityButton);
        //TODO add activity to schedule
    }

}
