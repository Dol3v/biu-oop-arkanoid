/**
 * Removes a block from the game on hit.
 */
public class BlockRemover implements HitListener {

    private final GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Creates a hit listener that destroys blocks on impact.
     *
     * @param gameLevel current game session
     * @param initialBlocks number of initial blocks
     */
    public BlockRemover(GameLevel gameLevel, int initialBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = new Counter(initialBlocks);
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(gameLevel);
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
