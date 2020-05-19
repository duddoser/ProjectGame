package com.example.projectgame.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//MessageResponse class is used to deserialize json response
public class MessageResponse {

    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}