package com.tannerowens.a407_roommate_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by fgtho on 5/2/2017.
 */

public class BillsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bills_main);

        configureBackButton();
    }

    private void configureBackButton() {
        //This ensures that the back button is on the front layer and can always be selected
        LinearLayout layoutTop = (LinearLayout) findViewById(R.id.bigBackArrow);
        layoutTop.bringToFront();

        Button button = (Button) findViewById(R.id.bigBackButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}