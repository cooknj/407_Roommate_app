package com.tannerowens.a407_roommate_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ViewScheduleEntryActivity extends AppCompatActivity {

    private ScheduleEntry entry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_schedule_entry);

        entry = (ScheduleEntry) getIntent().getSerializableExtra("entry");
    }
}
