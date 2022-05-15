import abstractshapes.Line;
import abstractshapes.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages collisions for the game: stores collidables and allows to retrieve them.
 */
public class GameEnvironment {

    private List<Collidable> collidables;

    /**
     * Initializes the collidable list.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Adds a collidable to the environment.
     *
     * @param c collidable to add
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * Removes a collidable from the environment.
     *
     * @param c collidable to remove
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }

    /**
     * Finds the closest collidable to the start of the trajectory, that's on it. If there are no collidables on
     * the trajectory, returns null.
     *
     * @param trajectory line representing some point's trajectory
     * @return the info of the collision with the collidable on the trajectory that's closest to its start. If dosen't
     * exist, returns null.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closestPoint = null;
        Collidable closestCollidable = null;
        for (Collidable collidable : collidables) {
            Point collidableClosest = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            if (collidableClosest == null) {
                continue;
            }
            if (closestPoint == null || collidableClosest.distance(trajectory.start())
                    < closestPoint.distance(trajectory.start())) {
                closestPoint = collidableClosest;
                closestCollidable = collidable;
            }
        }
        if (closestPoint == null) {
            return null;
        }
        return new CollisionInfo(closestPoint, closestCollidable);
    }
}
