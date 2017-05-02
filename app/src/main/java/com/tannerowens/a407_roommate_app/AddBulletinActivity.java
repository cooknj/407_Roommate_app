package com.tannerowens.a407_roommate_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.tannerowens.a407_roommate_app.R.id.postButton;
import static com.tannerowens.a407_roommate_app.R.id.bulletinContent;
import static com.tannerowens.a407_roommate_app.R.id.cancelButton;
import static com.tannerowens.a407_roommate_app.R.id.bulletinTitle;

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
                finish();
            }
        });
    }

    //adds a post to the global bulletin board
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

                //add post to the bulletin board
                addPost(title, username, post);

                //Return user to the bulletin page after submitting their post
                finish();
            }
        });
    }

    private void addPost(String title, String poster, String content){
        BulletinBoardPost newPost = new BulletinBoardPost(title,poster,content);
        //String generatedID = mDatabase.child("bulletins").push().getKey();
        Long generatedID = System.currentTimeMillis()/1000;
        String stringedID = generatedID.toString();
        newPost.setID(stringedID);
        String bulletinRoot = ((User)getIntent().getSerializableExtra("user")).getHouse().getName();
        bulletinRoot = bulletinRoot + " bulletins";
        mDatabase.child(bulletinRoot).child(stringedID).setValue(newPost);
    }
}
