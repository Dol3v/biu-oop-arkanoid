package screens;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.Animation;

/**
 * An animation that can be stopped on a keypress.
 */
public class KeyPressStoppableAnimation implements Animation {

    private boolean shouldStop;
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;

    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.shouldStop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (sensor.isPressed(key)) {
            shouldStop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return shouldStop || animation.shouldStop();
    }

    @Override
    public double getFramesPerSecond() {
        return animation.getFramesPerSecond();
    }
}
