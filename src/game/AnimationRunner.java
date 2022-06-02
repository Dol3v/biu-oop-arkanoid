package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * Runs animations.
 */
public class AnimationRunner {

    private GUI gui;
    private Sleeper sleeper;

    /**
     * Creates an animation runner.
     *
     * @param gui gui object
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.sleeper = new Sleeper();
    }

    /**
     * Runs an animation.
     *
     * @param animation animation to run
     */
    public void run(Animation animation) {
        int milliSecondsPerFrame = (int) (1000 / animation.getFramesPerSecond());
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis();
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondsToSleep = milliSecondsPerFrame - usedTime;
            if (milliSecondsToSleep > 0) {
                sleeper.sleepFor(milliSecondsToSleep);
            }
        }
    }
}
