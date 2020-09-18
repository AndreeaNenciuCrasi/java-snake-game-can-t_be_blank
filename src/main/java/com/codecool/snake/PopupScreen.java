package com.codecool.snake;

import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class PopupScreen {
    public static void displayGameOver(Game game){
        Text textGameOver = new Text("GAME OVER");
        textGameOver.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR, 100));
        textGameOver.setX(200);
        textGameOver.setY(200);

        Text textScoreResults = new Text("Snake 1 Score " + game.getSnakes().get(0).getLengthBodyPartsTotal() + " ft."
                                + "\n" + "Snake 2 Score " + game.getSnakes().get(1).getLengthBodyPartsTotal() + " ft.");
        textScoreResults.setFont(Font.font("arial", FontWeight.NORMAL, FontPosture.REGULAR, 50));
        textScoreResults.setX(300);
        textScoreResults.setY(300);

        Button restart = new Button("Restart");
        restart.setStyle("-fx-background-color: yellow; -fx-text-fill: black");
        restart.setLayoutX(400);
        restart.setLayoutY(500);

        restart.setOnAction(e -> {
            game.getSnakes().clear();
            Globals.getInstance().display.clear();
            game.init();
            game.start();
            System.out.println("Restart begin");
        });

        Button exitGame = new Button("Exit Game");

        exitGame.setLayoutX(500);
        exitGame.setLayoutY(500);

        exitGame.setOnAction(e -> Platform.exit());

        game.getChildren().addAll(textGameOver, textScoreResults, restart, exitGame);
    }

}
