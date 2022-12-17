package com.example.rpg_gui;

public class Combattant {

    private int pv, maxPv, shield, damage;


    public Combattant(int maxPv, int damage, int shield){
        this.maxPv = maxPv;
        this.pv = this.maxPv;
        this.shield = shield;
        this.damage = damage;
    }


    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public void addPv(int pv){
        this.pv += pv;
        if(this.pv > this.maxPv){
            this.pv = this.maxPv;
        }
    }
    public void removePv(int pv){
        this.pv -= pv;
        if(this.pv < 0){
            this.pv = 0;
        }
    }
    public void removeDamage(int damage){
        this.damage -= damage;
        if(this.damage < 0){
            this.damage = 0;
        }
    }
    public void addDamage(int damage){
        this.damage += damage;
    }

    public int getMaxPv() {
        return maxPv;
    }

    public void setMaxPv(int maxPv) {
        this.maxPv = maxPv;
    }

    public int getShield() {
        return shield;
    }

    public void setShield(int shield) {
        this.shield = shield;
    }
    public void addShield(int shield){
        this.shield += shield;

    }
}
