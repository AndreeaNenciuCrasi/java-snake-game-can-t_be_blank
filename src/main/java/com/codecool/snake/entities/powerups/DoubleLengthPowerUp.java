package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import java.util.Random;


public class DoubleLengthPowerUp extends GameEntity implements Interactable {
    private static Random rnd = new Random();

    public DoubleLengthPowerUp(){
        setImage(Globals.getInstance().getImage("PowerUpBurger"));

        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }

    @Override
    public void apply(GameEntity entity) {
        if (entity instanceof SnakeHead){
            System.out.println(getMessage());
            setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
            setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
        }
    }

    @Override
    public String getMessage() {
        return "Double length power-up !";
    }
}
