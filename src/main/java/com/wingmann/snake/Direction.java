package com.wingmann.snake;

enum Direction {
    UP,
    RIGHT,
    DOWN,
    LEFT;

    public int deltaX() {
        return switch (this) {
            case LEFT -> -1;
            case RIGHT -> 1;
            default -> 0;
        };
    }

    public int deltaY() {
        return switch (this) {
            case UP -> 1;
            case DOWN -> -1;
            default -> 0;
        };
    }
}
