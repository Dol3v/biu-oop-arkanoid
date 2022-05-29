import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class PauseScreen implements Animation {

    private KeyboardSensor sensor;
    private boolean shouldStop;

    public PauseScreen(KeyboardSensor sensor) {
        this.sensor = sensor;
        this.shouldStop = false;
    }

    /**
     * Executes one frame of the animation.
     *
     * @param d surface to draw the animation on
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        if (sensor.isPressed(KeyboardSensor.SPACE_KEY)) { shouldStop = true; }
    }

    /**
     * Checks if the animation should stop.
     *
     * @return whether the animation should stop
     */
    @Override
    public boolean shouldStop() {
        return shouldStop;
    }
}
