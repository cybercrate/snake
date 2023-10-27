package com.wingmann.snake.game;

import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.WindowConstants;
import javax.swing.JFrame;

public class GameFactory {
    public static Game create(Dimension screenSize, String windowTitle) {
        Game game = new Game(screenSize);

        JFrame frame = new JFrame();
        frame.setTitle(windowTitle);
        frame.setVisible(true);

        frame.setLayout(new BorderLayout());
        frame.add(game);
        frame.pack();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        game.createBufferStrategy(2);
        game.requestFocus();

        return game;
    }
}
