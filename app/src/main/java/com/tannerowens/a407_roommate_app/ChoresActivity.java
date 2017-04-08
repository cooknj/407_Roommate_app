package com.tannerowens.a407_roommate_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


/**
 * Created by Nick on 3/30/2017.
 */

public class ChoresActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chores_main);

        configureBackButton();
        configureHomeButton();
        configureMyChoresButton();
        configureCompletedChoresButton();
        configureAddChoresButton();
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

    private void configureHomeButton() {
        Button button = (Button) findViewById(R.id.homeButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoresActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void configureMyChoresButton() {
        Button button = (Button) findViewById(R.id.myChores);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoresActivity.this, MyChoresActivity.class);
                startActivity(intent);
            }
        });
    }

    private void configureCompletedChoresButton() {
        Button button = (Button) findViewById(R.id.uncompletedChores);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoresActivity.this, UncompletedChoresActivity.class);
                startActivity(intent);
            }
        });
    }

    private void configureAddChoresButton() {
        Button button = (Button) findViewById(R.id.addChores);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoresActivity.this, AddChoresActivity.class);
                startActivity(intent);
            }
        });
    }
}
