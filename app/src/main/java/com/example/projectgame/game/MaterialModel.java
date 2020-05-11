package com.example.projectgame.game;

import java.util.ArrayList;
import java.util.List;

public class MaterialModel {
    private List<String> material, material2;
    private List<Integer> amount, amount2;

    public MaterialModel(){
        material = new ArrayList<>();
        material2 = new ArrayList<>();
        amount = new ArrayList<>();
        amount2 = new ArrayList<>();
        material.add("stone");
        material.add("wood");
        material2.add("wood");
        material2.add("stone");
        amount.add(44);
        amount.add(99);
        amount2.add(78);
        amount2.add(87);
    }

    public List<String> getMaterial(){
        return material;
    }

    public List<String> getMaterial2(){
        return material2;
    }

    public void addAmount(int a){
        amount.add(a);
    }

    public void addAmount2(int a){
        amount2.add(a);
    }

    public void addMaterial(String a){
        material.add(a);
    }

    public void addMaterial2(String a){
        material2.add(a);
    }

    public List<Integer> getAmount(){
        return amount;
    }

    public List<Integer> getAmount2(){
        return amount2;
    }
}
