import abstractshapes.Point;
import abstractshapes.Rectangle;

/**
 * An object the ball can collide with.
 */
public interface Collidable {

    /**
     * The shape of the collidable.
     *
     * @return rectangle representing the collidable's dimensions
     */
    Rectangle getCollisionRectangle();

    /**
     * Calculates the ball's new velocity after hitting this collidable.
     *
     * @param collisionPoint point of collision
     * @param currentVelocity ball's velocity
     * @return ball's updated velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
