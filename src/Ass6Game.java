// Dolev Dabush, id: 215372939

import biuoop.GUI;
import game.Animation;
import game.AnimationRunner;
import game.GameLevel;
import levels.DirectHitLevel;
import levels.Green3Level;
import levels.LevelInformation;
import levels.WideEasyLevel;
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
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI("arkanoid", Consts.SCREEN_WIDTH, Consts.SCREEN_HEIGHT);
        AnimationRunner runner = new AnimationRunner(gui);
        GameFlow flow = new GameFlow(gui.getKeyboardSensor(), runner);

        List<LevelInformation> levels = new ArrayList<>();
//        levels.add(new DirectHitLevel());
//        levels.add(new WideEasyLevel());
        levels.add(new Green3Level());
        flow.runLevels(levels);

        gui.close();
    }
}
