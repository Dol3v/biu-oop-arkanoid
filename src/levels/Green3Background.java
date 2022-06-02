package levels;

import biuoop.DrawSurface;
import objects.Sprite;
import utils.Consts;

import java.awt.Color;
import java.awt.Polygon;

/**
 * Background for the third level.
 */
public class Green3Background implements Sprite {

    private final Color color;

    /**
     * Initializes the background.
     */
    public Green3Background() {
        this.color = new Color(0x266325);
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

        // draw tree
        surface.setColor(new Color(37, 72, 37));
        surface.fillPolygon(new Polygon(
                new int[]{50, 80, 50, 80, 50, 100, 150, 120, 150, 120, 150},
                new int[]{500, 470, 470, 440, 440, 390, 440, 440, 470, 470, 500},
                11
        ));
        surface.setColor(new Color(100, 75, 10));
        surface.fillRectangle(90, 500, 20, (int) (100 - Consts.BOUNDARY_BLOCK_MARGIN_SIZE));
    }

    /**
     * Calls an action which will be executed every frame.
     */
    @Override
    public void timePassed() {

    }
}