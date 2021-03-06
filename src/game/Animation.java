package game;

import biuoop.DrawSurface;

/**
 * An in-game animation.
 */
public interface Animation {
    /**
     * Executes one frame of the animation.
     *
     * @param d surface to draw the animation on
     */
    void doOneFrame(DrawSurface d);

    /**
     * Checks if the animation should stop.
     *
     * @return whether the animation should stop
     */
    boolean shouldStop();

    /**
     * Returns the FPS rate the animation should run at.
     *
     * @return the animation's FPS rate
     */
    double getFramesPerSecond();
}
