import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.*;
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
    private BlockRemover remover;

    /**
     * Initializes the game's sprites and collidables.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
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

        boundaryBlocks.add(new Block(0, 0, Consts.SCREEN_HEIGHT, Consts.BOUNDARY_BLOCK_SIZE, Color.GRAY));
        boundaryBlocks.add(new Block(Consts.SCREEN_WIDTH - Consts.BOUNDARY_BLOCK_SIZE, 0, Consts.SCREEN_HEIGHT,
                Consts.BOUNDARY_BLOCK_SIZE, Color.GRAY));
        boundaryBlocks.add(new Block(0, Consts.SCREEN_HEIGHT - Consts.BOUNDARY_BLOCK_SIZE, Consts.BOUNDARY_BLOCK_SIZE,
                Consts.SCREEN_WIDTH, Color.GRAY));
        boundaryBlocks.add(new Block(0, 0, Consts.BOUNDARY_BLOCK_SIZE, Consts.SCREEN_WIDTH, Color.GRAY));

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
        this.remover = new BlockRemover(this, blocks.size());

        for (Block block : blocks) {
            block.addHitListener(remover);
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

        ball.setVelocity(Velocity.fromAngleAndSpeed(50, Consts.BALL_SPEED));
        ball2.setVelocity(Velocity.fromAngleAndSpeed(310, Consts.BALL_SPEED));
        ball.addToGame(this);
        ball2.addToGame(this);

        Paddle paddle = new Paddle(gui.getKeyboardSensor());
        paddle.addToGame(this);

    }

    /**
     * Runs the game.
     */
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            if (remover.getRemainingBlocks().getValue() == 0) {
                gui.close();
                return;
            }

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
