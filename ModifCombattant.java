package com.example.rpg_gui;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ModifCombattant {

    private Warrior warrior = new Warrior();
    private Hunter hunter = new Hunter();
    private Mage mage = new Mage();
    private Healer healer = new Healer();

    Scanner sc = new Scanner(System.in);
    TxtUtility tu = new TxtUtility();
    Random rand = new Random();

    public ArrayList<Enemy> createEnemyTeam(int numberOfHero, int nbrCombat, ArrayList<String> enemyTypeList){
        ArrayList<Enemy> enemyTeam = new ArrayList<>();

        for(int i = 0; i < numberOfHero ; i++) {
            String enemyType = enemyTypeList.get(rand.nextInt(enemyTypeList.size()));
            if (nbrCombat > 4) {
                enemyType = "Warboss";
            }
            enemyTeam.add(new Enemy(enemyType, nbrCombat, numberOfHero));
        }

        return enemyTeam;
    }


    public int askHeroTeamAlive(ArrayList<Hero> heroes) {
        int nbrHeroAlive = 0;

        for (Hero hero : heroes) {
            if (hero.getPv() > 0) {
                nbrHeroAlive++;
            }
        }
        return nbrHeroAlive;
    }
}
