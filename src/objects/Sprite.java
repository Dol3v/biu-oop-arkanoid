package objects;

import biuoop.DrawSurface;

/**
 * A sprite on screen.
 */
public interface Sprite {

    /**
     * Draws the sprite on some draw surface.
     *
     * @param surface surface to draw the sprite on
     */
    void drawOn(DrawSurface surface);

    /**
     * Calls an action which will be executed every frame.
     */
    void timePassed();
}
