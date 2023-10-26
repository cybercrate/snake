package com.wingmann.snake;

import java.util.ArrayList;
import java.util.List;

class Snake {
    public int startX;
    public int startY;
    public Direction direction;
    public List<SnakePart> body;
    public SnakePart head;

    public Snake(int startX, int startY, Direction direction) {
        this.startX = startX;
        this.startY = startY;
        this.direction = direction;
        this.body = new ArrayList<>();
        this.head = null;
        init();
    }

    public Snake(int startX, int startY) {
        this(startX, startY, Direction.RIGHT);
    }

    private void init() {
        body.add(new SnakePart(startX, startY));
        body.add(new SnakePart(startX - direction.deltaX(), startY - direction.deltaY()));
        body.add(new SnakePart(startX - direction.deltaX() * 2, startY - direction.deltaY() * 2));
        head = body.get(0);
    }

    public void move() {
        for (int i = body.size() - 1; i >= 1; i--) {
            SnakePart current = body.get(i);
            SnakePart previous = body.get(i - 1);
            current.setX(previous.getX());
            current.setY(previous.getY());
        }
        head.setX(head.getX() + direction.deltaX());
        head.setY(head.getY() + direction.deltaY());
    }
}