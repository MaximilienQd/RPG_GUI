package com.example.rpg_gui;

import java.util.ArrayList;

public class ToGoGUI {
    Game game = new Game();
    public void submitBtn(int numHeroes, String[][] team) {
        game.start(numHeroes, team);
    }

}
