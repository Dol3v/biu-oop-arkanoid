package utils;

import abstractshapes.Point;

/**
 * Stores the velocity of a moving object.
 */
public class Velocity {

    private final double dx;
    private final double dy;

    /**
     * Creates a new velocity object.
     *
     * @param dx change in x
     * @param dy change in y
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Constructs a velocity object with no change at all.
     */
    public Velocity() {
        this(0, 0);
    }

    /**
     * Creates a utils.Velocity object from the object's angle and speed.
     *
     * @param angle angle the object is moving at, in degrees
     * @param speed speed the object is moving at
     * @return corresponding utils.Velocity object
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.cos(angle * Math.PI / 180);
        double dy = speed * Math.sin(angle * Math.PI / 180);
        return new Velocity(dx, dy);
    }

    /**
     * Creates a {@code Velocity} object from randomly-selected angles and speeds.
     *
     * @param minAngle minimum angle that can be used
     * @param maxAngle maximum angle that can be used
     * @param minSpeed minimum speed that can be used
     * @param maxSpeed maximum speed that can be used
     * @return Velocity with angle in range {@code minAngle} to {@code maxAngle} (inclusive) and speed in range
     * {@code minSpeed} to {@code maxSpeed} (inclusive)
     */
    public static Velocity randomFromRanges(double minAngle, double maxAngle, double minSpeed, double maxSpeed) {
        return fromAngleAndSpeed(
                minAngle + (maxAngle - minAngle) * Math.random(),
                minSpeed + (maxSpeed - minSpeed) * Math.random()
        );
    }

    /**
     * Creates a {@code Velocity} object from randomly-selected angles and speeds.
     *
     * @param minAngle minimum angle that can be used
     * @param maxAngle maximum angle that can be used
     * @param speed speed of ball
     * @return Velocity with angle in range {@code minAngle} to {@code maxAngle} (inclusive) and speed {@code speed}
     */
    public static Velocity randomFromRanges(double minAngle, double maxAngle, double speed) {
        return randomFromRanges(minAngle, maxAngle, speed, speed);
    }

    /**
     * Returns a new utils.Velocity with the same speed, and the direction shifted clockwise by a set number of degrees.
     *
     * @param degrees degrees to shift
     * @return shifted velocity
     */
    public Velocity rotateBy(double degrees) {
        double size = Math.sqrt(dx * dx + dy * dy);
        double angle = Math.atan2(dy, dx);
        double newAngle = angle + (degrees * Math.PI / 180.);
        return new Velocity(size * Math.cos(newAngle), size * Math.sin(newAngle));
    }

    /**
     * Increments the points coordinates using the velocity's data.
     *
     * @param p point to move
     * @return new point with updated location
     */
    public Point applyToPoint(Point p) {
        return new Point((int) p.getX() + dx, (int) p.getY() + dy);
    }

    /**
     * Getter for change in x.
     *
     * @return change in x
     */
    public double getDx() {
        return dx;
    }

    /**
     * Getter for change in y.
     *
     * @return change in y
     */
    public double getDy() {
        return dy;
    }
}
