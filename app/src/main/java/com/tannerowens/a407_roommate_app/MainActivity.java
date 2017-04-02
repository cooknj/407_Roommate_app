package com.tannerowens.a407_roommate_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //configureBillsButton();
        //configureBulletinBoardButton();
        configureCalendarButton();
        configureChoresButton();
        configureScheduleButton();
        //configureWhiteBoardButton();
    }


    /*private void configureBulletinBoardButton() {
        Button button = (Button) findViewById(R.id.bulletinBoardButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BulletinBoardActivity.class);
                startActivity(intent);
                //finish();  I think finish might end the activity which in this case we dont want to do
            }
        });
    }*/

    private void configureCalendarButton() {
        Button button = (Button) findViewById(R.id.calendarButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OurCalendarActivity.class);
                startActivity(intent);
            }
        });
    }

    private void configureChoresButton() {
        Button button = (Button) findViewById(R.id.choresButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ChoresActivity.class);
                startActivity(intent);
            }
        });
    }

    private void configureScheduleButton() {
        Button button = (Button) findViewById(R.id.scheduleButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ScheduleActivity.class);
                startActivity(intent);
            }
        });
    }

/*    private void configureWhiteBoardButton() {
        Button button = (Button) findViewById(R.id.whiteBoardButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WhiteBoardActivity.class);
                startActivity(intent);
            }
        });
    }

    private void configureBillsButton() {
        Button button = (Button) findViewById(R.id.billsButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BillsActivity.class);
                startActivity(intent);
            }
        });
    }*/
}
