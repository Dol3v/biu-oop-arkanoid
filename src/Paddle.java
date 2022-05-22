import abstractshapes.Point;
import abstractshapes.Rectangle;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * User controlled, moving collidable paddle.
 */
public class Paddle implements Sprite, Collidable, GameObject {

    private KeyboardSensor sensor;
    private Rectangle paddleShape;

    /**
     * Creates a paddle.
     *
     * @param sensor keyboard sensor
     */
    public Paddle(KeyboardSensor sensor) {
        this.sensor = sensor;
        this.paddleShape = new Rectangle(Consts.PUDDLE_INITIAL_TOP_LEFT, Consts.PUDDLE_HEIGHT, Consts.PUDDLE_WIDTH);
    }

    /**
     * Moves the paddle's position to the left.
     */
    public void moveLeft() {
        paddleShape.setUpperLeft(paddleShape.getUpperLeft().addPoint(-Consts.PADDLE_SPEED, 0));
    }

    /**
     * Moves the paddle's position to the right.
     */
    public void moveRight() {
        paddleShape.setUpperLeft(paddleShape.getUpperLeft().addPoint(Consts.PADDLE_SPEED, 0));
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
        if (collisionPoint.getY() <= paddleShape.getUpperLeft().getY() ||
            collisionPoint.getX() < paddleShape.getUpperLeft().getX() ||
            collisionPoint.getX() > paddleShape.getUpperRight().getX()) {
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
        return currentVelocity.rotateBy(shift);
    }

    @Override
    public void addToGame(Game game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    /**
     * Removes the object from the game.
     *
     * @param game game to remove the object from
     */
    @Override
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    public void drawOn(DrawSurface surface) {
        paddleShape.drawOn(surface, Consts.PUDDLE_COLOR);
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
