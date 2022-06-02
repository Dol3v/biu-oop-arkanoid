package hitlisteners;

import game.GameLevel;
import objects.Ball;
import objects.Block;

/**
 * A hit listener that removes balls when hit.
 */
public class BallRemover implements HitListener {

    private GameLevel gameLevel;

    /**
     * Creates a ball remover.
     *
     * @param gameLevel game session
     */
    public BallRemover(GameLevel gameLevel) {
        this.gameLevel = gameLevel;
    }

    /**
     * Removes the ball when hit.
     *
     * @param beingHit the block that was hit
     * @param hitter   the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(gameLevel);
        gameLevel.getAvailableBalls().decrease(1);
        gameLevel.getLives().decrease(1);
    }
}
