package abstractshapes;

/**
 * A point in 2-D space.
 */
public class Point{

    private final double x;
    private final double y;

    /**
     * Creates a point.
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the distance from this point to another.
     *
     * @param other point to calculate distance to
     * @return distance between the two points
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
    }

    /**
     * Creates a new point with the point-wise-added coordinates of the two points.
     *
     * @param x x coordinate to add
     * @param y coordinate to add
     * @return new point with x and y coordinates being the sum of this and other's coordinates
     */
    public Point addPoint(double x, double y) {
        return new Point(this.x + x, this.y + y);
    }

    /**
     * Scales the point's coordinates by some factor.
     *
     * @param factor to multiply the point's coordinate by
     * @return new point with modified coordinates
     */
    public Point scalePoint(double factor) {
        return new Point(x * factor, y * factor);
    }

    /**
     * Checks if two points are equal.
     *
     * @param other other point to check
     * @return if the point's coordiantes are the same.
     */
    public boolean equals(Point other) {
        return (x == other.x) && (y == other.y);
    }

    /**
     * Getter for x coordinate.
     *
     * @return x coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * Getter for y coordinate.
     *
     * @return y coordinate
     */
    public double getY() {
        return y;
    }
}
