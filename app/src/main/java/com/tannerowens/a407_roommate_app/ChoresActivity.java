package com.tannerowens.a407_roommate_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import static com.tannerowens.a407_roommate_app.R.id.addChores;
import static com.tannerowens.a407_roommate_app.R.id.completedChores;
import static com.tannerowens.a407_roommate_app.R.id.myChores;

/**
 * Created by Nick on 3/30/2017.
 */

public class ChoresActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chores_main);

        configureMyChoresButton();
        configureCompletedChoresButton();
        configureAddChoresButton();
    }

    private void configureMyChoresButton() {
        Button button = (Button) findViewById(myChores);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MyChoresActivity.class);
                startActivity(intent);
            }
        });
    }

    private void configureCompletedChoresButton() {
        Button button = (Button) findViewById(completedChores);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CompletedChoresActivity.class);
                startActivity(intent);
            }
        });
    }

    private void configureAddChoresButton() {
        Button button = (Button) findViewById(addChores);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddChoresActivity.class);
                startActivity(intent);
            }
        });
    }
}
