package statsbar;

import biuoop.DrawSurface;
import objects.Sprite;
import utils.Consts;

import java.awt.Color;

/**
 * The bar that specifies the player's current stats (level name, score, lives, etc).
 */
public class StatsBar implements Sprite {

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.LIGHT_GRAY);
        surface.fillRectangle(0, 0, Consts.SCREEN_WIDTH, Consts.STATS_BAR_HEIGHT);
    }

    @Override
    public void timePassed() {

    }
}
