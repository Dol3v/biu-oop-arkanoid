package game;

import biuoop.DrawSurface;
import objects.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * A collection of game sprites.
 */
public class SpriteCollection {

    private List<Sprite> sprites;

    /**
     * Initializes the sprite collection.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * Adds a sprite to the collection.
     *
     * @param s sprite to add
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * Removes a sprite from the collection.
     *
     * @param s sprite to remove
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }

    /**
     * Calls the timePassed method on all sprites.
     */
    public void notifyAllTimePassed() {
        // timePassed() may cause collisions, which may remove sprites
        List<Sprite> spritesCopy = new ArrayList<>(sprites);

        for (Sprite sprite : spritesCopy) {
            sprite.timePassed();
        }
    }

    /**
     * Draws all the collection's sprites on a draw surface.
     *
     * @param drawSurface surface to draw the sprites on
     */
    public void drawAllOn(DrawSurface drawSurface) {
        for (Sprite sprite : sprites) {
            sprite.drawOn(drawSurface);
        }
    }
}
