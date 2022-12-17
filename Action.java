package com.example.rpg_gui;

import java.util.ArrayList;
import java.util.Random;

public class Action {
    private Random rand = new Random();

    // hero attacks enemy
    public void attackHero(String prompt, Hero hero, Enemy enemy, ArrayList<Enemy> enemies, int target) {
        System.out.println(prompt);
        int dmg;

        if (hero.getHeroClass().equals("Hunter")) {
            if (hero.getArrows() == 0) {
                dmg = hero.getDamage() - 20;
                System.out.println("Vous n'avez plus de flèches, vous infligez " + dmg + " dégats");
            } else {
                dmg = hero.getDamage();
                System.out.println("Vous infligez " + dmg + " dégâts");
                hero.setArrows(hero.getArrows() - 1);
            }
        } else {
            dmg = hero.getDamage();
        }

        // si c'est un mage, enlever 5 mana
        if (hero.getHeroClass().equals("Mage")) {
            hero.removeMana(5);
        }

        enemy.removePv(dmg + dmg * enemy.getShield() / 100);
        if (enemy.getPv() <= 0) {
            System.out.println(enemy.getEnemyType() + " est mort");
            enemies.remove(target);
        } else {
            System.out.println(enemy.getEnemyType() + " a " + enemy.getPv() + "/" + enemy.getMaxPv() + " points de vie");
        }
    }
    // enemy attacks hero
    public void attackEnemy(String prompt, Enemy enemy, Hero hero) {
        System.out.println(prompt);
        int dmg = enemy.getDamage() - enemy.getDamage() * hero.getShield() / 100;
        hero.removePv(dmg);
        if (hero.getPv() <= 0){
            System.out.println(hero.getName() + " est mort");
        }
        else {
            System.out.println(hero.getName() + " a " + hero.getPv() + "PV/" + hero.getMaxPv());
        }
    }


    public void attackHeroCharged(String prompt, Hero hero, Enemy enemy, ArrayList<Enemy> enemies, int target){
        if (hero.getHeroClass().equals("Hunter")){
            if (hero.getArrows() >= 3) {
                System.out.println(prompt);

                hero.setArrows(hero.getArrows() - 3);

                System.out.println(prompt);
                enemy.removePv(hero.getDamage() * 2 + hero.getDamage() * enemy.getShield() /100);
                if (enemy.getPv() <= 0){
                    System.out.println(enemy.getEnemyType() + " est mort");
                    enemies.remove(target);
                }
                else {
                    System.out.println(enemy.getEnemyType() + " a " + enemy.getPv() + "PV/" + enemy.getMaxPv());
                }
            } else {
                System.out.println("Vous n'avez pas assez de flèches");
            }
        } else {
            System.out.println(prompt);

            enemy.removePv(hero.getDamage() * 2 + hero.getDamage() * enemy.getShield() /100);
            hero.removeMana(15);
            if (enemy.getPv() <= 0){
                System.out.println(enemy.getEnemyType() + " est mort");
                enemies.remove(target);
            }
            else {
                System.out.println(enemy.getEnemyType() + " a " + enemy.getPv() + "PV/" + enemy.getMaxPv());
            }
        }

    }

    //Heal à un héros
    public void heal(Hero hero, Hero target, String prompt){
        System.out.println(prompt);
        if (target.getPv() < target.getMaxPv()){
            target.addPv(20);
        }
        if (target.getPv() > target.getMaxPv()){
            target.setPv(target.getMaxPv());
        }
        hero.removeMana(5);
        System.out.println(target.getName() + " a " + target.getPv() + "/" + target.getMaxPv() + " points de vie");
    }

    //Heal à tous les héros
    public void healCharged(Hero hero, ArrayList<Hero> heroes, String prompt){
        System.out.println(prompt);
        //boucle soignant chaque héros de l'équipe
        for (Hero target : heroes){
            if (target.getPv() < target.getMaxPv()){
                target.addPv(15);
            }
            if (target.getPv() > target.getMaxPv()){
                target.setPv(target.getMaxPv());
            }
        }
        hero.removeMana(15);
    }

    public int defend(String prompt, Hero hero){
        int bonusShield = 10 + rand.nextInt(11);

        System.out.println(prompt);
        hero.addShield(bonusShield);

        return bonusShield;
    }
}


