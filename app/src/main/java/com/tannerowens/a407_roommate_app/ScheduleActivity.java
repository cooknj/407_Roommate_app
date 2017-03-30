package com.tannerowens.a407_roommate_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import static com.tannerowens.a407_roommate_app.R.id.homeButton;

/**
 * Created by Tanner on 3/29/2017.
 */

public class ScheduleActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_schedule);

        configureHomeButton();
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
}
