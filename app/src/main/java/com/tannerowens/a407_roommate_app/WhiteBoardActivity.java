package com.tannerowens.a407_roommate_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Map;

//import static com.tannerowens.a407_roommate_app.R.id.addPost;

public class WhiteBoardActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    List<Whiteboard> boardList = new ArrayList<>();
    private User user;
    private House house;
    String boardRoot = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.whiteboard_main);


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
            boardRoot = houseName.trim() + " " + "whiteboards";
        }

        configureBackButton();
        generateBoardList(boardRoot);
    }

    private void generateBoardList(String boardRoot) {

        mDatabase.child(boardRoot).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boardList = new ArrayList<>();
                for (DataSnapshot entries : dataSnapshot.getChildren()) {
                   Whiteboard b = entries.getValue(Whiteboard.class);
                    if(b != null){boardList.add(b);}
                }
                displayBoards();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("DBerror", "DATABASE ERROR WHILE READING BOARDS");
            }
        });
    }

    private void displayBoards(){
        LinearLayout linearView = (LinearLayout) findViewById(R.id.BoardsScrollView).findViewById(R.id.innerWheel);
        for(int i = 0; i < boardList.size(); i++) {
            if (boardList.get(i).getMembers().contains(user.getUsername())) {
                // Add Buttons
                if (findViewById(i) == null) {
                    Button button = new Button(this);
                    button.setPadding(20, 20, 20, 20);
                    button.setId(i);

                    ArrayList<String> mems = boardList.get(i).getMembers();
                    String otherUN = "";
                    String threadName = "";
                    for (int j = 0; j < mems.size(); j++) {
                        if (!(mems.get(j).equals(user.getUsername()))) {
                            otherUN = mems.get(j);
                            Log.i("otherUn", "Is " + otherUN);
                        }
                    }
                    threadName = "My Whiteboard with " + otherUN;

                    button.setText(threadName);
                    final int localI = i;
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(view.getContext(), ViewBoardActivity.class);
                            intent.putExtra("ParentBoard", boardList.get(localI));
                            intent.putExtra("user", user);

                            startActivity(intent);
                        }
                    });
                    linearView.addView(button);
                }
            }
        }
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
