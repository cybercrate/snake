package com.wingmann.snake;

import java.awt.Graphics2D;

abstract class Scene {
    public Game game;

    public Scene(Game game) {
        this.game = game;
    }

    public abstract void update(long timePassed);

    public abstract void draw(Graphics2D graphics);
}