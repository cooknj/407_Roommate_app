package com.tannerowens.a407_roommate_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.tannerowens.a407_roommate_app.R.id.postButton;
import static com.tannerowens.a407_roommate_app.R.id.bulletinContent;
import static com.tannerowens.a407_roommate_app.R.id.cancelButton;
import static com.tannerowens.a407_roommate_app.R.id.bulletinTitle;

/**
 * Created by fgtho on 4/12/2017.
 */

public class AddBulletinActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private User user;
    private String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_bulletin);

        mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://roommateapp-a6d3a.firebaseio.com/");
        user = (User) getIntent().getSerializableExtra("user");
        username = user.getName();

        configureCancelButton();
        configurePostButton();
    }


    //cancel button config (return to bulletin board)
    private void configureCancelButton() {
        Button button = (Button) findViewById(cancelButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), BulletinBoardActivity.class);
                startActivity(intent);
            }
        });
    }

    //adds a post to the global bulletin board
    //@TODO configure click listener
    private void configurePostButton() {
        Button button = (Button) findViewById(postButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get & process the name of the bulletin poster
                EditText title_txt = (EditText) findViewById(bulletinTitle);
                String title = title_txt.getText().toString();

                //get & process the content of the bulletin
                EditText post_txt = (EditText) findViewById(bulletinContent);
                String post = post_txt.getText().toString();

                //add post to the bulletin board @TODO
                //getCurrentUser();
                addPost(title, username, post);
            }
        });
    }

    /* private void getCurrentUser(){
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://roommateapp-a6d3a.firebaseio.com/users");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (username != null) {
                    for (DataSnapshot users: dataSnapshot.getChildren()) {
                        if(username.equals(users.getKey())){
                            User u = users.getValue(User.class);
                            user = u;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }*/

    private void addPost(String title, String poster, String content){
        BulletinBoardPost newPost = new BulletinBoardPost(title,poster,content);
        //String generatedID = mDatabase.child("bulletins").push().getKey();
        Long generatedID = System.currentTimeMillis()/1000;
        String stringedID = generatedID.toString();
        newPost.setID(stringedID);
        mDatabase.child("bulletins").child(stringedID).setValue(newPost);
    }
}
