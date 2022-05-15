public class TestListener implements HitListener {

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("Block " + beingHit + " was hit by " + hitter);
    }
}
