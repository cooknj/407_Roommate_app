package com.tannerowens.a407_roommate_app;

import java.io.Serializable;

/**
 * Created by Tanner on 4/5/2017.
 */

public class ScheduleEntry implements Serializable{

    private String start;
    private String end;
    private String day;
    private String description;
    private int button;

    public ScheduleEntry(){

    }

    public ScheduleEntry(String start, String end, String day, String description){
        this.start = start;
        this.end = end;
        this.day = day;
        this.description = description;
    }

    public int getButton() {
        return button;
    }

    public void setButton(int button) {
        this.button = button;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
