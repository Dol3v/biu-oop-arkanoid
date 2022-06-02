package screens;

import biuoop.DrawSurface;
import game.Animation;

/**
 * Game's pause screen.
 */
public class PauseScreen implements Animation {

    /**
     * Executes one frame of the animation.
     *
     * @param d surface to draw the animation on
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /**
     * Checks if the animation should stop.
     *
     * @return whether the animation should stop
     */
    @Override
    public boolean shouldStop() {
        return false;
    }

    /**
     * Returns the FPS rate the animation should run at.
     *
     * @return the animation's FPS rate
     */
    @Override
    public double getFramesPerSecond() {
        return 60;
    }
}
