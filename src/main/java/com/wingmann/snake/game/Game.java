package com.wingmann.snake.game;

import com.wingmann.snake.util.Input;
import com.wingmann.snake.scene.Scene;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;

public class Game extends Canvas {
    private Dimension screenSize;
    private Scene scene;
    private Input input;
    private Thread gameLoop;

    public Game(Dimension screenSize) {
        this.screenSize = screenSize;
        this.scene = null;
        this.input = new Input();
        this.gameLoop = null;

        setSize(screenSize);
        addKeyListener(input);
    }

    public Dimension getScreenSize() {
        return screenSize;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Input getInput() {
        return input;
    }

    public void play() {
        if (gameLoop != null) {
            return;
        }
        gameLoop = new Thread(new Runnable() {
            @Override
            public void run() {
                long lastIterationTime = System.nanoTime();

                while (!Thread.currentThread().isInterrupted()) {
                    Scene scene = Game.this.scene;
                    if (scene == null) {
                        continue;
                    }
                    long now = System.nanoTime();
                    long timePassed = now - lastIterationTime;

                    lastIterationTime = now;
                    scene.update(timePassed);

                    scene.draw((Graphics2D) getBufferStrategy().getDrawGraphics());
                    getBufferStrategy().show();
                }
            }
        });
        gameLoop.start();
    }
}
