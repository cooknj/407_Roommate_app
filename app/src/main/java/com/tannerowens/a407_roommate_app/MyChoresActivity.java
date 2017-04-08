package com.tannerowens.a407_roommate_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Nick on 3/30/2017.
 */

public class MyChoresActivity extends AppCompatActivity {
    HashMap<String, ArrayList<String>> choreMap = GlobalChoreList.getChoreMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_chores);

        configureBackButton();
        displayMyChores();
    }

    //back button config (back to chores activity)
    private void configureBackButton() {
        Button button = (Button) findViewById(R.id.backButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //displays the chores on the page
    private void displayMyChores() {
        final ArrayList<String> list;
        final String name = null; //TODO need a way to get current 'users' name

        list = choreMap.get(name);

        //get ListView object from xml file
        final ListView list_xml = (ListView) findViewById(R.id.list);

        //define new adapter
        final ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);

        //set list view adapter
        list_xml.setAdapter(adapter);

        //on click delete chore from list ("completed")
        list_xml.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //get the chore that was clicked
                String chore = adapter.getItem(position);

                //update the local list variable for xml changes
                list.remove(chore);

                //update user's chore list
                choreMap.put(name, list);

                adapter.notifyDataSetChanged();
            }
        });

        }
    }

