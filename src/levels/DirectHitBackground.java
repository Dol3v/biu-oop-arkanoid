package levels;

import biuoop.DrawSurface;
import objects.Sprite;
import utils.Consts;

import java.awt.Color;

/**
 * Background for the direct hit level.
 *
 * @see DirectHitLevel
 */
public class DirectHitBackground implements Sprite {

    private final Color color;

    /**
     * Initializes the background.
     */
    public DirectHitBackground() {
        this.color = Color.WHITE;
    }

    /**
     * Draws the sprite on some draw surface.
     *
     * @param surface surface to draw the sprite on
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillRectangle(Consts.STARTING_X, Consts.STARTING_Y, Consts.ENDING_X, Consts.ENDING_Y);

        surface.setColor(Color.RED);
        surface.drawCircle(Consts.SCREEN_WIDTH / 2, Consts.SCREEN_HEIGHT / 2, 120);
        surface.drawCircle(Consts.SCREEN_WIDTH / 2, Consts.SCREEN_HEIGHT / 2, 90);
        surface.drawCircle(Consts.SCREEN_WIDTH / 2, Consts.SCREEN_HEIGHT / 2, 60);

        surface.drawLine(Consts.SCREEN_WIDTH / 2 + 150, Consts.SCREEN_HEIGHT / 2,
                Consts.SCREEN_WIDTH / 2 - 150, Consts.SCREEN_HEIGHT / 2);
        surface.drawLine(Consts.SCREEN_WIDTH / 2, Consts.SCREEN_HEIGHT / 2 + 150,
                Consts.SCREEN_WIDTH / 2, Consts.SCREEN_HEIGHT / 2 - 150);
    }

    /**
     * Calls an action which will be executed every frame.
     */
    @Override
    public void timePassed() {

    }
}
