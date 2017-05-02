package com.tannerowens.a407_roommate_app;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by fgtho on 4/8/2017.
 */

public class Whiteboard implements Serializable {

    private ArrayList<String> members;
    private String firebaseID;
    private ArrayList<Message> messages = new ArrayList<>();

    public Whiteboard(){
    }

    public Whiteboard(ArrayList<String> mems){
        this.members = mems;
    }

    public Whiteboard(ArrayList<String> mems, ArrayList<Message> mess){
        this.members = mems;
        this.messages = mess;
    }

    public Whiteboard(ArrayList<String> mems, ArrayList<Message> mess, String fbID){
        this.members = mems;
        this.messages = mess;
        this.firebaseID = fbID;
    }

    public void addMessage(Message mes){this.messages.add(mes);}

    public void addMember(String name){this.members.add(name);}

    public ArrayList<String> getMembers() {
        return members;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setID(String id){this.firebaseID = id;}

    public String getID(){return firebaseID;}
}