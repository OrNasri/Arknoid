package gameobjects;
import basicobjects.Ball;
import basicobjects.Line;
import basicobjects.Point;
import basicobjects.Velocity;
import biuoop.DrawSurface;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Or Nasri
 * @version 3.0
 * @since 30.05.2021
 * one block
 */
public class Block implements Collidable, Sprite, HitNotifier, HitListener {
    private final Rectangle rectangle;
    private final Color color;
    private final List<HitListener> hitListeners;

    /**
     * Constructor.
     * @param rectangle rectangle of the block
     */
    public Block(Rectangle rectangle) {
        this.rectangle = rectangle;
        this.color = rectangle.getColor();
        this.hitListeners = new ArrayList<>();
    }

    /**
     * @return the "collision shape" of the object.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public List<Point> intersectionPoints(Line line) {
        // creating list of points
        List<Point> pointArrayList = new ArrayList<>();
        //if the line intersecting with left side of the rectangle
        if (line.isIntersecting(this.rectangle.getLeftLine())) {
            //add points to list using auxiliary function
            pointArrayList.add(line.intersectionWith(this.rectangle.getLeftLine()));
        }
        //if the line intersecting with lower side of the rectangle
        if (line.isIntersecting(this.rectangle.getDownLine())) {
            //add points to list using auxiliary function
            pointArrayList.add(line.intersectionWith(this.rectangle.getDownLine()));

        }
        //if the line intersecting with right side of the rectangle
        if (line.isIntersecting(this.rectangle.getRightLine())) {
            //add points to list using auxiliary function
            pointArrayList.add(line.intersectionWith(this.rectangle.getRightLine()));

        }
        //if the line intersecting with lower side of the rectangle
        if (line.isIntersecting(this.rectangle.getUpLine())) {
            //add points to list using auxiliary function
            pointArrayList.add(line.intersectionWith(this.rectangle.getUpLine()));

        }
        for (int i = 0; i < pointArrayList.size(); i++) {
            pointArrayList.get(i).setBlock(this);
        }
        return pointArrayList;
    }


    /**
     * when notify hit is happen.
     * @param hitter the ball that hit.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     * @param collisionPoint collision point
     * @param currentVelocity the update velocity of the ball
     * @param hitter the ball that hitter
     * @return the new velocity of the object.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // the coordinates of the collision point
        double x = collisionPoint.getX();
        double y = collisionPoint.getY();
        // check the conditions
        boolean flag = false;
        // x direction
        if ((x < rectangle.getUpLine().start().getX()) && (x > rectangle.getUpLine().end().getX())
                || (x > rectangle.getUpLine().start().getX()) && (x < rectangle.getUpLine().end().getX())) {
            currentVelocity.setDy((currentVelocity.getDy()) * (-1));
            flag = true;
        }
        // y direction
        if ((y > rectangle.getDownLine().start().getY()) && (y <  rectangle.getUpLine().start().getY())
                || (y < rectangle.getDownLine().start().getY()) && (y >  rectangle.getUpLine().start().getY())) {
            currentVelocity.setDx((currentVelocity.getDx()) * (-1));
            flag = true;
        }
        if (!flag) {
            // if the ball hit corners
            currentVelocity.setDx(currentVelocity.getDx() * (-1));
            currentVelocity.setDy(currentVelocity.getDy() * (-1));

        }
        // print message that block was hit
        this.notifyHit(hitter);
        return currentVelocity;
    }

    /**
     * draw and set color of the block.
     * @param d drawSurface methods
     */
    @Override
    public void drawOn(DrawSurface d) {
        // set color
        d.setColor(color);
        // draw the ball
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    @Override
    public void timePassed() {
    }

    /**
     * add the block as spirit and collidable to the game.
     * @param gameLevel the game
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }

    /**
     * remove the block as spirit and collidable from the game.
     * @param gameLevel the game
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }

    /**
     * remove the block as spirit from the game.
     * @param gameLevel the game
     */
    public void removeFromSprite(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }



    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(beingHit);
    }

    /**
     * return the hit listeners list.
     * @return hit listeners hit
     */
    public List<HitListener> getHitListeners() {
        return hitListeners;
    }
}
