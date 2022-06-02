package levels;

import objects.Block;
import objects.Sprite;
import utils.Consts;
import utils.Velocity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DirectHitLevel implements LevelInformation {

    private Sprite background;
    private List<Velocity> ballVelocities;
    private List<Block> blocks;
    private static final int PADDLE_SPEED = 5;

    public DirectHitLevel() {
        this.ballVelocities = new ArrayList<>();
        this.ballVelocities.add(Velocity.fromAngleAndSpeed(270, Consts.BALL_SPEED));

        this.blocks = new ArrayList<>();
        this.blocks.add(new Block(Consts.SCREEN_WIDTH / 2., Consts.SCREEN_HEIGHT  / 3.,
                20, 20, Color.BLACK));

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
