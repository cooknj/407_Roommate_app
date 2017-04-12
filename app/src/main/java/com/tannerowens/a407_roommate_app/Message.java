package com.tannerowens.a407_roommate_app;

/**
 * Created by fgtho on 4/8/2017.
 */

public class Message {

    private String poster;
    private String content;

    public Messsage(String poster, String content){
        this.poster = poster;
        this.content = content;
    }

    public String getPoster() {return poster;}

    public String getContent() {return content;}
}
