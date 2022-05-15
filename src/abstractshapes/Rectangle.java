package abstractshapes;

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A rectangle shape.
 */
public class Rectangle {

    private Point upperLeft;
    private final double height;
    private final double width;

    /**
     * Creates a rectangle.
     *
     * @param upperLeft upper left point
     * @param height    height
     * @param width     width
     */
    public Rectangle(Point upperLeft, double height, double width) {
        this.upperLeft = upperLeft;
        this.height = height;
        this.width = width;
    }

    /**
     * Returns the (possibly empty) intersection points of a line with the rectangle.
     *
     * @param line line to check collision with
     * @return list of intersections of the line with the rectangle
     */
    public List<Point> intersectionPoints(Line line) {
        Line upperLine = new Line(upperLeft, getUpperRight());
        Line lowerLine = new Line(getLowerLeft(), getLowerRight());
        Line leftLine = new Line(upperLeft, getLowerLeft());
        Line rightLine = new Line(getUpperRight(), getLowerRight());

        List<Point> intersections = new ArrayList<>();
        intersections.add(upperLine.intersectionWith(line));
        intersections.add(lowerLine.intersectionWith(line));
        intersections.add(leftLine.intersectionWith(line));
        intersections.add(rightLine.intersectionWith(line));

        return intersections.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * Draws the rectangle on some draw surface.
     *
     * @param surface drawing surface
     * @param color   rectangle color
     */
    public void drawOn(DrawSurface surface, Color color) {
        surface.setColor(color);
        surface.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) width, (int) height);
    }

    /**
     * Getter for upper left point.
     *
     * @return upper left point
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * Returns the upper right point.
     *
     * @return upper right point
     */
    public Point getUpperRight() {
        return new Point(upperLeft.getX() + width, upperLeft.getY());
    }

    /**
     * Returns the lower left point.
     *
     * @return lower left point
     */
    public Point getLowerLeft() {
        return new Point(upperLeft.getX(), upperLeft.getY() + height);
    }

    /**
     * Returns the lower right point.
     *
     * @return lower right point
     */
    public Point getLowerRight() {
        return new Point(upperLeft.getX() + width, upperLeft.getY() + height);
    }

    /**
     * Getter for width.
     *
     * @return rectangle width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Setter for upper left point.
     *
     * @param upperLeft new point
     */
    public void setUpperLeft(Point upperLeft) {
        this.upperLeft = upperLeft;
    }
}
