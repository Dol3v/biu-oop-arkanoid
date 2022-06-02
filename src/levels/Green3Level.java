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
 * Information for the third level.
 */
public class Green3Level implements LevelInformation {

    private List<Point> ballCenters;
    private List<Velocity> velocities;
    private List<Block> blocks;

    private static final int NUMBER_OF_BALLS = 2;
    private static final double BALL_Y = 400;
    private static final int PADDLE_SPEED = 8;
    private static final int PADDLE_WIDTH = 100;
    private static final int BLOCK_LENGTH = 40;
    private static final int BLOCK_STARTING_HEIGHT = 400;

    /**
     * Initializes the level.
     */
    public Green3Level() {
        this.ballCenters = new ArrayList<>();
        this.velocities = new ArrayList<>();
        this.blocks = new ArrayList<>();

        for (int i = 0; i < NUMBER_OF_BALLS; i++) {
            velocities.add(Velocity.randomFromRanges(-90, 90, Consts.BALL_SPEED));
        }

        ballCenters.add(new Point((Consts.SCREEN_WIDTH / 2.) - 30, BALL_Y));
        ballCenters.add(new Point((Consts.SCREEN_WIDTH / 2.) + 30, BALL_Y));

        Color[] blockColors = {Color.GREEN, Color.BLACK, Color.RED, Color.CYAN, Color.BLUE, Color.ORANGE};

        for (int row = 0; row < 6; row++) {
            for (int col = 1; col < 5 + row + 1; col++) {
                blocks.add(new Block(Consts.SCREEN_WIDTH - col * BLOCK_LENGTH - Consts.BOUNDARY_BLOCK_MARGIN_SIZE,
                        BLOCK_STARTING_HEIGHT - BLOCK_LENGTH * row, BLOCK_LENGTH,
                        BLOCK_LENGTH, blockColors[row]));
            }
        }
    }

    @Override
    public int numberOfBalls() {
        return NUMBER_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return velocities;
    }

    @Override
    public List<Point> initialBallCenters() {
        return ballCenters;
    }

    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Green3Background();
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
