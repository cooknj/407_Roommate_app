package com.tannerowens.a407_roommate_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewScheduleEntryActivity extends AppCompatActivity {

    private ScheduleEntry entry;
    private TextView description;
    private TextView start;
    private TextView end;
    private TextView day;
    private Button removeActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_schedule_entry);

        configureBackButton();

        entry = (ScheduleEntry) getIntent().getSerializableExtra("entry");
        if ((findViewById(R.id.entryDescription) != null)){
            description = (TextView) findViewById(R.id.entryDescription);
        }
        start = (TextView) findViewById(R.id.startTime);
        end = (TextView) findViewById(R.id.endTime);
        day = (TextView) findViewById(R.id.day);
        removeActivity = (Button) findViewById(R.id.removeActivityButton);

        description.setText(entry.getDescription());
        start.setText(entry.getStart());
        end.setText(entry.getEnd());
        day.setText(entry.getDay());

        removeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.putExtra("entry", entry);
                setResult(1, i);
                finish();
            }
        });
    }

    private void configureBackButton() {
        Button button = (Button) findViewById(R.id.backButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
