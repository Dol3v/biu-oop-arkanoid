/**
 * An object that appears in the game.
 */
public interface GameObject {

    /**
     * Adds the object to the game.
     *
     * @param game game to add the object to
     */
    void addToGame(Game game);

    /**
     * Removes the object from the game.
     *
     * @param game game to remove the object from
     */
    void removeFromGame(Game game);
}
