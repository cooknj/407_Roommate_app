package com.tannerowens.a407_roommate_app;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;

import static com.tannerowens.a407_roommate_app.R.id.addChoreButton;
import static com.tannerowens.a407_roommate_app.R.id.assignedToText;
import static com.tannerowens.a407_roommate_app.R.id.backButton;
import static com.tannerowens.a407_roommate_app.R.id.choreNameText;

/**
 * Created by Nick on 3/30/2017.
 */


public class AddChoresActivity extends AppCompatActivity{
    //get the global chore map variable
    GlobalChoreList choreMap = (GlobalChoreList)getApplication();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_chores);

        configureBackButton();
        configureAddChoresButton();
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

    //adds a chore to the chore list
    private void configureAddChoresButton() {
        Button button = (Button) findViewById(addChoreButton);

        //get & process the name of who is assigned the chore
        EditText name_txt = (EditText) findViewById(choreNameText);
        String name = name_txt.getText().toString();

        //get & process the chore
        EditText chore_txt = (EditText) findViewById(assignedToText);
        String chore = chore_txt.getText().toString();

        //add to the choreMap
        choreMap.assignChore(chore, name);
    }
}
