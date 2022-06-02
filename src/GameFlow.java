import biuoop.KeyboardSensor;
import game.AnimationRunner;
import game.GameLevel;
import levels.LevelInformation;
import screens.EndScreen;
import screens.KeyPressStoppableAnimation;
import utils.Counter;

import java.util.List;

/**
 * Runs several game levels until the game ends.
 */
public class GameFlow {

    private KeyboardSensor keyboardSensor;
    private AnimationRunner runner;
    private Counter score;
    private Counter lives;

    public static final int MAX_LIVES = 10;

    /**
     * Creates the game flow object.
     *
     * @param keyboardSensor keyboard sensor
     * @param runner animation runner
     */
    public GameFlow(KeyboardSensor keyboardSensor, AnimationRunner runner) {
        this.keyboardSensor = keyboardSensor;
        this.runner = runner;
        this.score = new Counter(0);
        this.lives = new Counter(MAX_LIVES);
    }

    /**
     * Runs a list of levels, and displays the {@code EndScreen} animation when it has ended.
     *
     * @param levels list of levels to run successively
     */
    public void runLevels(List<LevelInformation> levels) {
        boolean gameWon = true;
        for (LevelInformation level : levels) {
            GameLevel gameLevel = new GameLevel(level, keyboardSensor, runner, score, lives);
            gameLevel.initialize();

            gameLevel.run();

            if (gameLevel.getAvailableBalls().getValue() == 0
                    || lives.getValue() == 0) {
                gameWon = false;
                break;
            }
        }
        runner.run(new KeyPressStoppableAnimation(keyboardSensor, KeyboardSensor.SPACE_KEY,
                new EndScreen(score.getValue(), gameWon)));
    }
}
