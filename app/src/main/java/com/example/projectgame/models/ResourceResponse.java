package com.example.projectgame.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//ResourceResponse class is used to deserialize json response
public class ResourceResponse {

    @SerializedName("wood")
    @Expose
    private Integer wood;
    @SerializedName("iron")
    @Expose
    private Integer iron;
    @SerializedName("metal")
    @Expose
    private Integer metal;
    @SerializedName("stone")
    @Expose
    private Integer stone;

    public Integer getWood() {
        return wood;
    }

    public void setWood(Integer wood) {
        this.wood = wood;
    }

    public Integer getIron() {
        return iron;
    }

    public void setIron(Integer iron) {
        this.iron = iron;
    }

    public Integer getMetal() {
        return metal;
    }

    public void setMetal(Integer metal) {
        this.metal = metal;
    }

    public Integer getStone() {
        return stone;
    }

    public void setStone(Integer stone) {
        this.stone = stone;
    }

}