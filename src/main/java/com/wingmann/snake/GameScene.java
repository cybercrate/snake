package com.wingmann.snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import java.util.concurrent.TimeUnit;

public class GameScene extends Scene {
    private final Snake snake;
    private Apple apple;
    private final Timer snakeMoveTimer;

    public GameScene(Game game) {
        super(game);
        snake = new Snake(Constants.BOARD_WIDTH / 2, Constants.BOARD_HEIGHT / 2);
        placeApple();
        snakeMoveTimer = new Timer(TimeUnit.MILLISECONDS.toNanos(Constants.DELAY_DURATION));
    }

    @Override
    public void update(long timePassed) {
        if (gameIsOver()) {
            game.setScene(new GameOverScene(game));
            return;
        }
        processInput();
        snakeMoveTimer.update(timePassed);

        if (snakeMoveTimer.timeIsUp()) {
            snake.move();
            SnakePart head = snake.head;

            if (head.getX() < 1) {
                head.setX(Constants.BOARD_WIDTH);
            }

            if (head.getX() > Constants.BOARD_WIDTH) {
                head.setX(1);
            }

            if (head.getY() < 1) {
                head.setY(Constants.BOARD_HEIGHT);
            }

            if (head.getY() > Constants.BOARD_HEIGHT) {
                head.setY(1);
            }

            if (head.getX() == apple.getX() && head.getY() == apple.getY()) {
                java.util.List<SnakePart> body = snake.body;
                SnakePart lastPart = body.get(body.size() - 1);
                body.add(new SnakePart(lastPart.getX(), lastPart.getY()));
                placeApple();
            }

            snakeMoveTimer.reset();
        }
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, game.getWidth(), game.getHeight());
        drawSnake(graphics);
        drawApple(graphics);
    }

    private void processInput() {
        for (Input.Event event : game.getInput().consumeEvents()) {
            if (event instanceof Input.Event.KeyPressed) {
                KeyEvent data = ((Input.Event.KeyPressed) event).data;

                switch (data.getKeyCode()) {
                    case KeyEvent.VK_UP -> snake.direction = Direction.UP;
                    case KeyEvent.VK_RIGHT -> snake.direction = Direction.RIGHT;
                    case KeyEvent.VK_DOWN -> snake.direction = Direction.DOWN;
                    case KeyEvent.VK_LEFT -> snake.direction = Direction.LEFT;
                }
            }
        }
    }

    private void drawSnake(Graphics2D graphics) {
        graphics.setColor(Color.GREEN);

        for (SnakePart part : snake.body) {
            graphics.fillRect(
                    part.getX() * Constants.CELL_SIZE - Constants.CELL_SIZE,
                    game.getScreenSize().height - part.getY() * Constants.CELL_SIZE,
                    Constants.CELL_SIZE,
                    Constants.CELL_SIZE);
        }
    }

    private void drawApple(Graphics2D graphics) {
        graphics.setColor(Color.RED);

        graphics.fillRect(
                apple.getX() * Constants.CELL_SIZE - Constants.CELL_SIZE,
                game.getScreenSize().height - apple.getY() * Constants.CELL_SIZE,
                Constants.CELL_SIZE,
                Constants.CELL_SIZE);
    }

    private void placeApple() {
        int x = (int) (1 + (Math.random() * Constants.BOARD_WIDTH));
        int y = (int) (1 + (Math.random() * Constants.BOARD_HEIGHT));

        while (!isCellEmpty(x, y)) {
            if (x < Constants.BOARD_WIDTH) {
                x++;
            } else {
                if (y < Constants.BOARD_HEIGHT) {
                    x = 1;
                    y++;
                } else {
                    x = 1;
                    y = 1;
                }
            }
        }
        apple = new Apple(x, y);
    }

    private boolean isCellEmpty(int x, int y) {
        for (SnakePart part : snake.body) {
            if (part.getX() == x && part.getY() == y) {
                return false;
            }
        }
        return true;
    }

    private boolean gameIsOver() {
        if (snake.body.size() == Constants.BOARD_WIDTH * Constants.BOARD_HEIGHT) {
            return true;
        }
        for (int index = 1; index < snake.body.size(); index++) {
            SnakePart part = snake.body.get(index);
            if (part.getX() == snake.head.getX() && part.getY() == snake.head.getY()) {
                return true;
            }
        }
        return false;
    }
}