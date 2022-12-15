package com.example.rpg_gui;

import java.util.ArrayList;

public class ToGoGUI {

    public void submitBtn(int numHeroes, String[][] team) {

        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> classes = new ArrayList<>();
        for (int i = 0; i < numHeroes; i++) {
            names.add(team[i][0]);
            classes.add(team[i][1]);
        }

        for (int i = 0; i < numHeroes; i++) {
            System.out.println(names.get(i) + " " + classes.get(i));
        }

    }

}
