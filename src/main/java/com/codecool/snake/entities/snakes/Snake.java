package com.codecool.snake.entities.snakes;

import com.codecool.snake.DelayedModificationList;
import com.codecool.snake.Globals;
import com.codecool.snake.PopupScreen;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.eventhandler.InputHandler;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;


public class Snake implements Animatable {
    private static float speed;
    private int health;
    private int id;

    private SnakeHead head;
    private DelayedModificationList<GameEntity> body;
    private int lengthBodyPartsTotal = 0;

    public Snake(Point2D position, int id, String imageHead) {
        head = new SnakeHead(this, position, imageHead);
        body = new DelayedModificationList<>();
        this.id = id;
        this.health = 100;
        this.speed = 1.5f;

        addPart(4);
    }

    public int getHealth() {
        return health;
    }


    public void setHealth(int health) {
        if (health > 100){
            health=100;
        }
        this.health = health;
    }

    public void step() {
        SnakeControl turnDir = null;
        switch (id){
            case 0:
                turnDir =getUserInput(KeyCode.LEFT , KeyCode.RIGHT, KeyCode.SPACE);
                break;
            case 1:
                turnDir = getUserInput(KeyCode.A,KeyCode.D, KeyCode.S);
                break;
            default:
                System.out.println("System error direction");
        }

        head.updateRotation(turnDir, speed);

        updateSnakeBodyHistory();

        body.doPendingModifications();
    }

    private SnakeControl getUserInput(KeyCode turnLeft, KeyCode turnRight, KeyCode shoot) {
        SnakeControl turnDir = SnakeControl.INVALID;
        if(InputHandler.getInstance().isKeyPressed(turnLeft)) turnDir = SnakeControl.TURN_LEFT;
        if(InputHandler.getInstance().isKeyPressed(turnRight)) turnDir = SnakeControl.TURN_RIGHT;
        if(InputHandler.getInstance().isKeyPressed(shoot)) turnDir = SnakeControl.SHOOT;
        return turnDir;
    }

    public void addPart(int numParts) {

        GameEntity parent = getLastPart();
        Point2D position = parent.getPosition();

        for (int i = 0; i < numParts; i++) {
            SnakeBody newBodyPart = new SnakeBody(position);
            body.add(newBodyPart);
            this.lengthBodyPartsTotal++;
        }
        Globals.getInstance().display.updateSnakeHeadDrawPosition(head);
    }

    public void changeHealth(int diff) {
        health += diff;
        if (health>100){
            health =100;
        }
    }


    public static float getSpeed() {
        return speed;
    }

    public static void setSpeed(float speed) {
        Snake.speed = speed;
    }

    public SnakeHead getHead() {
        return head;
    }

    public int getLengthBodyPartsTotal() {
        return lengthBodyPartsTotal;
    }

    private void updateSnakeBodyHistory() {
        GameEntity prev = head;
        for(GameEntity currentPart : body.getList()) {
            currentPart.setPosition(prev.getPosition());
            prev = currentPart;
        }
    }

    private GameEntity getLastPart() {
        GameEntity result = body.getLast();

        if(result != null) return result;
        return head;
    }
}
