package com.tannerowens.a407_roommate_app;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Tanner on 4/7/2017.
 */

public class User implements Serializable{

    private String name;
    private String email;
    private String username;
    private String house;
    private ArrayList<ScheduleEntry> scheduleEntries = new ArrayList<>(100);
    private ArrayList<CalendarEvent> calendarEvents = new ArrayList<>();

    public User(){

    }

    public User(String name, String email){
        this.name = name;
        this.email = email;
        String[] s = email.split("@");
        this.username = s[0];
    }

    public void removeScheduleEntry(ScheduleEntry s){
        int butt = s.getButton();
        for(int i = 0; i<scheduleEntries.size();i++){
            if (scheduleEntries.get(i).getButton() == butt){
                this.scheduleEntries.remove(i);
            }
        }

    }

    public void addScheduleEntry(ScheduleEntry s){
        this.scheduleEntries.add(s);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public ArrayList<ScheduleEntry> getScheduleEntries() {
        return scheduleEntries;
    }

    public void setScheduleEntries(ArrayList<ScheduleEntry> scheduleEntries) {
        this.scheduleEntries = scheduleEntries;
    }
    
    public void setCalendarEvents(ArrayList<CalendarEvent> calendarEvents) {
        this.calendarEvents = calendarEvents;
    }

    public void addCalendarEvent(CalendarEvent s) {this.calendarEvents.add(s);}

    public ArrayList<CalendarEvent> getCalendarEvents() {return calendarEvents;}
}
