package statsbar;

import biuoop.DrawSurface;
import objects.Sprite;
import utils.Consts;
import utils.Fetcher;

import java.awt.*;

/**
 * An indicator of a game-related statistic to be drawn on the stats bar.
 */
public class Indicator<T> implements Sprite {

    private Fetcher<T> fetcher;
    private int startingTextXLocation;
    private String valueName;

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
