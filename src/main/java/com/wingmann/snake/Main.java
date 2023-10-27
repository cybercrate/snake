package com.wingmann.snake;

import com.wingmann.snake.data.Constants;
import com.wingmann.snake.data.Strings;
import com.wingmann.snake.game.Game;
import com.wingmann.snake.game.GameFactory;
import com.wingmann.snake.scene.MainMenuScene;

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
