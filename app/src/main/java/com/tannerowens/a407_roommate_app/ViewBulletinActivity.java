package com.tannerowens.a407_roommate_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static com.tannerowens.a407_roommate_app.R.id.bulletinContent;
import static com.tannerowens.a407_roommate_app.R.id.bulletinTitle;
import static com.tannerowens.a407_roommate_app.R.id.postButton;

/**
 * Created by fgtho on 4/21/2017.
 */

public class ViewBulletinActivity  extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private BulletinBoardPost thisPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bulletin_reply);

        mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://roommateapp-a6d3a.firebaseio.com/");
        thisPost = (BulletinBoardPost) getIntent().getSerializableExtra("ParentPost");

        generateParentPost();
        generateReplyList();
        configureReplyButton();
    }

    private void generateParentPost(){
        ScrollView scrollView = (ScrollView) findViewById(R.id.BulletinReplies);

        // Create a LinearLayout element
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        int t = thisPost.getTitle().length();
        int o = thisPost.getOwner().length();
        int c = thisPost.getContent().length();

        Spannable span = new SpannableString(thisPost.getTitle() + "\n" +  thisPost.getOwner() + "\n" + thisPost.getContent());
        //Big font till you find `\n`
        span.setSpan(new RelativeSizeSpan(1.5f), 0, t, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //Smallest font between '\n's
        span.setSpan(new RelativeSizeSpan(0.8f), t, (t+o+1), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //Medium font to end
        span.setSpan(new RelativeSizeSpan(1.0f), (t+o+1), (c+(t+o+1)+1), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        Button notUsedAsAButton = new Button(this);
        notUsedAsAButton.setText(span);

        linearLayout.addView(notUsedAsAButton);

        // Add the LinearLayout element to the ScrollView
        scrollView.addView(linearLayout);
    }

    private void generateReplyList(){
        ScrollView scrollView = (ScrollView) findViewById(R.id.BulletinReplies);
        final ArrayList<Message> replies = thisPost.getReplies();

        // Create a LinearLayout element
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        if(!replies.isEmpty()) {
            for (int i = 0; i < replies.size(); i++) {
                // Add Replies as unused buttons
                Button notUsedAsAButton = new Button(this);

                int p = replies.get(i).getPoster().length();
                int c = replies.get(i).getContent().length();

                Spannable span = new SpannableString(thisPost.getTitle() + "\n" +  thisPost.getOwner() + "\n" + thisPost.getContent());
                //Smaller font for poster
                span.setSpan(new RelativeSizeSpan(0.8f), 0, p, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                //Medium font for content
                span.setSpan(new RelativeSizeSpan(1.0f), p, (p+c+1), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                notUsedAsAButton.setText(span);

                linearLayout.addView(notUsedAsAButton);

                // Add the LinearLayout element to the ScrollView
                scrollView.addView(linearLayout);
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

                //add reply to the bulletin board post@TODO
                //getCurrentUser();
                User user = (User) getIntent().getSerializableExtra("user");
                String username = user.getName();
                addReply(username, reply);
            }
        });
    }
    //@TODO finalize function
    private void addReply (String poster, String content){
        Message newReply = new Message(poster,content);
        BulletinBoardPost postToUpdate = (BulletinBoardPost) getIntent().getSerializableExtra("ParentPost");
        postToUpdate.addReply(newReply);
        mDatabase.child("bulletins").child(postToUpdate.getID()).setValue(postToUpdate);
    }
}
