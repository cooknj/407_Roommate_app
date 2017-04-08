package com.tannerowens.a407_roommate_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Nick on 3/30/2017.
 */

public class UncompletedChoresActivity extends AppCompatActivity{
    HashMap<String, ArrayList<String>> choreMap = GlobalChoreList.getChoreMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uncompleted_chores);

        configureBackButton();
        displayUncompletedChores();
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

    //display uncompleted chores to user
    //uncompleted chores are all those in the GlobalChoreList
    private void displayUncompletedChores() {
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

        //populate list
        for(String name : choreMap.keySet()) {
            for(String chore : choreMap.get(name)) {
                HashMap<String, String> item = new HashMap<String, String>();
                item.put("chore", chore);
                item.put("name", name);
                list.add(item);
            }
        }

        //set up simple adapter params
        String[] from = new String[] {"chore", "name"};
        int[] to = new int[] {android.R.id.text1, android.R.id.text2};
        int nativeLayout = android.R.layout.two_line_list_item;

        //set up list view adapter
        ListView lv = (ListView) findViewById(R.id.list);
        SimpleAdapter s_adapt = new SimpleAdapter(this, list, nativeLayout, from, to);
        lv.setAdapter(s_adapt);
    }
}
