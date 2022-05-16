/**
 * Increases the score of a player on hits.
 */
public class ScoreTrackingListener implements HitListener {

    private Counter currentScore;

    private final static int SCORE_INCREASE_ON_HIT = 5;

    public ScoreTrackingListener(Counter currentScore) {
        this.currentScore = currentScore;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(SCORE_INCREASE_ON_HIT);
    }
}
