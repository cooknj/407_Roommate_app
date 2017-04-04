package com.tannerowens.a407_roommate_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference fire;
    private TextView email;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (TextView) findViewById(R.id.email);
        mAuth = FirebaseAuth.getInstance();

        /*/////////////////TESTING FIREBASE DATABASE////////////////////////////
        username = (TextView) findViewById(R.id.email);
        fire = FirebaseDatabase.getInstance().getReference();
        fire.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                username.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

        Intent i = new Intent(MainActivity.this, SignUpActivity.class);
        startActivityForResult(i, 1);
        //////////////////////////////////////////////////////////////

        //configureBillsButton();
        //configureBulletinBoardButton();
        //configureWhiteBoardButton();
        configureCalendarButton();
        configureChoresButton();
        configureScheduleButton();
        configureSignOutButton();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1){
            if(resultCode == 1){
                email.setText(data.getStringExtra("email"));
            }
            else{
                Intent i = new Intent(MainActivity.this, SignUpActivity.class);
                startActivityForResult(i, 1);
            }
        }
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
}
