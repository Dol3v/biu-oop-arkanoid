package abstractshapes;

/**
 * Line segment.
 */
public class Line {

    private final double x1;
    private final double y1;
    private final double x2;
    private final double y2;

    private static final double NUDGE_OFFSET = 1.7;

    /**
     * Creates a line segment.
     *
     * @param x1 first point's x coordinate
     * @param y1 first point's y coordinate
     * @param x2 second point's x coordinate
     * @param y2 second point's y coordinate
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    /**
     * Creates a line.
     *
     * @param start starting point
     * @param end   end point
     */
    public Line(Point start, Point end) {
        this(start.getX(), start.getY(), end.getX(), end.getY());
    }

    /**
     * Gets the line's starting point.
     *
     * @return starting point
     */
    public Point start() {
        return new Point(x1, y1);
    }

    /**
     * Gets the line's end point.
     *
     * @return end point
     */
    public Point end() {
        return new Point(x2, y2);
    }

    /**
     * Gets the line segment's middle point.
     *
     * @return middle point
     */
    public Point middle() {
        return new Point((x1 + x2) / 2, (y1 + y2) / 2);
    }

    /**
     * Returns the line orientation.
     *
     * @return whether the first point is the closer point to the origin
     */
    private boolean getOrientation() {
        return Math.abs(start().getX()) >= Math.abs(end().getX());
    }

    /**
     * Receives a point that was moving in the direction of the line (i.e from start to end), and "nudges" it
     * backwards.
     *
     * @param onLine a point on the line to nudge
     * @return nudged point
     */
    public Point nudgeBackwards(Point onLine) {
        double slope = getSlope();
        double yOffset = slope * NUDGE_OFFSET;
        double orientationFactor = (getOrientation() ? 1 : -1);
        return onLine.addPoint(orientationFactor * NUDGE_OFFSET, orientationFactor * yOffset);
    }

    /**
     * Calculates the line's slope.
     *
     * @return line's slope, and `Double.POSITIVE_INFINITY` if the line is parallel to the x-axis
     */
    public double getSlope() {
        return x2 == x1 ? Double.POSITIVE_INFINITY : (y2 - y1) / (x2 - x1);
    }

    /**
     * Calculates the line intersection point with the y-axis, ignoring any range restrictions imposed by the starting/
     * ending points.
     *
     * @return y-axis intersection
     */
    public double getYAxisIntersection() {
        double slope = getSlope();
        if (slope == Double.POSITIVE_INFINITY) {
            return Double.POSITIVE_INFINITY;
        }
        return y1 - slope * x1;
    }

    /**
     * Check's if a point's coordinates are within the line's x-y range.
     *
     * @param p point to check
     * @return if the point's coordinates are in the segment's range
     */
    public boolean inLineRange(Point p) {
        double maxX = Math.max(x1, x2);
        double maxY = Math.max(y1, y2);
        double minX = Math.min(x1, x2);
        double minY = Math.min(y1, y2);
        return p.getX() <= maxX && p.getX() >= minX && p.getY() <= maxY && p.getY() >= minY;
    }

    /**
     * Substitutes an x value in the line equation the segments lies on.
     *
     * @param x x value
     * @return slope * x + y-intersect
     */
    public double substitute(double x) {
        return x * getSlope() + getYAxisIntersection();
    }

    /**
     * Checks if two lines intersect with each other.
     *
     * @param other line to check intersection with
     * @return whether the two lines intersect with each other
     */
    public boolean isIntersecting(Line other) {
        return intersectionWith(other) != null;
    }

    /**
     * Returns the intersection point between this line and another line, and null if it doesn't exist.
     *
     * @param other line to get intersection point with
     * @return the intersection point if it exists, else null
     */
    public Point intersectionWith(Line other) {
        double slope = getSlope();
        double otherSlope = other.getSlope();
        // if the lines are parallel, there's no intersection point
        if (slope == otherSlope) {
            return null;
        }
        double intersectionX, intersectionY;
        if (slope == Double.POSITIVE_INFINITY || otherSlope == Double.POSITIVE_INFINITY) {
            // if either of the lines is parallel to the y-axis
            if (slope == Double.POSITIVE_INFINITY) {
                intersectionX = x1;
                intersectionY = other.substitute(intersectionX);
            } else {
                intersectionX = other.x1;
                intersectionY = substitute(intersectionX);
            }
        } else {
            intersectionX = (other.getYAxisIntersection() - getYAxisIntersection()) / (slope - otherSlope);
            intersectionY = substitute(intersectionX);
        }

        // check if the point is in range of both line segments
        Point possibleIntersection = new Point(intersectionX, intersectionY);
        return inLineRange(possibleIntersection) && other.inLineRange(possibleIntersection)
                ? possibleIntersection : null;
    }

    /**
     * Returns the closest intersection of the rectangle with line to the start, or null if it doesn't exist.
     *
     * @param rect rectangle to check
     * @return intersection point, or null
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        return rect.intersectionPoints(this).stream().min((point1, point2) -> (int) (point1.distance(start())
                - point2.distance(start()))).orElse(null);
    }


    /**
     * Checks if two line segments are equal to each other.
     *
     * @param other other line to check
     * @return if the line's starting and ending points are the same
     */
    public boolean equals(Line other) {
        return x1 == other.x1 && x2 == other.x2 && other.y1 == y1 && other.y2 == y2;
    }
}
