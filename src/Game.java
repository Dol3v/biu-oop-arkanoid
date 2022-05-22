import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Runs the Game: two balls bouncing in space, which can also bounce off a moving paddle.
 */
public class Game {

    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private ScoreTrackingListener scoreTrackingListener;
    private Counter availableBalls;

    private static final int SCORE_INCREASE_ON_LEVEL_COMPLETION = 100;

    /**
     * Initializes the game's sprites and collidables.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.availableBalls = new Counter(0);
        this.ballRemover = new BallRemover(this);
        this.scoreTrackingListener = new ScoreTrackingListener(new Counter(0));
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

    /**
     * Adds blocks to the game.
     */
    public void addBlocksToGame() {
        List<Block> boundaryBlocks = new ArrayList<>();

        // creating death block
        Block deathBlock = new Block(0, Consts.SCREEN_HEIGHT - Consts.BOUNDARY_BLOCK_SIZE, Consts.BOUNDARY_BLOCK_SIZE,
                Consts.SCREEN_WIDTH, Color.GRAY);
        deathBlock.addHitListener(ballRemover);

        // creating boundary blocks
        boundaryBlocks.add(new Block(0, Consts.SCORE_INDICATOR_HEIGHT, Consts.SCREEN_HEIGHT,
                Consts.BOUNDARY_BLOCK_SIZE, Color.GRAY));
        boundaryBlocks.add(new Block(Consts.SCREEN_WIDTH - Consts.BOUNDARY_BLOCK_SIZE, Consts.SCORE_INDICATOR_HEIGHT,
                Consts.SCREEN_HEIGHT, Consts.BOUNDARY_BLOCK_SIZE, Color.GRAY));
        boundaryBlocks.add(deathBlock);
        boundaryBlocks.add(new Block(0, Consts.SCORE_INDICATOR_HEIGHT,
                Consts.BOUNDARY_BLOCK_SIZE, Consts.SCREEN_WIDTH, Color.GRAY));

        // boundary blocks are indestructible
        for (Block boundaryBlock : boundaryBlocks) {
            boundaryBlock.addToGame(this);
        }

        List<Block> blocks = new ArrayList<>();
        Color[] blockColors = new Color[]{Color.GREEN, Color.BLACK, Color.RED, Color.CYAN, Color.BLUE, Color.ORANGE};

        for (int row = 0; row < 6; row++) {
            for (int col = 1; col < 5 + row + 1; col++) {
                blocks.add(new Block(Consts.SCREEN_WIDTH - col * Consts.BLOCK_LENGTH - Consts.BOUNDARY_BLOCK_SIZE,
                        Consts.BLOCK_STARTING_HEIGHT - Consts.BLOCK_LENGTH * row, Consts.BLOCK_LENGTH,
                        Consts.BLOCK_LENGTH, blockColors[row]));
            }
        }
        this.blockRemover = new BlockRemover(this, blocks.size());

        for (Block block : blocks) {
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
            block.addToGame(this);
        }
    }

    /**
     * Initializes the game's GUI, objects, blocks. balls, and paddle.
     */
    public void initialize() {
        gui = new GUI("title", Consts.SCREEN_WIDTH, Consts.SCREEN_HEIGHT);
        sleeper = new Sleeper();

        addBlocksToGame();
        // adding balls
        Ball ball = new Ball(Consts.SCREEN_WIDTH / 2. - 60, Consts.SCREEN_HEIGHT / 2., 10, Color.BLACK, environment);
        Ball ball2 = new Ball(Consts.SCREEN_WIDTH / 2. - 30, Consts.SCREEN_HEIGHT / 2., 10, Color.BLACK,
                environment);
        Ball ball3 = new Ball(Consts.SCREEN_WIDTH / 2. - 90, Consts.SCREEN_HEIGHT / 2., 10, Color.BLACK,
                environment);

        ball.setVelocity(Velocity.fromAngleAndSpeed(50, Consts.BALL_SPEED));
        ball2.setVelocity(Velocity.fromAngleAndSpeed(310, Consts.BALL_SPEED));
        ball3.setVelocity(Velocity.fromAngleAndSpeed(170, Consts.BALL_SPEED));
        ball.addToGame(this);
        ball2.addToGame(this);
        ball3.addToGame(this);
        // registering the number of balls
        availableBalls.increase(3);

        Paddle paddle = new Paddle(gui.getKeyboardSensor());
        paddle.addToGame(this);

        ScoreIndicator scoreIndicator = new ScoreIndicator(scoreTrackingListener.getCurrentScore());
        addSprite(scoreIndicator);
    }

    /**
     * Runs the game.
     */
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        boolean shouldEndGame = false;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            if (availableBalls.getValue() == 0) {
                gui.close();
                return;
            }

            if (shouldEndGame) {
                sleeper.sleepFor(1000);
                gui.close();
                return;
            }

            if (blockRemover.getRemainingBlocks().getValue() == 0) {
                scoreTrackingListener.getCurrentScore().increase(SCORE_INCREASE_ON_LEVEL_COMPLETION);
                shouldEndGame = true;
            }

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * Returns a reference to the counter for available balls in the game.
     *
     * @return available balls counter
     */
    public Counter getAvailableBalls() {
        return availableBalls;
    }
}
