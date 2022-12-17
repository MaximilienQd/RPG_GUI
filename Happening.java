package com.example.rpg_gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Happening {
    private TxtUtility tu = new TxtUtility();
    private ArrayList<String> encounters = new ArrayList<>(Arrays.asList("FoodBag", "NothingHappens", "Stranger", "Pub"));

    Random rand = new Random();

    public void randomEncounter(ArrayList<Hero> heroes, int numberOfHero){
        String encounter = encounters.get(rand.nextInt(encounters.size()));
        switch(encounter){
            case "FoodBag" -> {
                if(tu.askInt("Vous rencontrez un sac plein de nourriture au milieu du chemin.\nQue voulez-vous faire ?\n1 : Manger le contenu du sac\n2 : Continuer à marcher sans manger la nourriture ? ", 2) == 1){
                    eatFood(heroes, numberOfHero);
                } else{
                    System.out.println("Vous ne touchez pas à la nourriture et continuez votre chemin.\nPeut-être que le sac est tombé d'une charette qui passait par là ?\nQui sait ?");
                }
                encounters.remove("FoodBag");
            }
            case "NothingHappens" -> {
                System.out.println("Eh bien il ne se passe rien après ce combat, dommage !");
                encounters.remove("NothingHappens");
            }
            case "Stranger" -> {
                if(tu.askInt("Vous rencontrez un étranger encapuchonné, qui vous donne une énigme à résoudre.\nL'énigme est : Qu'est ce qui a 4 pattes le matin, 2 à midi et 3 le soir ?\nVous êtes obligés de répondre à sa question, sinon il anéantit votre équipe.\nQuelle est la réponse :\n1) Le Tabouret\n2) L'Homme\n3) La Vache ou\n4) Le Gobelin ? ", 4) == 2) {
                    goodAnsStranger(heroes, numberOfHero);
                }
                else{
                    badAnsStranger(heroes, numberOfHero);
                }
                encounters.remove("Stranger");
            }
            case "Pub" -> {
                if(tu.askInt("Vous voilà devant une auberge à l'allure inquiétante, voulez-vous y rentrer (A vos risques et périls ...)?\n1) Rentrer dans l'auberge\n2) Continuer votre chemin",2) == 1){
                    enterPub(heroes, numberOfHero);
                } else{
                    System.out.println("Vous continuez votre chemin sans entrer dans l'auberge.\nC'était peut-être la meilleure décision à prendre !");
                }
                encounters.remove("Pub");
            }
        }
    }

    // Coder le happening "Foodbag"
    public void eatFood(ArrayList<Hero> heroes, int numberOfHero) {
        for (int i = 0; i < numberOfHero; i++) {
            heroes.get(i).addPv(10);
            heroes.get(i).removeMana(10);
        }
        System.out.println("Votre équipe mange la nourriture du sac.\nIls gagnent tous 10 PV.\nIls se sentent malheureusement vite nauséeux, la nourriture devait être maudite !!!\nTous les héros ont perdu 10 de Mana");

        // il faudrait modifier les pv en fonction du résultat aléatoire (si ça empoisonne ou non)
    }

    // Coder le happening "Stranger"
    public void goodAnsStranger(ArrayList<Hero> heroes, int numberOfHero){
        for (int i = 0; i < numberOfHero; i++) {
            heroes.get(i).addPv(10);
            heroes.get(i).addMana(10);
            heroes.get(i).addDamage(10);
        }
        System.out.println("Vous avez bien répondu à sa question !\nTous vos héros gagnent +10 en PV, Mana et Dommages !!!");
    }
    public void badAnsStranger(ArrayList<Hero> heroes, int numberOfHero){
        for (int i = 0; i < numberOfHero; i++) {
            heroes.get(i).removePv(10);
            heroes.get(i).removeMana(10);
            heroes.get(i).removeDamage(10);
        }
        System.out.println("Vous avez mal répondu !\nL'étranger a maudit votre équipe, il a retiré 10 aux PV, Mana et Dommages de tous les héros");
    }
    //Coder le happening "Pub"
    public void enterPub(ArrayList<Hero> heroes, int numberOfHero){
        for (int i = 0; i < numberOfHero; i++) {
            heroes.get(i).addPv(10);
            heroes.get(i).addMana(10);
        }
        System.out.println("En fin de compte l'ambiance à l'intérieur de l'auberge est très chaleureuse,\nl'atmosphère est à la fête ! Vos héros passent la nuit dans cette auberge et se reposent tellement qu'ils regagnent quelques PV et tout leur Mana.");
    }

}
