package com.tannerowens.a407_roommate_app;

import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ValueEventListener;

//import static com.tannerowens.a407_roommate_app.R.id.addPost;


/**
 * Created by fgtho on 4/8/2017.
 */

public class BulletinBoardActivity extends AppCompatActivity { //@TODO

    private DatabaseReference mDatabase;
    List<BulletinBoardPost> bulletinList = new ArrayList<>();
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bulletin_board_main);

        mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://roommateapp-a6d3a.firebaseio.com/");
        user = (User) getIntent().getSerializableExtra("user");

        generateBulletinList();
        configureNewPostButton();
    }


    private void generateBulletinList(){
        ScrollView scrollView = (ScrollView) findViewById(R.id.BulletinsScrollView);
        final ArrayList<BulletinBoardPost> posts = new ArrayList<>();

        // Create a LinearLayout element
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        DatabaseReference mRef = FirebaseDatabase.getInstance().getReferenceFromUrl
                ("https://roommateapp-a6d3a.firebaseio.com/bulletins");
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                BulletinBoardPost p;
                for (DataSnapshot entries: dataSnapshot.getChildren()) {
                    p = entries.getValue(BulletinBoardPost.class);
                    posts.add(p);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("DBerror", "DATABASE ERROR WHILE READING BULLETINS");
            }
        });

        for(int i = 0; i < posts.size(); i++){
            // Add Buttons
            Button button = new Button(this);
            button.setText(posts.get(i).getTitle());
            final int localI = i;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), ViewBulletinActivity.class);
                    intent.putExtra("ParentPost", posts.get(localI));//@TODO potential for this to always return the last post
                    intent.putExtra("user", user);

                    startActivity(intent);
                }
            });
            linearLayout.addView(button);

            // Add the LinearLayout element to the ScrollView
            scrollView.addView(linearLayout);
        }
    }

    private void configureNewPostButton() {
        Button button = (Button) findViewById(R.id.NewPostButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddBulletinActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
}