package com.codecool.snake.entities.snakes;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.Enemy;
import com.codecool.snake.entities.powerups.DoubleLengthPowerUp;
import com.codecool.snake.entities.powerups.SimplePowerUp;

import com.codecool.snake.entities.powerups.SpeedBoostPowerUp;
import javafx.geometry.Point2D;


public class SnakeHead extends GameEntity implements Interactable {
    private static final float turnRate = 2;
    private Snake snake;

    public SnakeHead(Snake snake, Point2D position, String imageHead) {
        this.snake = snake;
        setImage(Globals.getInstance().getImage(imageHead));

        setPosition(position);
    }

    public void updateRotation(SnakeControl turnDirection, float speed) {
        double headRotation = getRotate();

        if (turnDirection.equals(SnakeControl.TURN_LEFT)) {
            headRotation = headRotation - turnRate;
        }
        if (turnDirection.equals(SnakeControl.TURN_RIGHT)) {
            headRotation = headRotation + turnRate;
        }

        if (turnDirection.equals(SnakeControl.SHOOT)) {
            new SnakeLaser(snake);
        }

        // set rotation and position
        setRotate(headRotation);
        Point2D heading = Utils.directionToVector(headRotation, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof Enemy){
            System.out.println(getMessage());
            snake.changeHealth(-((Enemy) entity).getDamage());
        }
        if(entity instanceof SimplePowerUp){
            System.out.println(getMessage());
            snake.addPart(1);
        }
        if(entity instanceof DoubleLengthPowerUp){
            System.out.println(getMessage());
            snake.addPart(2);
        }
        if(entity instanceof SpeedBoostPowerUp){
            System.out.println(getMessage());
            Snake.setSpeed(Snake.getSpeed() + 0.5f);
        }
    }

    @Override
    public String getMessage() {
        return "IMMA SNAEK HED! SPITTIN' MAH WENOM! SPITJU-SPITJU!";
    }
}
