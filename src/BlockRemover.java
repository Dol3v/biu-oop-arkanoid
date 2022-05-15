/**
 * Removes a block from the game on hit.
 */
public class BlockRemover implements HitListener {

    private Game game;
    private int remainingBlocks; //TODO: verify why in the world we need a Counter class for this

    public BlockRemover(Game game, int remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {

    }
}
