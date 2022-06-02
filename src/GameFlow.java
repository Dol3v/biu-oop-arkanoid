import biuoop.KeyboardSensor;
import game.AnimationRunner;
import game.GameLevel;
import levels.LevelInformation;
import screens.EndScreen;
import utils.Counter;

import java.util.List;

/**
 * Runs several game levels until the game ends.
 */
public class GameFlow {

    private KeyboardSensor keyboardSensor;
    private AnimationRunner runner;
    private Counter score;

    public GameFlow(KeyboardSensor keyboardSensor, AnimationRunner runner) {
        this.keyboardSensor = keyboardSensor;
        this.runner = runner;
        this.score = new Counter(0);
    }

    public void runLevels(List<LevelInformation> levels) {
        boolean gameWon = true;
        for (LevelInformation level : levels) {
            GameLevel gameLevel = new GameLevel(level, keyboardSensor, runner, score);
            gameLevel.initialize();

            gameLevel.run();

            if (gameLevel.getAvailableBalls().getValue() == 0) {
                gameWon = false;
                break;
            }
        }
        runner.run(new EndScreen(keyboardSensor, score.getValue(), gameWon));
    }
}
