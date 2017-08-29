package com.chatapp;

/**
 * Created by Tamer on 8/24/2017.
 */

public class Message {
    public String masscontcnt="";
    public String sender="";

    public Message(String masscontcnt, String sender) {
        this.masscontcnt = masscontcnt;
        this.sender = sender;
    }

    public Message() {

    }

    public String getMasscontcnt() {
        return masscontcnt;
    }

    public void setMasscontcnt(String masscontcnt) {
        this.masscontcnt = masscontcnt;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
