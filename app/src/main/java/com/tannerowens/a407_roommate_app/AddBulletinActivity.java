package com.tannerowens.a407_roommate_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.tannerowens.a407_roommate_app.R.id.postButton;
import static com.tannerowens.a407_roommate_app.R.id.bulletinContent;
import static com.tannerowens.a407_roommate_app.R.id.cancelButton;
import static com.tannerowens.a407_roommate_app.R.id.bulletinTitle;

/**
 * Created by fgtho on 4/12/2017.
 */

public class AddBulletinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_bulletin);

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
    private void configurePostButton() {
        Button button = (Button) findViewById(postButton);

        //get & process the name of who is assigned the chore @TODO
        //EditText name_txt = (EditText) findViewById(bulletinTitle);
        //String name = name_txt.getText().toString();

        //get & process the  @TODO
        //EditText chore_txt = (EditText) findViewById(bulletinContent);
        //String chore = chore_txt.getText().toString();

        //add to the bulletinBoard @TODO
        //choreMap.assignChore(chore, name);
    }

}
