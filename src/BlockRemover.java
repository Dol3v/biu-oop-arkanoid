/**
 * Removes a block from the game on hit.
 */
public class BlockRemover implements HitListener {

    private final Game game;
    private Counter remainingBlocks;

    /**
     * Creates a hit listener that destroys blocks on impact.
     *
     * @param game current game session
     * @param initialBlocks number of initial blocks
     */
    public BlockRemover(Game game, int initialBlocks) {
        this.game = game;
        this.remainingBlocks = new Counter(initialBlocks);
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(game);
        remainingBlocks.decrease(1);
    }

    /**
     * Get a reference for the counter for the remaining blocks.
     *
     * @return remaining blocks counter
     */
    public Counter getRemainingBlocks() {
        return remainingBlocks;
    }
}
