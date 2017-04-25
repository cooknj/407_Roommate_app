package com.tannerowens.a407_roommate_app;

import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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
    ArrayList<User> user_list;
    House house = HouseWrapper.getHouse();
    User user;
    DatabaseReference mdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_chores);

        mdb = FirebaseDatabase.getInstance().getReferenceFromUrl("https://roommateapp-a6d3a.firebaseio.com/");
        user = (User) getIntent().getSerializableExtra("user");

        if(house == null) {
            Context context = getApplicationContext();
            CharSequence text = "Please log into a House first.";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            finish();
        } else {
            user_list = house.getUsers();
        }

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
        String[] items = new String[4];
        final Spinner dropdown = (Spinner) findViewById(R.id.assignedToSpinner);

        if (user_list != null) {
            for (int i = 0; i < user_list.size(); i++) {
                items[i] = user_list.get(i).getUsername().toLowerCase();
            }
         } else {
            items[0] = user.getUsername().toLowerCase();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> list;
                //get & process the name of who is assigned the chore

                String username = dropdown.toString();

                //get & process the chore
                EditText chore_txt = (EditText) findViewById(R.id.choreNameText);
                String chore = chore_txt.getText().toString();

                //add to the choreMap
                list = choreMap.get(username);

                if(list==null)
                    list = new ArrayList<String>();

                list.add(chore);
                choreMap.put(username, list);

                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

                Snackbar sb = Snackbar.make(coordinatorLayout, "Chore Added!", Snackbar.LENGTH_SHORT);
                sb.show();

                //clear text fields for multiple-entry
                //((Spinner)findViewById(R.id.assignedToText)).setHint(((EditText) findViewById(R.id.assignedToText)).getHint());
                ((EditText)findViewById(R.id.choreNameText)).setHint(((EditText) findViewById(R.id.choreNameText)).getHint());

                storeChoresInFirebase();
            }
        });
    }

    public void storeChoresInFirebase() {
        mdb.child("chores").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mdb.child("chores").setValue(choreMap);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("DBerror", "DATABASE ERROR WHILE STORING CHORES");
            }
        });

    }
}
