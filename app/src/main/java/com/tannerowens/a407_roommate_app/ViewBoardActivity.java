package com.tannerowens.a407_roommate_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.GridLayout.LayoutParams;
import android.widget.ScrollView;
import android.graphics.Color;
import android.view.Gravity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.tannerowens.a407_roommate_app.R.id.bulletinContent;
import static com.tannerowens.a407_roommate_app.R.id.bulletinTitle;
import static com.tannerowens.a407_roommate_app.R.id.postButton;

public class ViewBoardActivity  extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private Whiteboard thisBoard;
    private User user;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reply_board);

        mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://roommateapp-a6d3a.firebaseio.com/");
        user = (User) getIntent().getSerializableExtra("user");
        thisBoard = (Whiteboard) getIntent().getSerializableExtra("ParentBoard");
        id = thisBoard.getID();

        configureBackButton();
        generateReplyList();
        configureReplyButton();
    }


    private void generateReplyList(){
        String boardRoot = thisBoard.getID();

        mDatabase.child(boardRoot).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                thisBoard = dataSnapshot.getValue(Whiteboard.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("DBerror", "DATABASE ERROR WHILE READING REPLIES");
            }
        });

        final ArrayList<Message> replies = thisBoard.getMessages();


        LinearLayout linearView = (LinearLayout) findViewById(R.id.BoardReplies).findViewById(R.id.repliesHolder);

        if(!replies.isEmpty()) {
            for (int i = 0; i < replies.size(); i++) {
                // Add Replies as unused buttons
                //Button notUsedAsAButton = new Button(this);
                TextView text = new TextView(this);

                int p = replies.get(i).getPoster().length();
                int c = replies.get(i).getContent().length();

                Spannable span = new SpannableString(replies.get(i).getPoster() + "\n" + replies.get(i).getContent());
                //Smaller font for poster
                span.setSpan(new RelativeSizeSpan(1.0f), 0, p, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                //Medium font for content
                span.setSpan(new RelativeSizeSpan(1.2f), p, (p+c+1), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                text.setText(span);

                //Set upper and lower margins for replies
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                params.setMargins(20, 25, 25, 20);
                text.setLayoutParams(params);
                text.setPadding(5,5,5,5);

                if(replies.get(i).getPoster().equals(user.getName())){
                    //Set reply background to white
                    text.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    text.setTextColor(Color.parseColor("#000000"));
                    text.setGravity(Gravity.LEFT);
                }
                else{
                    //Set reply backbround to black
                    text.setBackgroundColor(Color.parseColor("#000000"));
                    text.setTextColor(Color.parseColor("#FFFFFF"));
                    text.setGravity(Gravity.RIGHT);
                }

                //Add reply to view
                linearView.addView(text);
            }
        }
    }

    private void configureReplyButton() {
        Button button = (Button) findViewById(R.id.ReplyButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get & process the content of the bulletin
                EditText reply_txt = (EditText) findViewById(R.id.ReplyText);
                String reply = reply_txt.getText().toString();

                //add reply to the bulletin board post
                //getCurrentUser();
                String username = user.getName();
                addReply(username, reply);
            }
        });
    }
    //@TODO finalize function
    private void addReply (String poster, String content){
        //Create the reply as a message object and add it to the list of replies
        Message newReply = new Message(poster,content);
        Whiteboard boardToUpdate = (Whiteboard) getIntent().getSerializableExtra("ParentBoard");
        boardToUpdate.addMessage(newReply);

        //generate the house id where this bulletin is located that this reply should be added to
        user = (User) getIntent().getSerializableExtra("user");
        String houseBoardsID = user.getHouse().getName().trim() + " whiteboards";

        //update the bulletin in the database
        mDatabase.child(houseBoardsID).child(boardToUpdate.getID()).setValue(boardToUpdate);
        showAddedReply(poster, content);
    }

    private void showAddedReply(String poster, String content){
        //Clear text field to avoid duplicates and permit additional entries
        ((EditText) findViewById(R.id.ReplyText)).setText("");
        ((EditText) findViewById(R.id.ReplyText)).setHint(((EditText) findViewById(R.id.ReplyText)).getHint());

        LinearLayout linearView = (LinearLayout) findViewById(R.id.BoardReplies).findViewById(R.id.repliesHolder);
        TextView text = new TextView(this);

        int p = poster.length();
        int c = content.length();

        Spannable span = new SpannableString(poster + "\n" + content);
        //Smaller font for poster
        span.setSpan(new RelativeSizeSpan(1.0f), 0, p, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //Medium font for content
        span.setSpan(new RelativeSizeSpan(1.2f), p, (p+c+1), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        text.setText(span);

        //Set reply background to white
        int white = Color.parseColor("#FFFFFF");
        text.setBackgroundColor(white);
        text.setGravity(Gravity.LEFT);

        //Set upper and lower margins for replies
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.setMargins(20, 25, 25, 20);
        text.setLayoutParams(params);
        text.setPadding(5,5,5,5);
        text.setTextColor(Color.parseColor("#000000"));

        //Add reply to view
        linearView.addView(text);

    }

    private void configureBackButton() {
        //This ensures that the back button is on the front layer and can always be selected
        LinearLayout layoutTop = (LinearLayout) findViewById(R.id.title);
        layoutTop.bringToFront();

        Button button = (Button) findViewById(R.id.backButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });

    }
}
