package com.example.rpg_gui;

public class Hero extends Combattant {

    private Warrior warrior = new Warrior();
    private Hunter hunter = new Hunter();
    private Mage mage = new Mage();
    private Healer healer = new Healer();

    private String name, heroClass;
    private int mana, manaMax, arrows;

    public Hero(String name, String heroClass) {
        super(0, 0, 0); //rien avant, pas de variable modifiée avant dans les parenthèses,
        this.name = name;
        this.heroClass = heroClass;
        switch(heroClass) {
            case "Warrior" -> {
                setShield(warrior.getShield());
                this.manaMax = warrior.getMana();
                this.mana = this.manaMax;
                setMaxPv(warrior.getPv());
                setDamage(warrior.getDamage());
            }
            case "Hunter" -> {
                setShield(hunter.getShield());
                this.arrows = hunter.getArrows();
                setMaxPv(hunter.getPv());
                setDamage(hunter.getDamage());
            }
            case "Mage" -> {
                setShield (mage.getShield());
                this.manaMax = mage.getMana();
                this.mana = this.manaMax;
                setMaxPv(mage.getPv());
                setDamage(mage.getDamage());
            }
            case "Healer" -> {
                setShield(healer.getShield());
                this.manaMax = healer.getMana();
                this.mana = this.manaMax;
                setMaxPv(healer.getPv());
                setDamage(healer.getDamage());
            }
        }
    }
    public String getName() {
        return name;
    }
    public String getHeroClass() {
        return heroClass;
    }
    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getManaMax() {
        return manaMax;
    }
    public void setManaMax(int manaMax) {
        this.manaMax = manaMax;
    }
    public int getArrows() {
        return arrows;
    }
    public  void setArrows(int arrows){
        this.arrows = arrows;
    }

    public void addMana(int mana){
        this.mana += mana;
        if(this.mana > this.manaMax){
            this.mana = this.manaMax;
        }
    }
    public void removeMana(int mana){
        this.mana -= mana;
        if(this.mana < 0){
            this.mana = 0;
        }
    }

}
