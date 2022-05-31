// Dolev Dabush, id: 215372939

import game.GameLevel;

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
        GameLevel gameLevel = new GameLevel();
        gameLevel.initialize();
        gameLevel.run();
    }
}
