package com.tannerowens.a407_roommate_app;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ValueEventListener;

//import static com.tannerowens.a407_roommate_app.R.id.addPost;

public class BulletinBoardActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    List<BulletinBoardPost> bulletinList = new ArrayList<>();
    private User user;
    private House house;
    String bulletinRoot = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bulletin_board_main);


        mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://roommateapp-a6d3a.firebaseio.com/");
        user = (User) getIntent().getSerializableExtra("user");
        house = user.getHouse();

        if(house == null) {
            Context context = getApplicationContext();
            CharSequence text = "Please log into a House first.";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            finish();
        } else {
            String houseName = house.getName();
            bulletinRoot = houseName.trim() + " " + "bulletins";
        }

        configureBackButton();
        generateBulletinList(bulletinRoot);
        configureNewPostButton();
    }

    private void generateBulletinList(String bulletinRoot) {
        //ScrollView scrollView = (ScrollView) findViewById(R.id.BulletinsScrollView);
        //final ArrayList<BulletinBoardPost> posts = new ArrayList<>();

        // Create a LinearLayout element
        //LinearLayout linearLayout = new LinearLayout(this);
        //linearLayout.setOrientation(LinearLayout.VERTICAL);
        //LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,);
        //lp.setMargins(30, 0, 30, 0);

        //String determineBulletinURL = "https://roommateapp-a6d3a.firebaseio.com/bulletins"
        //DatabaseReference mRef = FirebaseDatabase.getInstance().getReferenceFromUrl
        // ("https://roommateapp-a6d3a.firebaseio.com/bulletins");
        mDatabase.child(bulletinRoot).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                bulletinList = new ArrayList<>();
                for (DataSnapshot entries : dataSnapshot.getChildren()) {
                    BulletinBoardPost p = entries.getValue(BulletinBoardPost.class);
                    bulletinList.add(p);
                }
                displayBulletinButtons();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("DBerror", "DATABASE ERROR WHILE READING BULLETINS");
            }
        });
    }

   private void displayBulletinButtons(){
        LinearLayout linearView = (LinearLayout) findViewById(R.id.BulletinsScrollView).findViewById(R.id.innerWheel);
        for(int i = 0; i < bulletinList.size(); i++) {

            if (findViewById(i) == null) {
                // Add Buttons
                Button button = new Button(this);
                button.setPadding(20, 20, 20, 20);
                button.setText(bulletinList.get(i).getTitle());
                button.setId(i);
                final int localI = i;
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(), ViewBulletinActivity.class);
                        intent.putExtra("ParentPost", bulletinList.get(localI));
                        intent.putExtra("user", user);

                        startActivity(intent);
                    }
                });
                linearView.addView(button);

                // Add the LinearLayout element to the ScrollView
                //scrollView.addView(linearLayout,lp);
            }
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

    private void configureBackButton() {
        //This ensures that the back button is on the front layer and can always be selected
        LinearLayout layoutTop = (LinearLayout) findViewById(R.id.title);
        layoutTop.bringToFront();

        Button button = (Button) findViewById(R.id.backButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}