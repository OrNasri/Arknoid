package interfaces;

import basicobjects.Point;
import basicobjects.Velocity;
import biuoop.DrawSurface;
import gameobjects.Block;

import java.awt.Color;
import java.util.List;

/**
 * @author Or Nasri
 * @version 2.0
 * @since 06.06.2021
 * level information.
 */
public interface LevelInformation {
    /**
     * number of balls in this level.
     * @return the number
     */
    int numberOfBalls();
    /**
     * The initial velocity of each ball.
     * @return the list of velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return paddle speed.
     */
    int paddleSpeed();

    /**
     * @return paddle width.
     */
    int paddleWidth();

    /**
     * the level name will be displayed at the top of the screen.
     * @return the name of the level.
     */
    String levelName();

    /**
     * @return a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * create list of blocks.
     * @param blocksList list
     * @param x coordinate of start the blocks
     * @param y coordinate of start the blocks
     * @param width of each block
     * @param height of each block
     * @param color of each block
     * @param size number of blocks
     * @return list of all the blocks
     */
    List<Block> createBlocks(List<Block> blocksList, int x, int y, int width, int height, Color color, int size);
    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     * @return the list of blocks
     */
    List<Block> blocks();

    /**
     * @return list of colors to the blocks
     */
    List<Color> createColors();

    /**
     * @return if is level with direct hit.
     */
    boolean isDirectHit();
    /**
     * draw all elements.
     * @param d surface
     */
    void drawOn(DrawSurface d);
    /**
     * @return number of blocks that should be removed
     */
    int numberOfBlocksToRemove();

    /**
     * @return point start of rectangle of paddle
     */
    Point getStartPaddle();
    /**
     * @return paddle color
     */
    Color paddleColor();

    /**
     * @return color of balls
     */
    Color colorOfBalls();
    /**
     * @param value if was direct hit
     */
    void setDidIt(boolean value);

    /**
     * @return return if it direct hit level
     */
    boolean getDidIt();

}

