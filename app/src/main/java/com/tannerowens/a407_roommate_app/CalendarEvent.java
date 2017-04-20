package com.tannerowens.a407_roommate_app;


import java.io.Serializable;

public class CalendarEvent implements Serializable{

    private String name;
    private String location;
    private String startTime;
    private String endTime;
    private int month;
    private int date;
    private int year;


    public CalendarEvent(String name, String location, String startTime, String endTime,
                              int month, int date, int year) {
        this.name = name;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        this.month = month;
        this.date = date;
        this.year = year;
    }

    public int getMonth() {return month;}

    public int getDate() {return date;}

    public int getYear() {return year;}

    public String getName() {return name;}

    public String getLocation() {return location;}

    public String getStart() {return startTime;}

    public String getEnd() {return endTime;}
}
