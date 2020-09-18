package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;

import java.util.List;
import java.util.Random;

import com.codecool.snake.entities.snakes.SnakeLaser;
import javafx.animation.PathTransition;
import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class CirclingEnemy extends Enemy implements Interactable, Animatable {

    private Point2D heading;
    private static Random rnd = new Random();

    public CirclingEnemy() {
        super(5);

        setImage(Globals.getInstance().getImage("BlueEnemy"));

        setStartCoordinates();

        double direction = rnd.nextDouble() * 360;
        setRotate(direction);

        int speed = 1;
        heading = Utils.directionToVector(direction, speed);

        Circle circle = new Circle(500, 300,  300);
        PathTransition transition = new PathTransition();
        transition.setNode(this);
        transition.setDuration(Duration.seconds(3));
        transition.setPath(circle);
        transition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        transition.setCycleCount(PathTransition.INDEFINITE);
        transition.play();
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            setStartCoordinates();
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SnakeHead){
            System.out.println(getMessage());
            destroy();
            new CirclingEnemy();
        }
        if(entity instanceof SnakeLaser){
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return (getDamage() + " damage");
    }

}

