package com.wingmann.snake;

import java.awt.Dimension;

public class Main {
    public static void main(String[] args) {
        Game game = GameFactory.create(
                new Dimension(Constants.WINDOW_SIZE, Constants.WINDOW_SIZE),
                Strings.WINDOW_TITLE);
        game.setScene(new MainMenuScene(game));
        game.play();
    }
}