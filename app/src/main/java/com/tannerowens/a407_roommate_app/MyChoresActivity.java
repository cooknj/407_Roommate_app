package com.tannerowens.a407_roommate_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import static com.tannerowens.a407_roommate_app.R.id.backButton;
import static com.tannerowens.a407_roommate_app.R.id.homeButton;

/**
 * Created by Nick on 3/30/2017.
 */

public class MyChoresActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_chores);

        configureHomeButton();
        configureBackButton();
    }
    
    //home button configuration (back to main activity)
    private void configureHomeButton() {
        Button button = (Button) findViewById(homeButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    //back button config (back to chores activity)
    private void configureBackButton() {
        Button button = (Button) findViewById(backButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ChoresActivity.class);
                startActivity(intent);
            }
        });
    }
}
