/**
 * A hit listener that removes balls when hit.
 */
public class BallRemover implements HitListener {

    private Game game;

    /**
     * Creates a ball remover.
     *
     * @param game game session
     */
    public BallRemover(Game game) {
        this.game = game;
    }

    /**
     * Removes the ball when hit.
     *
     * @param beingHit the block that was hit
     * @param hitter   the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        game.getAvailableBalls().decrease(1);
    }
}
