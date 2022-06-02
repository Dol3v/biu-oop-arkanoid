package statsbar;

import biuoop.DrawSurface;
import objects.Sprite;
import utils.Consts;
import utils.Fetcher;

import java.awt.Color;

/**
 * An indicator of a game-related statistic to be drawn on the stats bar.
 *
 * @param <T> type of object indicated to the player
 */
public class Indicator<T> implements Sprite {

    private Fetcher<T> fetcher;
    private int startingTextXLocation;
    private String valueName;

    /**
     * Creates an indicator.
     *
     * @param fetcher {@code Fetcher} for indicated value
     * @param startingTextXLocation x location to start the text from
     * @param valueName name of the indicated value
     */
    public Indicator(Fetcher<T> fetcher, int startingTextXLocation, String valueName) {
        this.fetcher = fetcher;
        this.startingTextXLocation = startingTextXLocation;
        this.valueName = valueName;
    }


    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawText(startingTextXLocation, Consts.Y_TEXT_START,
                valueName + ": " + fetcher.getValue(),
                Consts.FONT_SIZE);
    }

    @Override
    public void timePassed() {

    }
}
