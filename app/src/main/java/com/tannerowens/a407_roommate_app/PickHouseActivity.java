package com.tannerowens.a407_roommate_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PickHouseActivity extends AppCompatActivity {

    private Button pickHouseButton;
    private EditText houseName;
    private DatabaseReference mDatabase;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_house);

        pickHouseButton = (Button) findViewById(R.id.pickHouseButton);
        houseName = (EditText) findViewById(R.id.houseNameText);
        mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://roommateapp-a6d3a.firebaseio.com/");
        user = (User) getIntent().getSerializableExtra("user");

        pickHouseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                pickHouse();
            }
        });
    }

    private void pickHouse(){
        //join existing house
        final String h = houseName.getText().toString();
        if (TextUtils.isEmpty(h)) {
            Toast.makeText(PickHouseActivity.this, "Fill in house name", Toast.LENGTH_LONG).show();
        }
        else{
            mDatabase.child("house").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    boolean houseExists = false;
                    /*House house;
                    for (DataSnapshot houses: dataSnapshot.getChildren()) {//EXISTING HOUSE
                        house = (House) houses.getValue();
                        if (house.getName().equals(h)){
                            houseExists = true;
                            house.addUser(user);
                            mDatabase.child("house").child(h).setValue(house);
                            user.setHouse(house);
                            mDatabase.child("users").child(user.getEmail()).setValue(user);
                        }
                    }*/
                    if(!houseExists){//create a new house
                        //mDatabase.child("house").child(h).setValue(houseName.getText().toString());
                        House newHouse = new House(h, user);
                        mDatabase.child("house").child(h).setValue(newHouse);
                        user.setHouse(newHouse);
                        mDatabase.child("users").child(user.getUsername()).setValue(user);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        /*if(mDatabase.child("house").child(house) != null){
            mDatabase.child("house").child(house).child("users").child(email).setValue(user);
        }
        else{
            mDatabase.child("house").child(house).setValue(houseName.getText().toString());
            mDatabase.child("house").child(house).child("users").child(email).setValue(user);
            //mDatabase.child("house").child(house).child("bulletin board").child("null").setValue(null);
        }*/
        finish();
    }
}
