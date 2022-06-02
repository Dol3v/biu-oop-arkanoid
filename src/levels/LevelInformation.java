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
    // The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()
    List<Velocity> initialBallVelocities();

    List<Point> initialBallCenters();

    int paddleSpeed();
    int paddleWidth();
    // the level name will be displayed at the top of the screen.
    String levelName();
    // Returns a sprite with the background of the level
    Sprite getBackground();
    // The Blocks that make up this level, each block contains
    // its size, color and location.
    List<Block> blocks();
    // Number of blocks that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();
    int numberOfBlocksToRemove();
}