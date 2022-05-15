/**
 * An object that listens to hit events.
 */
public interface HitListener {

    /**
     * Act in the event of a block being hit.
     *
     * @param beingHit the block that was hit
     * @param hitter the ball that hit the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}
