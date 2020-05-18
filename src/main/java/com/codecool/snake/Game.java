package com.codecool.snake;

import com.codecool.snake.entities.enemies.ChasingEnemy;
import com.codecool.snake.entities.enemies.CirclingEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.DoubleLengthPowerUp;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.powerups.SpeedBoostPowerUp;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.eventhandler.ClickRestartHandler;
import com.codecool.snake.eventhandler.InputHandler;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import java.math.RoundingMode;



public class Game extends Pane {
    Rectangle healthBarSnake1 = new Rectangle();
    Rectangle healthBarSnake2 = new Rectangle();
    private List<Snake> snakes = new ArrayList<>();
    private GameTimer gameTimer = new GameTimer();
    private int numberOfPlayers = 2;


    public Game() {
        Globals.getInstance().game = this;
        Globals.getInstance().display = new Display(this);
        Globals.getInstance().setupResources();
        init();
    }

    public Rectangle getHealthBarSnake1() {
        return healthBarSnake1;
    }

    public Rectangle getHealthBarSnake2() {
        return healthBarSnake2;
    }


    public void init() {
        spawnSnake();
        spawnEnemies(4);
        spawnPowerUps(4);

        GameLoop gameLoop = new GameLoop(snakes);
        Globals.getInstance().setGameLoop(gameLoop);
        gameTimer.setup(gameLoop::step);
        gameTimer.play();
        Rectangle healthBarBackGround1 = new Rectangle();
        healthBarBackGround1.setX(0);
        healthBarBackGround1.setY(30);
        healthBarBackGround1.setWidth(210);
        healthBarBackGround1.setHeight(30);
        healthBarSnake1.setX(5);
        healthBarSnake1.setY(35);
        healthBarSnake1.setWidth(2 * snakes.get(0).getHealth());

        healthBarSnake1.setHeight(20);
        healthBarSnake1.setFill(Color.GREEN);
        getChildren().addAll(healthBarBackGround1, healthBarSnake1);
        // Snake 2
        Rectangle healthBarBackGround2 = new Rectangle();
        healthBarBackGround2.setX(0);
        healthBarBackGround2.setY(70);
        healthBarBackGround2.setWidth(210);
        healthBarBackGround2.setHeight(30);
        healthBarSnake2.setX(5);
        healthBarSnake2.setY(75);
        healthBarSnake2.setWidth(2 * snakes.get(1).getHealth());

        healthBarSnake2.setHeight(20);
        healthBarSnake2.setFill(Color.PINK);
        getChildren().addAll(healthBarBackGround2, healthBarSnake2);
    }


    public void start() {
        setupInputHandling();
        setRestartButton(Globals.getInstance().game);
        Globals.getInstance().startGame();
    }

    private void spawnSnake() {
        snakes.add(new Snake(new Point2D(500, 500), 0, "SnakeHead"));
        snakes.add(new Snake(new Point2D(400, 400), 1, "SnakeHead2"));
    }

    public List<Snake> getSnakes() {
        return snakes;
    }

    private void spawnEnemies(int numberOfEnemies) {
        new SimpleEnemy();
        for (int i = 0; i < numberOfEnemies / 2; ++i) new ChasingEnemy();
        new CirclingEnemy();
    }

    private void spawnPowerUps(int numberOfPowerUps) {
        for (int i = 0; i < numberOfPowerUps; ++i) new SimplePowerUp();
        for (int i = 0; i < numberOfPowerUps / 2; i++) new DoubleLengthPowerUp();
        for (int i = 0; i < numberOfPowerUps / 4; i++) new SpeedBoostPowerUp();
    }

    private void setupInputHandling() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> InputHandler.getInstance().setKeyPressed(event.getCode()));
        scene.setOnKeyReleased(event -> InputHandler.getInstance().setKeyReleased(event.getCode()));
    }


    public void checkGameOver() {

        int counter = numberOfPlayers;
        for (Snake snake : snakes) {
            if (snake.getHead().isOutOfBounds() || snake.getHealth() <= 0) {
                snake.setHealth(0);
                counter -= 1;
            }
        }
        if (counter == 0 || checkHeadCollision()) {
            Globals.getInstance().stopGame();
            PopupScreen.displayGameOver(Globals.getInstance().game);
        }

    }

    private boolean checkHeadCollision(){
            return Globals.getInstance().game.getSnakes().get(0).getHead().intersects(Globals.getInstance().game.getSnakes().get(1).getHead().getBoundsInLocal());
       }


    public void setRestartButton(Game game) {
        Button button = new Button("Restart");
        // CSS inline
        button.setStyle("-fx-background-color: yellow; -fx-text-fill: black");
        game.getChildren().add(button);
        ClickRestartHandler clickHandler = new ClickRestartHandler();
        button.setOnAction(clickHandler);
        game.requestFocus();
    }
}
