package hitlisteners;

import hitlisteners.HitListener;
import objects.Ball;
import objects.Block;
import utils.Counter;

/**
 * Increases the score of a player on hits.
 */
public class ScoreTrackingListener implements HitListener {

    private Counter currentScore;

    private static final int SCORE_INCREASE_ON_HIT = 5;

    /**
     * Creates a score-tracking listener.
     *
     * @param currentScore reference to the current score counter
     */
    public ScoreTrackingListener(Counter currentScore) {
        this.currentScore = currentScore;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(SCORE_INCREASE_ON_HIT);
    }

    /**
     * Gets a reference to the score counter.
     *
     * @return reference to the current score counter
     */
    public Counter getCurrentScore() {
        return currentScore;
    }
}
