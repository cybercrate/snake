package com.wingmann.snake;

import java.awt.*;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

public class GameOverScene extends Scene {
    public GameOverScene(Game game) {
        super(game);
    }

    @Override
    public void update(long timePassed) {
        game.getInput().consumeEvents().forEach(event -> {
            if (event instanceof Input.Event.KeyPressed keyPressedEvent) {
                if (keyPressedEvent.data.getKeyCode() == KeyEvent.VK_ENTER) {
                    game.setScene(new GameScene(game));
                }
            }
        });
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, game.getScreenSize().width, game.getScreenSize().height);

        graphics.setFont(new Font(Strings.FONT, Font.BOLD, Constants.SECONDARY_FONT_SIZE));
        graphics.setColor(Color.white);

        String message = Strings.NEW_GAME_MESSAGE;
        Rectangle2D messageBounds = graphics.getFontMetrics().getStringBounds(message, graphics);
        int messageWidth = (int) messageBounds.getWidth();
        int messageHeight = (int) messageBounds.getHeight();

        graphics.drawString(
                message,
                game.getScreenSize().width / 2 - messageWidth / 2,
                game.getScreenSize().height / 2 - messageHeight / 2
        );
    }
}