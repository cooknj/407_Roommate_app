package com.tannerowens.a407_roommate_app;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by fgtho on 4/8/2017.
 */

public class BulletinBoardPost implements Serializable {

    private String title;
    private String owner;
    private String content;
    private String firebaseID;
    private ArrayList<Message> replies = new ArrayList<>();

    public BulletinBoardPost(String title, String owner, String content){
        this.title = title;
        this.owner = owner;
        this.content = content;
    }

    public void addReply(Message reply){this.replies.add(reply);}

    public void addReply(String title, String content){
        Message newReply = new Message(title, content);
        this.replies.add(newReply);
    }

    public String getTitle() {
        return title;
    }

    public String getOwner() {
        return owner;
    }

    public String getContent() {
        return content;
    }

    public ArrayList<Message> getReplies() {
        return replies;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setID(String id){this.firebaseID = id;}

    public String getID(){return firebaseID;}
}