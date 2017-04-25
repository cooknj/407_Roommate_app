package com.tannerowens.a407_roommate_app;

import android.app.Application;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Tanner on 4/7/2017.
 */

public class House implements Serializable {

    private String name;
    private ArrayList<String> users = new ArrayList<>();
    //private ArrayLise<Chore> houseChores;

    public House(){

    }

    public House(String name, String user){
        this.name = name;
        this.users.add(user);
    }

    public void removeUser(String user) {this.users.remove(user);}

    public void addUser(String user){
        this.users.add(user);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }
}
