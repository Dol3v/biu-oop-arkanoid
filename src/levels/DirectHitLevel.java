package levels;

import abstractshapes.Point;
import objects.Block;
import objects.Sprite;
import utils.Consts;
import utils.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * First game level.
 */
public class DirectHitLevel implements LevelInformation {

    private Sprite background;
    private List<Velocity> ballVelocities;
    private List<Block> blocks;
    private static final int PADDLE_SPEED = 5;

    private static final int BLOCK_HEIGHT = 20;
    private static final int BLOCK_WIDTH = 20;

    /**
     * Initializes the level.
     */
    public DirectHitLevel() {
        this.ballVelocities = new ArrayList<>();
        this.ballVelocities.add(Velocity.fromAngleAndSpeed(270, Consts.BALL_SPEED));

        this.blocks = new ArrayList<>();
        this.blocks.add(new Block((Consts.SCREEN_WIDTH - BLOCK_WIDTH) / 2.,
                (Consts.SCREEN_HEIGHT - BLOCK_HEIGHT) / 2.,
                BLOCK_HEIGHT, BLOCK_WIDTH, Color.BLACK));

        this.background = new DirectHitBackground();
    }

    /**
     * Number of balls in the level.
     *
     * @return number of balls
     */
    @Override
    public int numberOfBalls() {
        return ballVelocities.size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return ballVelocities;
    }

    @Override
    public List<Point> initialBallCenters() {
        List<Point> centers = new ArrayList<>();
        int paddleLeft = (Consts.SCREEN_WIDTH - paddleWidth()) / 2;
        int xIncrement = paddleWidth() / (numberOfBalls() + 1);

        for (int i = 0; i < numberOfBalls(); i++) {
            centers.add(new Point(paddleLeft + (i + 1) * xIncrement,
                    Consts.PADDLE_LOCATION_HEIGHT - Consts.BALL_RADIUS - Consts.BALL_MARGIN));
        }
        return centers;
    }

    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    @Override
    public int paddleWidth() {
        return 50;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return background;
    }

    @Override
    public List<Block> blocks() {
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks.size();
    }
}
