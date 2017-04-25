package com.tannerowens.a407_roommate_app;


import java.io.Serializable;

public class CalendarEvent implements Serializable{

    private String name;
    private String location;
    private String start;
    private String end;
    private int month;
    private int date;
    private int year;

    public CalendarEvent(){
        this.name = "";
        this.location = "";
        this.start = "";
        this.end = "";
        this.month = 0;
        this.date = 0;
        this.year = 0;
    }

    public CalendarEvent(String name, String location, String start, String end,
                              int month, int date, int year) {
        this.name = name;
        this.location = location;
        this.start = start;
        this.end = end;
        this.month = month;
        this.date = date;
        this.year = year;
    }

    public int getMonth() {return month;}

    public int getDate() {return date;}

    public int getYear() {return year;}

    public String getName() {return name;}

    public String getLocation() {return location;}

    public String getStart() {return start;}

    public String getEnd() {return end;}
}
