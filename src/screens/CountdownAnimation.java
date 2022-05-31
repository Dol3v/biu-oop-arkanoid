package screens;

import biuoop.DrawSurface;
import game.Animation;
import game.SpriteCollection;

/**
 * A countdown animation.
 */
public class CountdownAnimation implements Animation {

    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;

    private int currentNumber;

    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.currentNumber = countFrom;
    }

    /**
     * Executes one frame of the animation.
     *
     * @param d surface to draw the animation on
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        gameScreen.drawAllOn(d);
        d.drawText(d.getWidth() / 3, d.getHeight() / 2, currentNumber + "...", 32);
        currentNumber--;
    }

    /**
     * Checks if the animation should stop.
     *
     * @return whether the animation should stop
     */
    @Override
    public boolean shouldStop() {
        return currentNumber <= 0;
    }

    /**
     * Returns the FPS rate the animation should run at.
     *
     * @return the animation's FPS rate
     */
    @Override
    public double getFramesPerSecond() {
        return numOfSeconds / countFrom;
    }
}
