package com.example.baweistore;

/**
 * date:2018/12/7
 * author:别的小朋友(别的小朋友)
 * function:
 */
public class EventBusMessage {
    public String name;
    public String pass;
    public String userId;
    public String sessionId;

    public EventBusMessage(String name, String pass,String userId,String sessionId) {
        this.name = name;
        this.pass = pass;
        this.userId=userId;
        this.sessionId=sessionId;


    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
