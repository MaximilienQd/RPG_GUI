package com.example.rpg_gui;

public class Enemy extends Combattant {

    private Orc orc = new Orc();
    private Warboss warboss = new Warboss();
    private Gobelin gobelin = new Gobelin();

    private String enemyType;
    public Enemy(String enemyType, int nbrCombat, int nbHero) {
        super(0,0,0);
        this.enemyType = enemyType;
        switch(enemyType) {
            case "Orc" -> {
                setMaxPv(orc.getPv() + nbrCombat * 10);
                setShield(orc.getShield());
                setDamage(orc.getDamage() + nbrCombat * 5);
            }
            case "Warboss" -> {
                setMaxPv(100 * nbHero + nbrCombat * 15);
                setShield(warboss.getShield());
                setDamage(10 * nbHero + nbrCombat * 10);
            }
            case "Gobelin" -> {
                setMaxPv(gobelin.getPv() + nbrCombat * 10);
                setShield(gobelin.getShield());
                setDamage(gobelin.getDamage() + nbrCombat * 5);
            }
        } setPv(getMaxPv());
    }

    public String getEnemyType() {
        return enemyType;
    }

}
