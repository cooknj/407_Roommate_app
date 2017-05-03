package com.tannerowens.a407_roommate_app;

import java.io.Serializable;

public class Message implements Serializable{

    private String poster;
    private String content;

    public Message(){
        //Constructor with no arguments for Firebase
    }

    public  Message(String poster, String content){
        this.poster = poster;
        this.content = content;
    }

    public void setPoster(String p) {this.poster = p;}

    public void setConent(String c) {this.content = c;}

    public String getPoster() {return poster;}

    public String getContent() {return content;}
}
