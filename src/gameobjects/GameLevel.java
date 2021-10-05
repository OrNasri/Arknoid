package gameobjects;
import basicobjects.Ball;
import basicobjects.Point;
import basicobjects.Velocity;
import biuoop.DrawSurface;
import biuoop.GUI;
import interfaces.Animation;
import interfaces.Collidable;
import interfaces.Sprite;
import interfaces.HitListener;
import interfaces.LevelInformation;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.SideHit;
import screens.CountdownAnimation;
import screens.KeyPressStoppableAnimation;
import screens.PauseScreen;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Or Nasri
 * @version 2.0
 * @since 10.06.2021
 *  information of the game level
 */
public class GameLevel implements Animation {
    public static final int WIDTH_FRAME = 800;
    public static final int HEIGHT_FRAME = 600;
    public static final Point DOWN_LIMIT_POINT = new Point(0, 580);
    public static final int DOWN_LIMIT_WIDTH = 800;
    public static final int DOWN_LIMIT_HEIGHT = 20;
    public static final int X = 370;
    public static final int Y = 520;
    public static final int HEIGHT_PADDLE = 30;
    private final AnimationRunner runner;
    private boolean running;
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final GUI gui;
    private final Counter blocksCounter;
    private final Counter ballsCounter;
    private final Counter counterScore;
    private final LevelInformation level;
    private boolean isDead;

    /**
     * Constructor.
     * create a new game
     * @param levelToPlay the level that we want to run
     * @param gui gui object
     * @param runner animation runner
     * @param score score counter of the game
     */
    public GameLevel(LevelInformation levelToPlay, GUI gui, AnimationRunner runner, Counter score) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui =  gui;
        this.blocksCounter = new Counter();
        this.ballsCounter = new Counter();
        this.counterScore = score;
        this.runner = runner;
        this.running = true;
        this.level = levelToPlay;
        this.isDead = false;
    }


    /**
     * add listeners to ech block.
     * @param listenerList the list of listeners to add to the block
     * @param block the block that we want to add it the listeners.
     */
    public void addListenersToBlock(List<HitListener> listenerList, Block block) {
        for (int i = 0; i < listenerList.size(); i++) {
            block.addHitListener(listenerList.get(i));
        }
    }

    /**
     * add the collidable to the environment game.
     * @param c the collidable
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * add the sprite to the environment game.
     * @param s the sprite
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * @return if it already dead.
     */
    public boolean getLife() {
        return this.isDead;
    }

    /**
     * remove the collidable from the environment game.
     * @param c the collidable
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * remove the sprite from the environment game.
     * @param s the sprite
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * create new ball to the game.
     * @param color color of the ball
     * @param size number of balls to create
     * @param vel list of velocities
     */
    public void createBalls(Color color, int size, List<Velocity> vel) {
        for (int i = 0; i < size; i++) {
            // create ball
            Ball newBall = new Ball(new Point(X, Y), 5, color);
            // update the limits of the ball
            newBall.setXMax(WIDTH_FRAME);
            newBall.setYMax(HEIGHT_FRAME);
            // set velocity
            newBall.setVelocity(vel.get(i).getDx(), vel.get(i).getDy());
            // add to the game
            newBall.addToGame(this);
            newBall.setGameEnvironment(environment);
        }
    }

    /**
     * create block the make limits to the game.
     * @param rect get rectangle to create ball
     */
    public void createLimits(Rectangle rect) {
        // set color
        rect.setColor(Color.GRAY);
        // create block
        Block block = new Block(rect);
        block.addToGame(this);
        block.addHitListener(new SideHit());
    }

    /**
     * create dead limit - the down limit line.
     * @param listener create new limit
     */
    public void createDeadLimit(HitListener listener) {
        Rectangle rect = new Rectangle(DOWN_LIMIT_POINT, DOWN_LIMIT_WIDTH, DOWN_LIMIT_HEIGHT);
        // set color
        rect.setColor(Color.LIGHT_GRAY);
        // create block
        Block block = new Block(rect);
        // add it to hit listener list
        block.addHitListener(listener);
        // add to game
        block.addToGame(this);
        // remove from sprite - that ball can disappear
        block.removeFromSprite(this);
    }
    /**
     * Initialize a new game: create the Blocks and Ball and Paddle and add them to the game.
     * it's implemented with for loop
     */
    public void initialize() {
        // update the score
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(counterScore);
        // see score
        ScoreIndicator scoreIndicator = new ScoreIndicator(counterScore);
        createBalls(this.level.colorOfBalls(), this.level.numberOfBalls(), this.level.initialBallVelocities());
        // update block counters
        this.ballsCounter.increase(this.level.numberOfBalls());
        // create paddle
        Rectangle rectPaddle = new Rectangle(this.level.getStartPaddle(),
                this.level.paddleWidth(), HEIGHT_PADDLE);
        rectPaddle.setColor(this.level.paddleColor());
        Paddle paddle = new Paddle(rectPaddle);
        paddle.setSpeed(this.level.paddleSpeed());
        // create GUI
        paddle.setGui(gui);
        paddle.setKeyboard();
        paddle.addToGame(this);
        // create blocks limits
        Rectangle rectUp = new Rectangle(new Point(0, 0), 800, 50);
        createLimits(rectUp);
        Rectangle rectLeft = new Rectangle(new Point(0, 50), 25, 770);
        createLimits(rectLeft);
        Rectangle rectRight = new Rectangle(new Point(775, 50), 25, 770);
        createLimits(rectRight);
        HitListener listener = new BallRemover(this, this.ballsCounter);
        createDeadLimit(listener);
        ArrayList<HitListener> listeners = new ArrayList<>();
        if (!this.level.isDirectHit()) {
            HitListener listen = new BlockRemover(this);
            listeners.add(listen);
            listeners.add(scoreTrackingListener);
        }
        // create blocks
        List<Block> blockList = this.level.blocks();
        for (int i = 0; i < blockList.size(); i++) {
            addListenersToBlock(listeners, blockList.get(i));
            blockList.get(i).addToGame(this);
        }
        // update block counter
        this.blocksCounter.increase(this.level.numberOfBlocksToRemove());
        sprites.addSprite(scoreIndicator);
    }

    /**
     * Run the game - start the animation loop.
     */
    public void run() {
        this.running = true;
        this.runner.run(new CountdownAnimation(0.8, 3, this.sprites, this.level.getBackground()));
        // use our runner to run the current animation, which is one turn of the game.
        this.runner.run(this);
    }

    /**
     * return the value of the counter.
     * @return the block counter
     */
    public Counter getBlocksCounter() {
        return this.blocksCounter;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // timing
        long startTime = System.currentTimeMillis();
        // create and color the frame
        this.level.getBackground().drawOn(d);
        this.level.drawOn(d);
        // draw all the spirits
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        d.setColor(Color.BLACK);
        d.drawText(580, 15, "Level: " + this.level.levelName(), 20);
        if (this.gui.getKeyboardSensor().isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.gui.getKeyboardSensor(), "space", new PauseScreen()));
        }
        // check if all the ball fall down or all the blocks disappear - and close the game
        if (ballsCounter.getValue() == 0 || blocksCounter.getValue() == 0 || this.level.getDidIt()) {
            if (ballsCounter.getValue() == 0) {
                this.isDead = true;
            }
            this.running = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}
