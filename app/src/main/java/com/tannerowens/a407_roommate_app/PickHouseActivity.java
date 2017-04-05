package com.tannerowens.a407_roommate_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PickHouseActivity extends AppCompatActivity {

    private Button pickHouseButton;
    private EditText houseName;
    private DatabaseReference mDatabase;
    private String email;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_house);

        pickHouseButton = (Button) findViewById(R.id.pickHouseButton);
        houseName = (EditText) findViewById(R.id.houseNameText);
        mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://roommateapp-a6d3a.firebaseio.com/");
        email = getIntent().getStringExtra("email");
        name = getIntent().getStringExtra("name");

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
        String house = houseName.getText().toString();
        if (TextUtils.isEmpty(house)) {
            Toast.makeText(PickHouseActivity.this, "Fill in house name", Toast.LENGTH_LONG).show();
        }
        else if(mDatabase.child("house").child(house) != null){
            mDatabase.child("house").child(house).child("users").child(name).setValue(email);
        }
        //TODO:create new house
        else{
            mDatabase.child("house").child(house).setValue(houseName.getText().toString());
            mDatabase.child("house").child(house).child("users").child(name).setValue(email);
            //mDatabase.child("house").child(house).child("bulletin board").child("null").setValue(null);
        }
        finish();
    }
}
