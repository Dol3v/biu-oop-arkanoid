package screens;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.Animation;

/**
 * Game end screen, stops on space press.
 */
public class EndScreen implements Animation {

    private boolean shouldStop;
    private KeyboardSensor sensor;
    private int score;
    private boolean gameWon;
    private String message;

    private static final double FPS_RATE = 60;

    public EndScreen(KeyboardSensor sensor, int score, boolean gameWon) {
        this.sensor = sensor;
        this.score = score;
        this.gameWon = gameWon;
        this.shouldStop = false;
        if (gameWon) {
            message = "You Win! Your score is " + score;
        } else {
            message = "Game Over. Your score is " + score;
        }
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, message, 32);
        if (sensor.isPressed(KeyboardSensor.SPACE_KEY)) { shouldStop = true; }
    }

    @Override
    public boolean shouldStop() {
        return shouldStop;
    }

    @Override
    public double getFramesPerSecond() {
        return FPS_RATE;
    }
}
