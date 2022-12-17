package com.example.rpg_gui;

import java.util.ArrayList;

public class ToBackGUI {

    public void showHeroes(ArrayList<Hero> heroes) {
        for (Hero hero : heroes) {
            Main.labelHeroes.accessibleTextProperty().setValue(hero.getName() + " " + hero.getPv() + "/" + hero.getMaxPv() + " PV\n");
            System.out.println(hero);
        }
    }


}
