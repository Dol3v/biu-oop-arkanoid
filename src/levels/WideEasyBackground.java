package levels;

import biuoop.DrawSurface;
import objects.Sprite;
import utils.Consts;

import java.awt.Color;

/**
 * Background for the wide easy level.
 *
 * @see WideEasyLevel
 */
public class WideEasyBackground implements Sprite {

    private final Color color;

    /**
     * Initializes the background.
     */
    public WideEasyBackground() {
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
    }

    /**
     * Calls an action which will be executed every frame.
     */
    @Override
    public void timePassed() {

    }
}
