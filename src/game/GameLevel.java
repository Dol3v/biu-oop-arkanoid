package game;

import abstractshapes.Point;
import abstractshapes.Rectangle;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import hitlisteners.BallRemover;
import hitlisteners.BlockRemover;
import hitlisteners.ScoreTrackingListener;
import statsbar.Indicator;
import levels.LevelInformation;
import objects.*;
import screens.CountdownAnimation;
import screens.PauseScreen;
import statsbar.StatsBar;
import utils.Consts;
import utils.Counter;
import utils.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Runs the Game: two balls bouncing in space, which can also bounce off a moving paddle.
 */
public class GameLevel implements Animation {

    private SpriteCollection sprites;
    private GameEnvironment environment;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private ScoreTrackingListener scoreTrackingListener;
    private Counter availableBalls;
    private Counter lives;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboardSensor;
    private LevelInformation currentLevel;

    private static final int FRAMES_PER_SECOND = 60;

    private static final int SCORE_INCREASE_ON_LEVEL_COMPLETION = 100;
    public static final int LIVES = 10;

    /**
     * Initializes the game's sprites and collidables.
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor sensor, AnimationRunner runner, Counter currentScore) {
        this.currentLevel = levelInformation;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.availableBalls = new Counter(levelInformation.numberOfBalls());
        this.lives = new Counter(LIVES);
        this.keyboardSensor = sensor;
        this.runner = runner;
        this.running = true;

        // listeners
        this.ballRemover = new BallRemover(this);
        this.blockRemover = new BlockRemover(this, levelInformation.numberOfBlocksToRemove());
        this.scoreTrackingListener = new ScoreTrackingListener(currentScore);
    }

    /**
     * Adds a collidable to the game.
     *
     * @param c collidable to add
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Adds a sprite to the game.
     *
     * @param s sprite to add
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Removes a collidable from the game.
     *
     * @param c collidable to remove
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * Removes a sprite from the game.
     *
     * @param s sprite to remove
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    private void addBoundaryBlocks() {
        // creating death block
        Block deathBlock = new Block(0, Consts.SCREEN_HEIGHT - Consts.BOUNDARY_BLOCK_MARGIN_SIZE, Consts.BOUNDARY_BLOCK_MARGIN_SIZE, Consts.SCREEN_WIDTH, Color.GRAY);
        deathBlock.addHitListener(ballRemover);

        List<Block> boundaryBlocks = new ArrayList<>();
        boundaryBlocks.add(new Block(0, Consts.STATS_BAR_HEIGHT, Consts.SCREEN_HEIGHT, Consts.BOUNDARY_BLOCK_MARGIN_SIZE, Color.GRAY));
        boundaryBlocks.add(new Block(Consts.SCREEN_WIDTH - Consts.BOUNDARY_BLOCK_MARGIN_SIZE, Consts.STATS_BAR_HEIGHT, Consts.SCREEN_HEIGHT, Consts.BOUNDARY_BLOCK_MARGIN_SIZE, Color.GRAY));
        boundaryBlocks.add(deathBlock);
        boundaryBlocks.add(new Block(0, Consts.STATS_BAR_HEIGHT, Consts.BOUNDARY_BLOCK_MARGIN_SIZE, Consts.SCREEN_WIDTH, Color.GRAY));

        for (Block boundaryBlock : boundaryBlocks) {
            boundaryBlock.addToGame(this);
        }
    }

    /**
     * Initializes the game's objects, blocks. balls, and paddle.
     */
    public void initialize() {
        // adding blocks
        for (Block block : currentLevel.blocks()) {
            block.addToGame(this);
            block.addHitListener(blockRemover);
        }
        addBoundaryBlocks();

        // adding paddle
        Paddle paddle = new Paddle(keyboardSensor, new Rectangle(Consts.SCREEN_WIDTH / 2. - currentLevel.paddleWidth() / 2., Consts.PADDLE_LOCATION_HEIGHT, Consts.PADDLE_HEIGHT, currentLevel.paddleWidth()), currentLevel.paddleSpeed());
        paddle.addToGame(this);

        // adding stats bar
        StatsBar statsBar = new StatsBar();
        addSprite(statsBar);

        Indicator<Integer> scoreIndicator = new Indicator<>(scoreTrackingListener.getCurrentScore(),
                20,
                "Score");
        addSprite(scoreIndicator);

        Indicator<Integer> livesIndicator = new Indicator<>(lives,
                Consts.SCREEN_WIDTH - 80,
                "Lives");
        addSprite(livesIndicator);

        Indicator<String> levelNameIndicator = new Indicator<>(() -> currentLevel.levelName(),
                Consts.SCREEN_WIDTH / 2,
                "Name");
        addSprite(levelNameIndicator);
    }

    public void createBalls() {
        List<Velocity> velocities = currentLevel.initialBallVelocities();
        List<Point> centers = currentLevel.initialBallCenters();

        for (int i = 0; i < velocities.size(); i++) {
            Ball ball = new Ball(
                    centers.get(i),
                    Consts.BALL_RADIUS,
                    Color.BLACK,
                    environment
            );
            ball.setVelocity(velocities.get(i));
            ball.addToGame(this);
        }
    }

    /**
     * Runs the game.
     */
    public void run() {
        runner.run(new CountdownAnimation(2, 3, sprites));
        createBalls();
        runner.run(this);
    }

    /**
     * Returns a reference to the counter for available balls in the game.
     *
     * @return available balls counter
     */
    public Counter getAvailableBalls() {
        return availableBalls;
    }

    /**
     * Executes one frame of the animation.
     *
     * @param d surface to draw the animation on
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        sprites.drawAllOn(d);
        sprites.notifyAllTimePassed();

        if (blockRemover.getRemainingBlocks().getValue() == 0) {
            scoreTrackingListener.getCurrentScore().increase(SCORE_INCREASE_ON_LEVEL_COMPLETION);
            running = false;
            return;
        }

        if (availableBalls.getValue() == 0) {
            running = false;
            return;
        }

        if (keyboardSensor.isPressed("p")) {
            runner.run(new PauseScreen(keyboardSensor));
        }
    }

    /**
     * Checks if the animation should stop.
     *
     * @return whether the animation should stop
     */
    @Override
    public boolean shouldStop() {
        return !running;
    }

    /**
     * Returns the FPS rate the animation should run at.
     *
     * @return the animation's FPS rate
     */
    @Override
    public double getFramesPerSecond() {
        return FRAMES_PER_SECOND;
    }
}
