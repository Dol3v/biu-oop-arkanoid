/**
 * An object that appears in the game.
 */
public interface GameObject {

    /**
     * Adds the object to the game.
     *
     * @param gameLevel game to add the object to
     */
    void addToGame(GameLevel gameLevel);

    /**
     * Removes the object from the game.
     *
     * @param gameLevel game to remove the object from
     */
    void removeFromGame(GameLevel gameLevel);
}
