package utils;

import abstractshapes.Point;

import java.awt.Color;

/**
 * utils.Consts that are used throughout the game.
 */
public class Consts {

    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final double ROUNDING_ERROR = 0.05;

    // graphics consts
    // boundary blocks
    public static final double BOUNDARY_BLOCK_SIZE = 20;

    // puddle
    public static final double PADDLE_HEIGHT = 9;
    public static final Color PUDDLE_COLOR = Color.BLUE;

    // ball
    public static final double BALL_SPEED = 11.;
    public static final int BALL_MARGIN = 2;



    // score indicator
    public static final int SCORE_INDICATOR_HEIGHT = 20;
    public static final int TEXT_START_X = (int) BOUNDARY_BLOCK_SIZE;
    public static final int TEXT_START_Y = 12;
    public static final int FONT_SIZE = 14;
    public static final double PADDLE_LOCATION_HEIGHT = SCREEN_HEIGHT - BOUNDARY_BLOCK_SIZE - PADDLE_HEIGHT;

    // locations
    public static final int STARTING_X = (int) BOUNDARY_BLOCK_SIZE;
    public static final int STARTING_Y = (int) (BOUNDARY_BLOCK_SIZE + SCORE_INDICATOR_HEIGHT);
    public static final int ENDING_X = (int) (SCREEN_WIDTH - BOUNDARY_BLOCK_SIZE);
    public static final int ENDING_Y = (int) (SCREEN_HEIGHT - BOUNDARY_BLOCK_SIZE);
}
