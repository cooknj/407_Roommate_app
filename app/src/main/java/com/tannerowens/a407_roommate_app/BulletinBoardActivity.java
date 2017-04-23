package com.tannerowens.a407_roommate_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//import static com.tannerowens.a407_roommate_app.R.id.addPost;


/**
 * Created by fgtho on 4/8/2017.
 */

public class BulletinBoardActivity extends AppCompatActivity { //@TODO

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bulletin_board_main);

        mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://roommateapp-a6d3a.firebaseio.com/");
    }

    private void configureNewPostButton() {
        Button button = (Button) findViewById(R.id.NewPostButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddBulletinActivity.class);
                startActivity(intent);
            }
        });
    }
}