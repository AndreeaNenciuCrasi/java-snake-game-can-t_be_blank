package com.codecool.snake.eventhandler;

import com.codecool.snake.Globals;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ClickRestartHandler implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {

        Globals.getInstance().stopGame();
        Globals.getInstance().game.getSnakes().clear();
        Globals.getInstance().display.clear();
        Globals.getInstance().game.init();
        Globals.getInstance().game.start();

    }
}
