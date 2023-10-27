package com.wingmann.snake.scene;

import com.wingmann.snake.game.Game;

import java.awt.Graphics2D;

public abstract class Scene {
    private Game game;

    public Scene(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public abstract void update(long timePassed);

    public abstract void draw(Graphics2D graphics);
}
