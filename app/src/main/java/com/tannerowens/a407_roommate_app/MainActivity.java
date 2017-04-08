package com.tannerowens.a407_roommate_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private TextView nameText;
    private FirebaseAuth mAuth;
    private String email;
    private String username;
    private String name;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameText = (TextView) findViewById(R.id.nameText);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://roommateapp-a6d3a.firebaseio.com/");

        Intent i = new Intent(MainActivity.this, SignUpActivity.class);
        startActivityForResult(i, 1);

        /*mDatabase.child("users").child("username").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user = (User) dataSnapshot.child("users").child(username).getValue();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

        //email and name are set in onActivityResult()
        //////////////////////////////////////////////////////////////

        //configureBillsButton();
        //configureBulletinBoardButton();
        //configureWhiteBoardButton();
        configureCalendarButton();
        configureChoresButton();
        configureScheduleButton();
        configureSignOutButton();
        configurePickHouseButton();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1){
            if(resultCode == 1){
                email = data.getStringExtra("email");
                String[] s = email.split("@");
                username = s[0];
                name = data.getStringExtra("name");
                nameText.setText(name);
                getCurrentUser();
            }
            else{
                Intent i = new Intent(MainActivity.this, SignUpActivity.class);
                startActivityForResult(i, 1);
            }
        }
    }

    private void getCurrentUser(){
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
    }
    /*private void configureBulletinBoardButton() {
        Button button = (Button) findViewById(R.id.bulletinBoardButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BulletinBoardActivity.class);
                startActivity(intent);
                //finish();  I think finish might end the activity which in this case we dont want to do
            }
        });
    }*/

    private void configureCalendarButton() {
        Button button = (Button) findViewById(R.id.calendarButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OurCalendarActivity.class);
                startActivity(intent);
            }
        });
    }

    private void configureChoresButton() {
        Button button = (Button) findViewById(R.id.choresButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ChoresActivity.class);
                startActivity(intent);
            }
        });
    }

    private void configureScheduleButton() {
        Button button = (Button) findViewById(R.id.scheduleButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ScheduleActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }

/*    private void configureWhiteBoardButton() {
        Button button = (Button) findViewById(R.id.whiteBoardButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WhiteBoardActivity.class);
                startActivity(intent);
            }
        });
    }

    private void configureBillsButton() {
        Button button = (Button) findViewById(R.id.billsButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BillsActivity.class);
                startActivity(intent);
            }
        });
    }*/

    private void configureSignOutButton(){
        Button button = (Button) findViewById(R.id.signOutButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                finish();
            }
        });
    }

    private void configurePickHouseButton(){
        Button button = (Button) findViewById(R.id.pickHouseButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, PickHouseActivity.class);
                    //user = new User("goo", "boo");//TESTING
                    intent.putExtra("user", user);
                    startActivity(intent);
                }
            });
    }

}
