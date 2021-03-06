package objects;

import abstractshapes.Line;
import abstractshapes.Point;
import biuoop.DrawSurface;
import game.CollisionInfo;
import game.GameEnvironment;
import game.GameLevel;
import game.GameObject;
import utils.Velocity;

import java.awt.Color;

/**
 * A circle with some radius, that can move in a given frame.
 */
public class Ball implements Sprite, GameObject {

    private abstractshapes.Point center;
    private final int r;
    private final Color color;
    private Velocity velocity;
    private GameEnvironment environment;

    /**
     * Creates a ball.
     *
     * @param center ball's center
     * @param r      ball's radius
     * @param color  ball's color
     * @param environment game.GameEnvironment the ball is in
     */
    public Ball(abstractshapes.Point center, int r, Color color, GameEnvironment environment) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.velocity = new Velocity();
        this.environment = environment;
    }

    /**
     * Creates a ball.
     *
     * @param x     center's x coordinate
     * @param y     center's y coordinate
     * @param r     radius
     * @param color color
     * @param environment game.GameEnvironment the ball is in
     */
    public Ball(double x, double y, int r, Color color, GameEnvironment environment) {
        this(new Point((int) x, (int) y), r, color, environment);
    }

    /**
     * Increments the ball location based on its velocity.
     */
    public void moveOneStep() {
        Line trajectory = new Line(center, velocity.applyToPoint(center));
        CollisionInfo collisionInfo = environment.getClosestCollision(trajectory);
        if (collisionInfo == null) {
            center = velocity.applyToPoint(center);
            return;
        }
        center = trajectory.nudgeBackwards(collisionInfo.collisionPoint());
        velocity = collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), velocity);
    }

    /**
     * Draws the ball on a `DrawSurface`.
     *
     * @param surface Surface to draw the ball on.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawCircle(getX(), getY(), r);
        surface.setColor(this.color);
        surface.fillCircle(getX(), getY(), r);
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Getter for ball's x coordinate.
     *
     * @return center's x coordinate
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * Getter for ball's y coordinate.
     *
     * @return center's y coordinate
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * Getter for radius.
     *
     * @return ball's radius.
     */
    public int getSize() {
        return r;
    }

    /**
     * Setter for velocity.
     *
     * @param velocity objects.Ball's new velocity.
     */
    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    /**
     * Setter for velocity.
     *
     * @param dx utils.Velocity's dx
     * @param dy utils.Velocity's dy
     */
    public void setVelocity(double dx, double dy) {
        setVelocity(new Velocity(dx, dy));
    }

    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    /**
     * Removes the object from the game.
     *
     * @param gameLevel game to remove the object from
     */
    @Override
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}
