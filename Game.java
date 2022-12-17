package com.example.rpg_gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private boolean isRunning = false;
    private ArrayList<Hero> heroes = new ArrayList<>();
    private int numberOfHero;
    private int nbrCombat = 1;

    private Warboss warboss = new Warboss();
    private ArrayList<Enemy> enemyTeam = new ArrayList<>();
    private ArrayList<String> enemyTypeList = new ArrayList<>(Arrays.asList("Orc", "Gobelin"));
    private Action action = new Action();
    private Happening hap = new Happening();
    private ModifCombattant modComb = new ModifCombattant();
    private TxtUtility tu = new TxtUtility(); //Toujours pour les titres
    private Scanner sc = new Scanner(System.in); //Scanner la réponse de l'utilisateur
    private ToBackGUI tBG = new ToBackGUI();
    private Random rand = new Random();

    public void start(int numberOfHero, String[][] team) {
        //Création de l'équipe dans la fenêtre de combat
        this.numberOfHero = numberOfHero;

        for (int i = 0; i < numberOfHero; i++){
            heroes.add(new Hero(team[i][0], team[i][1]));
        }
        tBG.showHeroes(heroes); //Affiche le showteam
    }

    //Boucle Menu Principal Pour le Joueur
    public void gameLoop(){
        while(isRunning){
            switch(tu.askInt("Action ? Si 1 : Jouer, si 2 : Showteam, si 3 : Quitter le jeu", 3)){
                case 1 -> combat();
                //case 2 -> showHeroTeam();
                case 3 -> isRunning = false;
            }
        }
    }
    //Pour montrer la liste de tous les personnages et leurs caractéristiques
    public void showHeroTeam(int numberOfHero, String[][] team) {
        this.numberOfHero = numberOfHero;
        System.out.println("VOICI TON EQUIPE DE PUISSANTS HEROS !");
        for (int i = 0; i < numberOfHero; i++) {
            System.out.println(heroes.add(new Hero(team[i][0], team[i][1])));
            System.out.println(heroes.get(i).getName() + " | " + heroes.get(i).getHeroClass() + " - Point de Vie : " + heroes.get(i).getPv() + "PV/" + heroes.get(i).getMaxPv() + " Résistance : " + heroes.get(i).getShield() + "Sh - Points de Mana " + heroes.get(i).getMana() + "PM/" + heroes.get(i).getManaMax() + " - " + " Dégâts : " + heroes.get(i).getDamage() + "DMG" + " | ");
        }
    }

    public void showEnemyTeam() {
        tu.separator(70, "-");
        tu.printTitle("Voici l'équipe des méchants ennemis ! ");
        for (int i = 0; i < enemyTeam.size(); i++){
            int crntEnemy = i + 1;
            System.out.println(crntEnemy + ") " + enemyTeam.get(i).getEnemyType() + " | " + enemyTeam.get(i).getPv() + "PV/" + enemyTeam.get(i).getMaxPv() + " | ");
        }
        tu.separator(70, "-");
    }

    // Pour générer une team d'ennemis
    public void generateEnemyTeam(){
        enemyTeam = modComb.createEnemyTeam(numberOfHero, nbrCombat, enemyTypeList);
    }

    public void combat() {
        generateEnemyTeam();
        tu.separator(70, "-");
        System.out.println("VOUS VOUS FAITES ATTAQUER ! Ne vous laissez pas faire ! ");
        System.out.println("C'est le combat " + nbrCombat + " !");

        ArrayList<Integer> bonusShieldList = new ArrayList<>();
        do {
            for (int i = 0; i < numberOfHero; i++) {

                showEnemyTeam();
                System.out.println("Si tous les ennemis sont morts, mais que la fin du combat ne s'est pas déclenchée, \ndonnez l'ordre à vos héros de *Se Défendre* jusqu'à ce que la fin de combat se déclenche");
                int categoryCombatChoice = tu.askInt("Action de " + heroes.get(i).getName() + ": 1) Combattre 2) Défendre", 2);
                switch (categoryCombatChoice) {
                    case 1 -> {
                        int targetInt = tu.askInt("Sur quel ennemi " + heroes.get(i).getName() + " le " + heroes.get(i).getHeroClass() + " va-t-il taper ?", enemyTeam.size());
                        int targetTab = targetInt - 1;
                        switch (heroes.get(i).getHeroClass()) {
                            case "Warrior", "Hunter", "Mage" -> {
                                switch (tu.askInt("Action " + heroes.get(i).getMana() + "PM/" + heroes.get(i).getManaMax() + " : 1) Attaquer 2) Attaque chargée ", 2)) {
                                    case 1 -> {
                                        action.attackHero("Vous attaquez avec une attaque normale", heroes.get(i), enemyTeam.get(targetTab), enemyTeam, targetTab);
                                        bonusShieldList.add(0);
                                    }
                                    case 2 -> {
                                        action.attackHeroCharged("Vous attaquez avec une attaque chargée", heroes.get(i), enemyTeam.get(targetTab), enemyTeam, targetTab);
                                        bonusShieldList.add(0);
                                    }
                                }
                            }
                            case "Healer" -> {
                                action.attackHero("Vous attaquez avec une attaque normale", heroes.get(i), enemyTeam.get(targetTab), enemyTeam, targetTab);
                                bonusShieldList.add(0);
                            }
                        }
                    }
                    case 2 -> {
                        switch (heroes.get(i).getHeroClass()) {
                            case "Warrior", "Hunter", "Mage" -> bonusShieldList.add(action.defend("Vous vous défendez en vous abritant derrière vos armes", heroes.get(i)));
                            case "Healer" -> {
                                switch (tu.askInt("Action " + heroes.get(i).getMana() + "PM/" + heroes.get(i).getManaMax() + "  : 1) Se Défendre, 2) Soigner un allié, 3) Soigner l'équipe", 2)) {
                                    case 1 -> bonusShieldList.add(action.defend("Vous vous défendez en vous abritant derrière votre canif", heroes.get(i)));
                                    case 2 -> {
                                        action.heal(heroes.get(i), heroes.get(tu.askInt("Qui voulez-vous soigner ?", numberOfHero) - 1), "Vous soignez un allié");
                                        bonusShieldList.add(0);
                                    }
                                    case 3 -> {
                                        action.healCharged(heroes.get(i), heroes, "Tous vos héros ont été soignés");
                                        bonusShieldList.add(0);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            enemyTeamAttack();

            for (int i = 0; i < bonusShieldList.size(); i++){
                heroes.get(i).setShield(heroes.get(i).getShield() - bonusShieldList.get(i));
            }

            bonusShieldList.clear();
        } while (enemyTeam.size() > 0 && modComb.askHeroTeamAlive(heroes) > 0);


        if (modComb.askHeroTeamAlive(heroes) <= 0) {
            System.out.println("Vous avez perdu...");
            isRunning = false;
        } else if (enemyTeam.size() == 0) {
            for (int i = 0; i < numberOfHero; i++) {
                switch (heroes.get(i).getHeroClass()) {
                    case "Hunter" -> {
                        heroes.get(i).setArrows(heroes.get(i).getArrows() + 10);
                        if (heroes.get(i).getArrows() > 30) {
                            heroes.get(i).setArrows(30);
                        }
                    }
                    case "Mage", "Healer", "Warrior" -> heroes.get(i).setMana(heroes.get(i).getManaMax());
                }
            }
            System.out.println("Vous avez gagné ce combat ! ");
            nbrCombat++;
            if (nbrCombat > 5) {
                System.out.println("Vous avez gagné la partie !");
                System.out.println("Souvenir de votre équipe : ");
                //showHeroTeam();
                isRunning = false;
            } else {
                tu.separator(70, "-");
                hap.randomEncounter(heroes, numberOfHero);
            }
        }
    }

    public void enemyTeamAttack() {
        //Attaque des ennemis
        for (Enemy enemy : enemyTeam) {
            int heroTarget = rand.nextInt(heroes.size());
            //showHeroTeam();
            action.attackEnemy("Le héros " + heroes.get(heroTarget).getName() + " (" + heroes.get(heroTarget).getHeroClass() + ") s'est fait attaquer", enemy, heroes.get(heroTarget));
        }
    }
}
