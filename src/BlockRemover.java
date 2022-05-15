/**
 * Removes a block from the game on hit.
 */
public class BlockRemover implements HitListener {

    private final Game game;
    private int remainingBlocks; //TODO: verify why in the world we need a Counter class for this

    public BlockRemover(Game game, int remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(game);
        remainingBlocks--;
    }

    /**
     * Gets the number of remaining blocks in the game.
     *
     * @return number of remaining blocks
     */
    public int getRemainingBlocks() {
        return remainingBlocks;
    }

    /**
     * Sets the number of remaining blocks in the game.
     *
     * @param remainingBlocks new number of remaining blocks
     */
    public void setRemainingBlocks(int remainingBlocks) {
        this.remainingBlocks = remainingBlocks;
    }
}
