// Dolev Dabush, id: 215372939

import biuoop.GUI;
import game.Animation;
import game.AnimationRunner;
import game.GameLevel;
import levels.*;
import utils.Consts;

import java.util.ArrayList;
import java.util.List;

/**
 * Assignment: initializes and runs the game.
 */
public class Ass6Game {

    /**
     * Initializes and runs the game.
     *
     * @param args command-line arguments; each specifies the number of a level to be run
     */
    public static void main(String[] args) {
        GUI gui = new GUI("arkanoid", Consts.SCREEN_WIDTH, Consts.SCREEN_HEIGHT);
        AnimationRunner runner = new AnimationRunner(gui);
        GameFlow flow = new GameFlow(gui.getKeyboardSensor(), runner);

        LevelInformation[] levelList = {new DirectHitLevel(), new WideEasyLevel(), new Green3Level(),
                new FinalFourLevel()};

        List<LevelInformation> levels = new ArrayList<>();

        for (String arg : args) {
            try {
                int level = Integer.parseInt(arg);
                levels.add(levelList[level - 1]);
            } catch (NumberFormatException | IndexOutOfBoundsException ignored) {
            }
        }

        flow.runLevels(levels);
        gui.close();
    }
}
