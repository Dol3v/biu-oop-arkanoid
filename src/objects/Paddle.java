package objects;

import abstractshapes.Point;
import abstractshapes.Rectangle;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.GameLevel;
import game.GameObject;
import utils.Consts;
import utils.Velocity;

/**
 * User controlled, moving collidable paddle.
 */
public class Paddle implements Sprite, Collidable, GameObject {

    private KeyboardSensor sensor;
    private Rectangle paddleShape;
    private int paddleSpeed;

    /**
     * Creates a paddle.
     *
     * @param sensor keyboard sensor
     * @param paddleShape shape of paddle
     * @param paddleSpeed speed of paddle
     */
    public Paddle(KeyboardSensor sensor, Rectangle paddleShape, int paddleSpeed) {
        this.sensor = sensor;
        this.paddleShape = paddleShape;
        this.paddleSpeed = paddleSpeed;
    }

    /**
     * Moves the paddle's position to the left.
     */
    public void moveLeft() {
        Point newUpperLeft = paddleShape.getUpperLeft().addPoint(-paddleSpeed, 0);
        // making sure the paddle can't go further than the screen boundaries
        if (newUpperLeft.getX() < Consts.BOUNDARY_BLOCK_MARGIN_SIZE) {
            return;
        }
        paddleShape.setUpperLeft(newUpperLeft);
    }

    /**
     * Moves the paddle's position to the right.
     */
    public void moveRight() {
        Point newUpperLeft = paddleShape.getUpperLeft().addPoint(paddleSpeed, 0);
        // making sure the paddle can't go further than the screen boundaries
        if (newUpperLeft.getX() + paddleShape.getWidth() >= Consts.SCREEN_WIDTH - Consts.BOUNDARY_BLOCK_MARGIN_SIZE) {
            return;
        }
        paddleShape.setUpperLeft(newUpperLeft);
    }

    /**
     * Returns the region of a point on the paddle: an integer from 1-5 specifying how left it is.
     *
     * @param x x coordinate of point
     * @return region
     */
    public int regionOf(double x) {
        if (x == paddleShape.getUpperLeft().getX()) {
            return 1;
        }
        if (x == paddleShape.getUpperLeft().getX() + paddleShape.getWidth()) {
            return 5;
        }
        return (int) ((x - paddleShape.getUpperLeft().getX()) / (paddleShape.getWidth() / 5)) + 1;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return paddleShape;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // if the ball hits the paddle's side, reflect it backwards
        if (collisionPoint.getY() <= paddleShape.getUpperLeft().getY()
            || collisionPoint.getX() < paddleShape.getUpperLeft().getX()
            || collisionPoint.getX() > paddleShape.getUpperRight().getX()) {
            return new Velocity(-1 * currentVelocity.getDx(), -1 *  currentVelocity.getDy());
        }
        int region = regionOf(collisionPoint.getX());
        if (region == 3) {
            return new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
        }
        double shift = 180;
        switch (region) {
            case 1:
                shift = -60;
                break;
            case 2:
                shift = -30;
                break;
            case 4:
                shift = 30;
                break;
            case 5:
                shift = 60;
                break;
            default:
                break;
        }
        return currentVelocity.rotateBy(-shift);
    }

    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }

    /**
     * Removes the object from the game.
     *
     * @param gameLevel game to remove the object from
     */
    @Override
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    @Override
    public void drawOn(DrawSurface surface) {
        paddleShape.drawOn(surface, Consts.PADDLE_COLOR);
    }

    @Override
    public void timePassed() {
        if (sensor.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (sensor.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }
}
