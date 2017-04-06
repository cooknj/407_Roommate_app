package com.tannerowens.a407_roommate_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.tannerowens.a407_roommate_app.R.id.backButton;
import static com.tannerowens.a407_roommate_app.R.id.homeButton;
import static com.tannerowens.a407_roommate_app.R.id.listView;

/**
 * Created by Nick on 3/30/2017.
 */

public class MyChoresActivity extends AppCompatActivity {
    GlobalChoreList choreMap = (GlobalChoreList) getApplication();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_chores);

        configureBackButton();
        configureHomeButton();
        displayMyChores();
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

    //back to main screen
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

    //displays the chores on the page
    private void displayMyChores() {
        ArrayList<String> list;
        String name = null; //TODO need a way to get current 'users' name

        list = choreMap.getChoresFor(name);

        //get ListView object from xml file
        final ListView list_xml = (ListView) findViewById(R.id.listView);

        //define new adapter
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, R.id.text1, list);

        //set list view adapter
        list_xml.setAdapter(adapter);

        }
    }

