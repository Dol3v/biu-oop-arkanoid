package levels;

import abstractshapes.Point;
import objects.Block;
import objects.Sprite;
import utils.Velocity;

import java.util.List;

/**
 * The information needed to construct a level.
 */
public interface LevelInformation {

    /**
     * Number of balls in the level.
     *
     * @return number of balls
     */
    int numberOfBalls();

    /**
     * Initial velocities of the balls.
     *
     * @return list of the balls initial velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * Initial centers of the balls.
     *
     * @return list of the balls initial centers
     */
    List<Point> initialBallCenters();

    /**
     * Speed of paddle.
     *
     * @return speed of paddle
     */
    int paddleSpeed();

    /**
     * Width of paddle.
     *
     * @return width of paddle
     */
    int paddleWidth();

    /**
     * Name of the level.
     *
     * @return level name
     */
    String levelName();

    /**
     * Background sprite.
     *
     * @return the level's background sprite
     */
    Sprite getBackground();

    /**
     * List of the level's blocks.
     *
     * @return level's blocks
     */
    List<Block> blocks();

    /**
     * The number of blocks in the level.
     *
     * @return the number of blocks in the level
     */
    int numberOfBlocksToRemove();
}