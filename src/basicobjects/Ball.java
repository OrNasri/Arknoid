package basicobjects;
import biuoop.DrawSurface;
import gameobjects.CollisionInfo;
import gameobjects.GameLevel;
import gameobjects.GameEnvironment;
import interfaces.Sprite;

import java.awt.Color;

/**
 * @author Or Nasri
 * @version 3.0
 * @since 13.06.2021
 * create ball
 */
public class Ball implements Sprite {
    private Velocity v;
    private Point center;
    private final int r;
    private final java.awt.Color color;
    private int xMax;
    private int yMax;
    private final int xMin;
    private final int yMin;
    private GameEnvironment gameEnvironment;
    private boolean hitBlock;

    /**
     * Constructor.
     *
     * @param center point center of the ball
     * @param r      the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.color = color;
        this.xMax = 200;
        this.yMax = 200;
        this.xMin = 0;
        this.yMin = 0;
        this.r = r;
        this.v = new Velocity(r, r - 1);
        this.hitBlock = false;
    }

    /**
     * Constructor.
     *
     * @param x     the x coordinate of the center point
     * @param y     the y coordinate of the center point
     * @param r     the radius of the ball
     * @param color the color of the ball
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.color = color;
        this.xMax = 200;
        this.yMax = 200;
        this.xMin = 0;
        this.yMin = 0;
        this.r = r;
        this.v = new Velocity(r, r - 1);
        this.hitBlock = false;
    }

    /**
     * @return the y coordinate of the center point
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * @return the radius
     */
    public int getR() {
        return this.r;
    }

    /**
     * @return the x coordinate of the center point
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * @return the r the radius of the ball
     */
    public int getSize() {
        return r;
    }

    /**
     * @param value if the ball hit block.
     */

    public void setHit(boolean value) {
        this.hitBlock = value;
    }

    /**
     * @return if the block hit the block
     */
    public boolean getHit() {
        return this.hitBlock;
    }

    /**
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return color;
    }

    /**
     * draw the ball on the given DrawSurface.
     *
     * @param surface several methods that used to draw circles.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) center.getX(), (int) center.getY(), r);
        surface.setColor(color);
        surface.fillCircle((int) center.getX(), (int) center.getY(), r);
    }

    @Override
    public void timePassed() {
        moveOneStep();

    }

    /**
     * update the velocity of the ball.
     *
     * @param vel the velocity
     */
    public void setVelocity(Velocity vel) {
        this.v = vel;
    }

    /**
     * update the velocity of the ball.
     *
     * @param dx the velocity specifies the change in position on the `x` axes
     * @param dy the velocity specifies the change in position on the `y` axes
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return v;
    }

    /**
     * update the x max of the range of the ball.
     *
     * @param num x coordinate of the max point
     */
    public void setXMax(int num) {
        this.xMax = num;
    }

    /**
     * update the y max of the range of the ball.
     *
     * @param num y coordinate of the max point
     */
    public void setYMax(int num) {
        this.yMax = num;
    }
    /**
     * Constructor.
     *
     * @param gameEnv set the game environment
     */
    public void setGameEnvironment(GameEnvironment gameEnv) {
        this.gameEnvironment = gameEnv;
    }

    /**
     *  make change in the ball to support arbitrary collisions.
     */

    public void moveOneStep() {
        Velocity currentVal = this.getVelocity();
        // calculate ball trajectory
        Line ballTrajectory = new Line(this.center, new Point(this.center.getX() + currentVal.getDx(),
                this.center.getY() + currentVal.getDy()));
        //  update collision info
        CollisionInfo collisionInfo = this.gameEnvironment.getClosestCollision(ballTrajectory);
        // if moving on this trajectory will not hit anything
        if (this.gameEnvironment.getClosestCollision(ballTrajectory) != null) {
            // if moving on this trajectory will hit block or paddle and update the ball move
            currentVal = collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), currentVal);
            this.setVelocity(currentVal);
        }
        this.center = currentVal.applyToPoint(this.center);
        // change the ball velocity to the right direction
        if (this.center.getY() + r >= yMax || this.center.getY() - r <= yMin) {
            setVelocity(this.v.getDx(), -this.v.getDy());
        }
        if (this.center.getX() + r >= xMax || this.center.getX() - r <= xMin) {
            setVelocity(-this.v.getDx(), this.v.getDy());
        }
    }

    /**
     * add the ball as spirit to the game.
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
    /**
     * remove the ball as spirit from the game.
     * @param gameLevel the game
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}