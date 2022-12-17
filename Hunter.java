package com.example.rpg_gui;

public class Hunter {


    private int pv = 80;
    private int shield = 20;
    private int damage = 60;
    private int arrows = 30;

    public int getPv() {
        return pv;
    }

    public int getShield() {
        return shield;
    }

    public int getDamage() {
        return damage;
    }
    public int getArrows(){
        return arrows;
    }
    public void removeArrows(int arrows){
        this.arrows -= arrows;
        if(this.arrows < 0){
            this.arrows = 0;
        }
    }
    public void addArrows(int arrows){
        this.arrows += arrows;
        if(this.arrows > 30) {
            this.arrows = 30;
        }
    }
}
