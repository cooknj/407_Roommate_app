package com.tannerowens.a407_roommate_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Nick on 3/30/2017.
 */

public class ChoresActivity extends AppCompatActivity {
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chores_main);

        user = (User) getIntent().getSerializableExtra("user");

        configureBackButton();
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

    private void configureMyChoresButton() {
        Button button = (Button) findViewById(R.id.myChores);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoresActivity.this, MyChoresActivity.class);
                intent.putExtra("user", user);
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
