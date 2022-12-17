package com.example.rpg_gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.shape.Line;
import javafx.scene.Group;

public class Main extends Application {

    private ToGoGUI toGoGUI = new ToGoGUI();
    //créer une combobox pour sélectionner le nombre de héros
    static ComboBox<Integer> numHeroesComboBox;
    //créer des éléments
    static Label nameLabel;
    static Label classLabel;
    static TextField[] nameFields;
    static ComboBox<String>[] classComboBoxes;
    Scene combatScene, heroCrea;

    static ChoiceBox<String> actionChoiceBox;
    static ChoiceBox<String> targetChoiceBox;
    static Button validateBtn;
    static Image combatImg;
    static BackgroundImage combatBgImg;
    static Background combatBg;
    static GridPane combat;
    static Label labelTitre;
    static Label labelHeroes;
    static Label labelEnemies;
    static Image heroCreaImg;
    static BackgroundImage heroBgImg;
    static Background heroBg;
    @Override
    public void start(Stage primaryStage) {

        VBox heroCreationLayout = new VBox();
        //créer une combobox pour sélectionner le nombre de héros
        numHeroesComboBox = new ComboBox<>();
        numHeroesComboBox.getItems().addAll(1, 2, 3, 4);
        numHeroesComboBox.setPromptText("Number of Heroes");
        //créer le gridpane
        GridPane form = new GridPane();
        //Ajouter le gridpane
        heroCreationLayout.getChildren().addAll(numHeroesComboBox, form);
        //créer des éléments
        nameLabel = new Label("Nom:");
        classLabel = new Label("Classe:");
        nameFields = new TextField[4];
        classComboBoxes = new ComboBox[4];
        for (int i = 0; i < 4; i++) {
            nameFields[i] = new TextField();
            classComboBoxes[i] = new ComboBox<>();
            classComboBoxes[i].getItems().addAll("Warrior", "Hunter", "Mage", "Healer");
            classComboBoxes[i].setDisable(true);
        }
        for (int i = 0; i < 4; i++) {
            nameFields[i].setDisable(true);
            classComboBoxes[i].setDisable(true);
        }
        //Ajouter des éléments au gridpane
        form.addRow(0, nameLabel, classLabel);
        for (int i = 0; i < 4; i++) {
            form.addRow(i+1, nameFields[i], classComboBoxes[i]);
        }
        //Créer le bouton submit
        Button submitBtn = new Button("Submit");
        //Ajouter le bouton submit
        heroCreationLayout.getChildren().add(submitBtn);
        submitBtn.setDisable(true);
        numHeroesComboBox.setOnAction(e -> {
            int numHeroes = numHeroesComboBox.getValue();
            submitBtn.setDisable(false);
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
        submitBtn.setOnAction(e -> {
            int numHeroes = numHeroesComboBox.getValue();
            String[][] team = new String[numHeroes][2];
            for (int i = 0; i < numHeroes; i++) {
                team[i][0] = nameFields[i].getText();
                team[i][1] = classComboBoxes[i].getValue();
            }
            System.out.println("Team:");
            for (int i = 0; i < numHeroes; i++) {
                System.out.println("Name: " + team[i][0] + ", Class: " + team[i][1]);
            }

            primaryStage.setScene(combatScene);
            toGoGUI.submitBtn(numHeroes, team);

        });

        //Ajout d'un fond d'écran
        heroCreaImg = new Image("file:PorteBois.png");
        heroBgImg = new BackgroundImage(heroCreaImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1.0, 1.0, true, true, false, false));
        heroBg = new Background(heroBgImg);
        heroCreationLayout.setBackground(heroBg);



        // Création du GridPane
        combat = new GridPane();

        // Ajout du texte 
        combat.add(new Label("C'est le début de l'aventure"), 0, 0);
        labelTitre = (Label)combat.getChildren().get(0);
        labelTitre.setTextFill(Color.WHITE);
        labelTitre.setTranslateX(500);
        labelTitre.setTranslateY(20);

        //Ajout du showteam héro
        labelHeroes = new Label("Équipe Héros");
        //labelHeroes = (Label)combat.getChildren().get(1);
        labelHeroes.setTextFill(Color.WHITE);
        labelHeroes.setTranslateX(50);
        labelHeroes.setTranslateY(20);
        combat.getChildren().add(labelHeroes);

        //Ajout du showteam ennemis
        combat.add(new Label("Équipe Ennemie"), 0, 0);
        labelEnemies = (Label)combat.getChildren().get(2);
        labelEnemies.setTextFill(Color.WHITE);
        labelEnemies.setTranslateX(1000);
        labelEnemies.setTranslateY(20);



        // Ajout du menu déroulant Action du Héros
        actionChoiceBox = new ChoiceBox<>();
        actionChoiceBox.getItems().addAll("Attaque", "Attaque Chargée", "Se Défendre", "Soin", "Soin Chargé");
        actionChoiceBox.setValue("Action du Héros");
        combat.add(actionChoiceBox, 0, 0);
        actionChoiceBox.setTranslateX(50);
        actionChoiceBox.setTranslateY(500);

        // Ajout du menu déroulant Cible
        targetChoiceBox = new ChoiceBox<>();
        targetChoiceBox.getItems().addAll("1", "2", "3", "4");
        targetChoiceBox.setValue("Cible");
        combat.add(targetChoiceBox, 0, 0);
        targetChoiceBox.setTranslateX(550);
        targetChoiceBox.setTranslateY(500);

        // Ajout du bouton Valider
        validateBtn = new Button("Valider");
        combat.add(validateBtn, 0, 0);
        validateBtn.setTranslateX(1000);
        validateBtn.setTranslateY(500);

        //Ajout d'un fond d'écran
        combatImg = new Image("file:ForêtLugubre.png");
        combatBgImg = new BackgroundImage(combatImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1.0, 1.0, true, true, false, false));
        combatBg = new Background(combatBgImg);
        combat.setBackground(combatBg);

        // Création de la scène et affichage
        combatScene = new Scene(combat, 1200, 600);
        heroCrea = new Scene(heroCreationLayout, 1200, 700);
        primaryStage.setScene(heroCrea);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}