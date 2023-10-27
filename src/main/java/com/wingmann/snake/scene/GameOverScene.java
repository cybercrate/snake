package com.wingmann.snake.scene;

import com.wingmann.snake.data.Constants;
import com.wingmann.snake.game.Game;
import com.wingmann.snake.util.Input;
import com.wingmann.snake.data.Strings;

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
        getGame().getInput().consumeEvents().forEach(event -> {
            if (event instanceof Input.Event.KeyPressed keyPressedEvent) {
                if (keyPressedEvent.data.getKeyCode() == KeyEvent.VK_ENTER) {
                    getGame().setScene(new GameScene(getGame()));
                }
            }
        });
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, getGame().getScreenSize().width, getGame().getScreenSize().height);

        graphics.setFont(new Font(Strings.FONT, Font.BOLD, Constants.SECONDARY_FONT_SIZE));
        graphics.setColor(Color.white);

        String message = Strings.NEW_GAME_MESSAGE;
        Rectangle2D messageBounds = graphics.getFontMetrics().getStringBounds(message, graphics);
        int messageWidth = (int) messageBounds.getWidth();
        int messageHeight = (int) messageBounds.getHeight();

        graphics.drawString(
                message,
                getGame().getScreenSize().width / 2 - messageWidth / 2,
                getGame().getScreenSize().height / 2 - messageHeight / 2
        );
    }
}
