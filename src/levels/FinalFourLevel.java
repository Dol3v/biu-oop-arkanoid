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
 * Game's fourth level.
 */
public class FinalFourLevel implements LevelInformation {

    private List<Point> ballCenters;
    private List<Velocity> velocities;
    private List<Block> blocks;

    private static final double BALL_LOWER_Y = 400;
    private static final double BALL_HIGHER_Y = 370;
    private static final int NUMBER_OF_BALLS = 3;
    private static final Color[] BLOCK_COLORS = {Color.GRAY, Color.RED, Color.YELLOW, Color.GREEN, Color.WHITE,
            Color.PINK, Color.CYAN};
    private static final int BLOCKS_PER_ROW = 15;
    private static final int BLOCK_STARTING_Y = 100;
    private static final int BLOCK_HEIGHT = 30;
    private static final int PADDLE_SPEED = 9;
    private static final int PADDLE_WIDTH = 110;

    /**
     * Initializes the level.
     */
    public FinalFourLevel() {
        this.ballCenters = new ArrayList<>();
        this.velocities = new ArrayList<>();
        this.blocks = new ArrayList<>();

        ballCenters.add(new Point(0.5 * Consts.SCREEN_WIDTH, BALL_HIGHER_Y));
        ballCenters.add(new Point(0.5 * Consts.SCREEN_WIDTH - 30, BALL_LOWER_Y));
        ballCenters.add(new Point(0.5 * Consts.SCREEN_WIDTH + 30, BALL_LOWER_Y));

        for (int i = 0; i < NUMBER_OF_BALLS; i++) {
            velocities.add(Velocity.randomFromRanges(-90, 90, Consts.BALL_SPEED));
        }

        for (int row = 0; row < BLOCK_COLORS.length; row++) {
            for (int x = Consts.STARTING_X; x < Consts.ENDING_X; x += Consts.GAME_WIDTH / BLOCKS_PER_ROW) {
                blocks.add(new Block(x, BLOCK_STARTING_Y + row * BLOCK_HEIGHT, BLOCK_HEIGHT,
                        Consts.GAME_WIDTH / BLOCKS_PER_ROW, BLOCK_COLORS[row]));
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
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return new FinalFourBackground();
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
