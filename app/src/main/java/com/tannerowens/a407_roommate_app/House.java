package com.tannerowens.a407_roommate_app;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Tanner on 4/7/2017.
 */

public class House implements Serializable{

    private String name;
    private ArrayList<User> users = new ArrayList<>();
    //private ArrayLise<Chore> houseChores;

    public House(){

    }

    public House(String name, User user){
        this.name = name;
        this.users.add(user);
    }

    public void addUser(User user){
        this.users.add(user);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
