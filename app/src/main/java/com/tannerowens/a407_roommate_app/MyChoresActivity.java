package com.tannerowens.a407_roommate_app;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Nick on 3/30/2017.
 */

public class MyChoresActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private User user;

    HashMap<String, ArrayList<String>> choreMap = GlobalChoreList.getChoreMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_chores);

        mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://roommateapp-a6d3a.firebaseio.com/");
        user = (User) getIntent().getSerializableExtra("user");

        configureBackButton();
        //getUserFromDB();
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
        final String name = user.getName();

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

                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

                Snackbar sb = Snackbar.make(coordinatorLayout, "Chore Removed!", Snackbar.LENGTH_SHORT);
                sb.show();

                adapter.notifyDataSetChanged();
            }
        });

        }
    }

