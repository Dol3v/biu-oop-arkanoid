import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Draws the current player score.
 */
public class ScoreIndicator implements Sprite {

    private Counter scoreCounter;

    /**
     * Creates a sprite that'll draw the current player score.
     *
     * @param scoreCounter reference to the current score counter
     */
    public ScoreIndicator(Counter scoreCounter) {
        this.scoreCounter = scoreCounter;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.GRAY);
        surface.fillRectangle(0, 0, Consts.SCREEN_WIDTH, Consts.SCORE_INDICATOR_HEIGHT);
        surface.setColor(Color.BLACK);
        surface.drawText(Consts.TEXT_START_X, Consts.TEXT_START_Y, "Score: " + scoreCounter.getValue(),
                Consts.FONT_SIZE);
    }

    @Override
    public void timePassed() {

    }
}
