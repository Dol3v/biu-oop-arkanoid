package levels;

import abstractshapes.Point;
import com.sun.source.tree.BreakTree;
import objects.Block;
import objects.Sprite;
import utils.Consts;
import utils.Velocity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Second game level.
 */
public class WideEasyLevel implements LevelInformation {

    private List<Velocity> velocities;
    private List<Block> blocks;

    public static final int NUMBER_OF_BALLS = 10;
    public static final int NUMBER_OF_BLOCKS = 15;
    public static final int PADDLE_SPEED = 5;

    private static final double PERCENT_OF_COVERED_AREA = 0.85;
    private static final int BLOCKS_Y = 300;
    private static final int BLOCK_HEIGHT = 30;

    public WideEasyLevel() {
        velocities = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_BALLS; i++) {
            velocities.add(Velocity.randomFromRanges(-90, 90, Consts.BALL_SPEED, Consts.BALL_SPEED));
        }

        blocks = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_BLOCKS; i++) {
            Color color;
            switch (i) {
                case 0, 1:
                    color = Color.RED;
                    break;
                case 2, 3:
                    color = Color.ORANGE;
                    break;
                case 4, 5:
                    color = Color.YELLOW;
                    break;
                case 6, 7, 8:
                    color = Color.GREEN;
                    break;
                case 9, 10:
                    color = Color.BLUE;
                    break;
                case 11, 12:
                    color = Color.PINK;
                    break;
                case 13, 14:
                    color = Color.CYAN;
                    break;
                default:
                    color = Color.BLACK;
            }
            blocks.add(new Block(
                    Consts.BOUNDARY_BLOCK_MARGIN_SIZE + i * (Consts.GAME_WIDTH / NUMBER_OF_BLOCKS),
                    BLOCKS_Y,
                    BLOCK_HEIGHT,
                    Consts.GAME_WIDTH / NUMBER_OF_BLOCKS,
                    color
            ));
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
        Point circleCenter = new Point(Consts.SCREEN_WIDTH / 2., 100);
        double radius = 100;

        List<Point> centers = new ArrayList<>();
        double initialDeviation = 12;
        for (double deviation = initialDeviation; deviation <= initialDeviation * NUMBER_OF_BALLS / 2;
             deviation += initialDeviation) {
            centers.add(circleCenter.addPoint(
                    radius * Math.cos((Math.PI / 180) * (90 + deviation)),
                    radius * Math.sin((Math.PI / 180) * (90 + deviation))
            ));
            centers.add(circleCenter.addPoint(
                    radius * Math.cos((Math.PI / 180) * (90 - deviation)),
                    radius * Math.sin((Math.PI / 180) * (90 - deviation))
            ));
        }

        return centers.stream().map(p -> new Point(p.getX(), Consts.SCREEN_HEIGHT - p.getY()))
                .collect(Collectors.toList());
    }

    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    @Override
    public int paddleWidth() {
        return (int) (PERCENT_OF_COVERED_AREA * Consts.GAME_WIDTH);
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new WideEasyBackground();
    }

    @Override
    public List<Block> blocks() {
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUMBER_OF_BLOCKS;
    }
}
