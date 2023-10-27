package com.wingmann.snake.scene;

import com.wingmann.snake.data.Constants;
import com.wingmann.snake.game.Game;
import com.wingmann.snake.util.Input;
import com.wingmann.snake.data.Strings;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class MainMenuScene extends Scene {
    private final Font primaryFont = new Font(Strings.FONT, Font.BOLD, Constants.PRIMARY_FONT_SIZE);
    private final Font secondaryFont = new Font(Strings.FONT, Font.PLAIN, Constants.SECONDARY_FONT_SIZE);

    public MainMenuScene(Game game) {
        super(game);
    }

    @Override
    public void update(long timePassed) {
        getGame().getInput().consumeEvents().forEach(event -> {
            if (event instanceof Input.Event.KeyPressed &&
                    ((Input.Event.KeyPressed) event).data.getKeyCode() == KeyEvent.VK_ENTER) {
                getGame().setScene(new GameScene(getGame()));
            }
        });
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, getGame().getScreenSize().width, getGame().getScreenSize().height);

        graphics.setFont(primaryFont);
        graphics.setColor(Color.white);
        String name = Strings.GAME_TITLE;

        graphics.drawString(
                name,
                getGame().getScreenSize().width / 2 - graphics.getFontMetrics().stringWidth(name) / 2,
                getGame().getScreenSize().height / 2 - 50);

        graphics.setFont(secondaryFont);
        graphics.setColor(Color.gray);
        String message = Strings.START_MESSAGE;

        graphics.drawString(
                message,
                getGame().getScreenSize().width / 2 - graphics.getFontMetrics().stringWidth(message) / 2,
                getGame().getScreenSize().height / 2 + 50);
    }
}
