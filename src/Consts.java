import abstractshapes.Point;

import java.awt.Color;

/**
 * Consts that are used throughout the game.
 */
public class Consts {

    public static final Color LINE_COLOR = Color.BLACK;
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final double ROUNDING_ERROR = 0.05;

    // graphics consts
    // boundary blocks
    public static final double BOUNDARY_BLOCK_SIZE = 20;

    // puddle
    public static final double PUDDLE_HEIGHT = 9;
    public static final double PUDDLE_WIDTH = 100;
    public static final abstractshapes.Point PUDDLE_INITIAL_TOP_LEFT = new Point(SCREEN_WIDTH / 2.,
            SCREEN_HEIGHT - BOUNDARY_BLOCK_SIZE - PUDDLE_HEIGHT);
    public static final Color PUDDLE_COLOR = Color.BLUE;
    public static final double PADDLE_SPEED = 10.;

    // ball
    public static final double BALL_SPEED = 11.;

    // block
    public static final double BLOCK_LENGTH = 50;
    public static final double BLOCK_STARTING_HEIGHT = 400;

    // score indicator
    public static final int SCORE_INDICATOR_HEIGHT = 20;
    public static final int TEXT_START_X = (int) BOUNDARY_BLOCK_SIZE;
    public static final int TEXT_START_Y = 12;
    public static final int FONT_SIZE = 14;
}
