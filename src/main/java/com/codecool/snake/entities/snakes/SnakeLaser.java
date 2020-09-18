package com.codecool.snake.entities.snakes;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.Enemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import javafx.geometry.Point2D;

import java.util.Random;

public class SnakeLaser extends GameEntity implements Animatable,Interactable{
    private Point2D heading;
    private Snake snake;
    private static Random rnd = new Random();

    public SnakeLaser(Snake snake) {
        super();
        this.snake=snake;
        setImage(Globals.getInstance().getImage("SnakeLaser"));
        setPosition(snake.getHead().getPosition());
        double direction = snake.getHead().getRotate();

        int speed = 2;
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof Enemy){
            System.out.println(getMessage());
            destroy();
        }

    }

    @Override
    public String getMessage() {
        return ("Snake Shoot ! damage");
    }
}
