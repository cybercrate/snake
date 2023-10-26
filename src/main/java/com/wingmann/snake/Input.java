package com.wingmann.snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

class Input implements KeyListener {
    private final List<Event> events = new ArrayList<>();

    @Override
    public void keyTyped(KeyEvent event) {
        // We're not interested in this kind of event
    }

    @Override
    public void keyPressed(KeyEvent event) {
        synchronized(this) {
            events.add(new Event.KeyPressed(event));
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
        synchronized(this) {
            events.add(new Event.KeyReleased(event));
        }
    }

    public List<Event> consumeEvents() {
        synchronized(this) {
            List<Event> consumedEvents = new ArrayList<>(events);
            events.clear();
            return consumedEvents;
        }
    }

    public static class Event {
        public static class KeyPressed extends Event {
            public KeyEvent data;

            public KeyPressed(KeyEvent data) {
                this.data = data;
            }
        }

        public static class KeyReleased extends Event {
            public KeyEvent data;

            public KeyReleased(KeyEvent data) {
                this.data = data;
            }
        }
    }
}