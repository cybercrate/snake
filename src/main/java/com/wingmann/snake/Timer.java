package com.wingmann.snake;

class Timer {
    private final long duration;
    private long remainingTime;

    public Timer(long duration) {
        this.duration = duration;
        this.remainingTime = duration;
    }

    public void update(long timePassed) {
        remainingTime -= timePassed;
    }

    public boolean timeIsUp() {
        return remainingTime <= 0;
    }

    public void reset() {
        remainingTime = duration;
    }
}
