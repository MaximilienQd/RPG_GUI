package com.example.rpg_gui;

import java.util.Scanner;

public class TxtUtility {

    private Scanner sc = new Scanner(System.in);

    // C'est pour clear la console en cas de besoin
    public void clear() {
        for (int i = 0; i < 200; i++) {
            System.out.println();
        }
    }

    public void separator(int n, String s) {
        for (int i = 0; i < n; i++) {
            System.out.print(s);
        }
        System.out.println();
    }

    //Pour avoir des petits tirets au dessus et en dessous des titres
    public void printTitle(String prompt) {
        separator(prompt.length(), "*");
        System.out.println(prompt);
        separator(prompt.length(), "*");
    }
    /* Pour vérifier que l'utilisateur répond des réponses valables à mes questions */
    public int askInt(String prompt, int nbrChoices) {
        int choice = 0;
        do {
            System.out.println(prompt);
            choice = sc.nextInt();
            if(choice < 1 || choice > nbrChoices) {
                System.out.println("Réponse fausse");
            }
        } while(choice < 1 || choice > nbrChoices);

        return choice;
    }

    //Pour entrer une touche dans la console pour continuer
    public void anythingToContinue(){
        System.out.println("Entrez n'importe quelle touche pour continuer");
        sc.nextLine();
        separator(70, "-");
    }
}
