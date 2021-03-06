package utils;

import java.awt.Color;

/**
 * utils.Consts that are used throughout the game.
 */
public class Consts {

    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final double ROUNDING_ERROR = 0.05;

    // boundary blocks
    public static final double BOUNDARY_BLOCK_MARGIN_SIZE = 20;

    // paddle
    public static final double PADDLE_HEIGHT = 9;
    public static final Color PADDLE_COLOR = Color.BLUE;
    public static final double PADDLE_LOCATION_HEIGHT = SCREEN_HEIGHT - BOUNDARY_BLOCK_MARGIN_SIZE - PADDLE_HEIGHT;


    // ball
    public static final double BALL_SPEED = 7;
    public static final int BALL_MARGIN = 2;
    public static final int BALL_RADIUS = 10;

    // stats bar
    public static final int STATS_BAR_HEIGHT = 20;
    public static final int FONT_SIZE = 14;
    public static final int Y_TEXT_START = 16;

    // locations
    public static final int STARTING_X = (int) BOUNDARY_BLOCK_MARGIN_SIZE;
    public static final int STARTING_Y = (int) (BOUNDARY_BLOCK_MARGIN_SIZE + STATS_BAR_HEIGHT);
    public static final int ENDING_X = (int) (SCREEN_WIDTH - BOUNDARY_BLOCK_MARGIN_SIZE);
    public static final int ENDING_Y = (int) (SCREEN_HEIGHT - BOUNDARY_BLOCK_MARGIN_SIZE);

    // dimensions
    public static final double GAME_WIDTH = SCREEN_WIDTH - 2 * BOUNDARY_BLOCK_MARGIN_SIZE;
    public static final double GAME_HEIGHT = SCREEN_HEIGHT - 2 * BOUNDARY_BLOCK_MARGIN_SIZE - STATS_BAR_HEIGHT;
}
