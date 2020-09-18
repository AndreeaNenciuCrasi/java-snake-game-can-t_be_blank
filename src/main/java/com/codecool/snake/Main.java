package com.codecool.snake;

import com.codecool.snake.entities.snakes.Snake;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
//        BorderPane bp = new BorderPane();
        Game game = new Game();
        Scene mainScene = new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT);
        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(mainScene);
//        Pane p =new Pane();
//        p.setPrefWidth(50);
//        p.setPrefHeight(50);
//        p.setBackground(new Background(new BackgroundFill(Color.rgb(255,255,153), CornerRadii.EMPTY, Insets.EMPTY)));
//        bp.setCenter(game);
//        bp.setTop(p);
        primaryStage.show();
        game.start();
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Exiting..");
    }
}
