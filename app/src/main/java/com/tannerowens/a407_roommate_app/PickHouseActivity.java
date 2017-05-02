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

import java.util.ArrayList;
<<<<<<< HEAD
import java.util.Collections;
=======
>>>>>>> refs/remotes/origin/master

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

        configureBackButton();
        pickHouseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                pickHouse();
            }
        });
    }

    private void configureBackButton() {
        Button button = (Button) findViewById(R.id.backButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void updateHouseUsers(House h){
        final House house = h;
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReferenceFromUrl
                ("https://roommateapp-a6d3a.firebaseio.com/users");
        final ArrayList<String> houseUsers = house.getUsers();

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User u;
                for (DataSnapshot users: dataSnapshot.getChildren()) {
                    if(houseUsers.contains(users.getKey().toString())){
                        u = users.getValue(User.class);
                        u.setHouse(house);
                        mDatabase.child("users").child(u.getUsername()).setValue(u);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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
            //Remove user from old house:
            final House oldHouse = user.getHouse();

            DatabaseReference mRef = FirebaseDatabase.getInstance().getReferenceFromUrl
                    ("https://roommateapp-a6d3a.firebaseio.com/house");
            mRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    boolean houseExists = false;
                    House house;
                    String houseName;
                    House old = null;

                    for (DataSnapshot houses: dataSnapshot.getChildren()) {//EXISTING HOUSE
                        houseName = houses.getKey();
                        if(houseName.equals(h)){
                            houseExists = true;
                            house = houses.getValue(House.class);
                            if(house.getUsers().contains(user.getUsername())){
                                user.setHouse(house);
                                HouseWrapper.setHouse(house);
                            }
                            else {
                                house.addUser(user.getUsername());
                                updateHouseUsers(house);
                                mDatabase.child("house").child(h).setValue(house);
                                user.setHouse(house);
                                HouseWrapper.setHouse(house);
                            }
                        }
                        if(oldHouse != null ){
                            if (houseName.equals(oldHouse.getName()) && !houseName.equals(h)) {
                                old = houses.getValue(House.class);
                            }
                        }
                    }

                    if(!houseExists){//create a new house
                        House newHouse = new House(h,user.getUsername());
                        HouseWrapper.setHouse(newHouse);
                        mDatabase.child("house").child(h).setValue(newHouse);
                        user.setHouse(newHouse);
                    }

                    if (old!=null){//deletes user from old house
                        old.removeUser(user.getUsername());
                        updateHouseUsers(old);
                        mDatabase.child("house").child(old.getName()).setValue(old);
                        HouseWrapper.setHouse(old);
                    }

                    //update old house users with house and update new house users with house
                    mDatabase.child("users").child(user.getUsername()).setValue(user);
                    generateGlobalWhiteboards();
                    finish();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    public void generateGlobalWhiteboards(){
        ArrayList<String> houseUsers = user.getHouse().getUsers();
        houseUsers.removeAll(Collections.singleton(null));

        for(int k = 0; k < houseUsers.size(); k++){
            ArrayList<String> mems = new ArrayList<String>();
            ArrayList<Message> mess = new ArrayList<Message>();

            if(!houseUsers.get(k).equals(user.getUsername())){
                mems.add(houseUsers.get(k));
                mems.add(user.getUsername());

                Whiteboard wb = new Whiteboard(mems, mess, houseUsers.get(k) + " & " + user.getUsername());
                String databaseRoot = user.getHouse().getName().trim() + " whiteboards";
                mDatabase.child(databaseRoot).child(wb.getID()).setValue(wb);
            }
        }
    }
}
