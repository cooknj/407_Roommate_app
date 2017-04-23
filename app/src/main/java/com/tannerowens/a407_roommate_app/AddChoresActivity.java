package com.tannerowens.a407_roommate_app;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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


public class AddChoresActivity extends AppCompatActivity{
    //get the global chore map variable
    HashMap<String, ArrayList<String>> choreMap = GlobalChoreList.getChoreMap();

    DatabaseReference mdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_chores);

        mdb = FirebaseDatabase.getInstance().getReferenceFromUrl("https://roommateapp-a6d3a.firebaseio.com/");

        configureBackButton();
        configureAddChoresButton();
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

    //adds a chore to the chore list
    private void configureAddChoresButton() {
        Button button = (Button) findViewById(R.id.addChoreButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> list;
                //get & process the name of who is assigned the chore
                EditText name_txt = (EditText) findViewById(R.id.assignedToText);
                String name = name_txt.getText().toString().toLowerCase();

                //get & process the chore
                EditText chore_txt = (EditText) findViewById(R.id.choreNameText);
                String chore = chore_txt.getText().toString();

                //add to the choreMap
                list = choreMap.get(name);

                if(list==null)
                    list = new ArrayList<String>();

                list.add(chore);
                choreMap.put(name, list);

                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

                Snackbar sb = Snackbar.make(coordinatorLayout, "Chore Added!", Snackbar.LENGTH_SHORT);
                sb.show();

                //clear text fields for multiple-entry
                ((EditText)findViewById(R.id.assignedToText)).setText(" ");
                ((EditText)findViewById(R.id.choreNameText)).setText(" ");

                storeChoresInFirebase();
            }
        });
    }

    //TODO stores the chores in firebase for persistence
    public void storeChoresInFirebase() {
        mdb.child("chores").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mdb.child("chores").setValue(choreMap);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
