package screens;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.Animation;

/**
 * Game end screen, stops on space press.
 */
public class EndScreen implements Animation {

    private int score;
    private boolean gameWon;
    private String message;

    private static final double FPS_RATE = 60;

    public EndScreen(int score, boolean gameWon) {
        this.score = score;
        this.gameWon = gameWon;
        if (gameWon) {
            message = "You Win! Your score is " + score;
        } else {
            message = "Game Over. Your score is " + score;
        }
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, message, 32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }

    @Override
    public double getFramesPerSecond() {
        return FPS_RATE;
    }
}
