package game;

import abstractshapes.Point;
import objects.Collidable;

/**
 * Stores info about a collision.
 */
public class CollisionInfo {

    private final Point collisionPoint;
    private final Collidable collidable;

    /**
     * Creates the collision info.
     *
     * @param collisionPoint point of collision
     * @param collidable the collidable that collided
     */
    public CollisionInfo(Point collisionPoint, Collidable collidable) {
        this.collisionPoint = collisionPoint;
        this.collidable = collidable;
    }

    /**
     * Getter for collisionPoint.
     *
     * @return point of collision
     */
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * Getter for collidable.
     *
     * @return the collidable involved in the collision
     */
    public Collidable collisionObject() {
        return collidable;
    }
}