package com.tannerowens.a407_roommate_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import static com.tannerowens.a407_roommate_app.R.id.homeButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configureHomeButton();
        configureBillsButton();
        configureBulletinBoardButton();
        configureCalendarButton();
        configureChoresButton();
        configureScheduleButton();
        configureWhiteBoardButton();
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

    private void configureBulletinBoardButton() {
        Button button = (Button) findViewById(R.id.bulletinBoardButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(this, BulletinBoardActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void configureCalendarButton() {
        Button button = (Button) findViewById(R.id.calendarButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(this, OurCalendarActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void configureChoresButton() {
        Button button = (Button) findViewById(R.id.choresButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(this, ChoresActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void configureScheduleButton() {
        Button button = (Button) findViewById(R.id.scheduleButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(this, ScheduleActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void configureWhiteBoardButton() {
        Button button = (Button) findViewById(R.id.whiteBoardButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(this, WhiteBoardActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void configureBillsButton() {
        Button button = (Button) findViewById(R.id.billsButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(this, BillsActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
