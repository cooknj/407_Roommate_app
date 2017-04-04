package com.tannerowens.a407_roommate_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PickHouseActivity extends AppCompatActivity {

    private Button pickHouseButton;
    private EditText houseName;
    private DatabaseReference mDatabase;
    private String email;//TODO: pass email and username in from main
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_house);

        pickHouseButton = (Button) findViewById(R.id.pickHouseButton);
        houseName = (EditText) findViewById(R.id.houseNameText);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //used to get list of data from db
        /*mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

        pickHouseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                pickHouse();
            }
        });
    }

    private void pickHouse(){
        //join existing house
        if(mDatabase.child("house").child(houseName.getText().toString()) != null){
            mDatabase.child("house").child(houseName.getText().toString()).child("users").child(email).setValue(username);
        }
        //TODO:create new house
        else{
            mDatabase.child("house").child(houseName.getText().toString()).child("users").child(email).setValue(username);
            mDatabase.child("house").child(houseName.getText().toString()).child("bulletin board").child("null").setValue(null);
        }
        finish();
    }
}
