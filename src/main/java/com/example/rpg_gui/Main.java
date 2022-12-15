package com.example.rpg_gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private ToGoGUI toGoGUI = new ToGoGUI();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //create the root node
        VBox root = new VBox();
        //create the combobox to select the number of heroes
        ComboBox<Integer> numHeroesComboBox = new ComboBox<>();
        numHeroesComboBox.getItems().addAll(1, 2, 3, 4);
        numHeroesComboBox.setPromptText("Number of Heroes");
        //create the gridpane to hold the form elements
        GridPane form = new GridPane();
        //add the gridpane to the root
        root.getChildren().addAll(numHeroesComboBox, form);
        //create the form elements
        Label nameLabel = new Label("Name:");
        Label classLabel = new Label("Class:");
        TextField[] nameFields = new TextField[4];
        ComboBox<String>[] classComboBoxes = new ComboBox[4];
        for (int i = 0; i < 4; i++) {
            nameFields[i] = new TextField();
            classComboBoxes[i] = new ComboBox<>();
            classComboBoxes[i].getItems().addAll("Warrior", "Hunter", "Mage", "Healer");
            classComboBoxes[i].setDisable(true);
        }
        //add the form elements to the gridpane
        form.addRow(0, nameLabel, classLabel);
        for (int i = 0; i < 4; i++) {
            form.addRow(i+1, nameFields[i], classComboBoxes[i]);
        }
        //create the submit button
        Button submitBtn = new Button("Submit");
        //add the submit button to the root
        root.getChildren().add(submitBtn);
        //handle the number of heroes selected
        numHeroesComboBox.setOnAction(e -> {
            //nameFields[].setDisable(true);
            int numHeroes = numHeroesComboBox.getValue();
            for (int i = 0; i < 4; i++) {
                if (i < numHeroes) {
                    nameFields[i].setDisable(false);
                    classComboBoxes[i].setDisable(false);
                } else {
                    nameFields[i].setDisable(true);
                    classComboBoxes[i].setDisable(true);
                }
            }
        });
        //handle the submit button
        submitBtn.setOnAction(e -> {
            int numHeroes = numHeroesComboBox.getValue();
            String[][] team = new String[numHeroes][2];
            for (int i = 0; i < numHeroes; i++) {
                team[i][0] = nameFields[i].getText();
                team[i][1] = classComboBoxes[i].getValue();
            }
            //do something with the team array
            System.out.println("Team:");
            for (int i = 0; i < numHeroes; i++) {
                System.out.println("Name: " + team[i][0] + ", Class: " + team[i][1]);
            }

            toGoGUI.submitBtn(numHeroes, team);

        });
        //create the scene
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}