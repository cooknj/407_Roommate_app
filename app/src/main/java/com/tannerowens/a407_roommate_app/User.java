package com.tannerowens.a407_roommate_app;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Tanner on 4/7/2017.
 */

public class User implements Serializable{

    private String name;
    private String email;
    private House house;
    private ArrayList<ScheduleEntry> scheduleEntries;

    public User(String name, String email){

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

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public ArrayList<ScheduleEntry> getScheduleEntries() {
        return scheduleEntries;
    }

    public void setScheduleEntries(ArrayList<ScheduleEntry> scheduleEntries) {
        this.scheduleEntries = scheduleEntries;
    }
}
