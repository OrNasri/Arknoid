package gameobjects;
import basicobjects.Ball;
import basicobjects.Line;
import basicobjects.Point;
import basicobjects.Velocity;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import interfaces.Collidable;
import interfaces.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Or Nasri
 * @version 1.0
 * @since 24.04.2021
 * create paddle
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private final Rectangle rectangle;
    private final Color color;
    private DrawSurface d;
    private GUI gui;
    private int speed;

    /**
     * Constructor.
     * @param rectangle of the paddle
     */
    public Paddle(Rectangle rectangle) {
        this.rectangle = rectangle;
        this.rectangle.setIsPuddle(true);
        this.color = rectangle.getColor();
    }

    /**
     * @param s to set
     */
    public void setSpeed(int s) {
        this.speed = s;
    }

    /**
     * rectangle that is controlled by the arrow keys,
     * and moves according to the player key presses to left side.
     */
    public void moveLeft() {
        Point temp = rectangle.getUpperLeft();
        // move the paddle to the left side
        if (temp.getX() - 25 <= 0) {
            rectangle.setRectangle(rectangle.getUpperLeft());
        } else {
            rectangle.setRectangle(new Point(temp.getX() - this.speed, temp.getY()));
        }
        rectangle.drawOn(d);
    }

    /**
     * rectangle that is controlled by the arrow keys,
     * and moves according to the player key presses to right side.
     */
    public void moveRight() {
        Point temp = rectangle.getUpperLeft();
        // move the paddle to the right side
        if (temp.getX() + 25 >= 800 - rectangle.getWidth()) {
            rectangle.setRectangle(rectangle.getUpperLeft());
        } else {
            rectangle.setRectangle(new Point(temp.getX() + this.speed, temp.getY()));
        }
    }

    /**
     * notify the sprite that time has passed.
     */
    public void timePassed() {
        // check what user press and move
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * draw and set color of the block.
     * @param ds drawSurface methods
     */
    @Override
    public void drawOn(DrawSurface ds) {
        // set color
        ds.setColor(color);
        // draw the ball
        ds.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        ds.setColor(Color.BLACK);
        ds.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    /**
     * collidable.
     * @return rectangle collision rectangle
     * the "collision shape" of the object.
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
        return pointArrayList;
    }


    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     * @param collisionPoint coordinates of the collision point
     * @param currentVelocity the velocity
     * @param hitter the ball that hit
     * @return update velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        hitter.setHit(true);
        Velocity newVelocity = currentVelocity;
        // the value of the collision point
        double x = collisionPoint.getX();
        // divide the paddle into 5 equal parts
        double lengthRect = rectangle.getUpLine().length() / 5;
        // calculate the new speed
        double speedHit = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));
        double startPoint = rectangle.getUpLine().start().getX();
        // check if the ball hit in the part one of the paddle
        if ((x >= startPoint) && (x <= startPoint + lengthRect)) {
            newVelocity = currentVelocity.fromAngleAndSpeed(-60, speedHit);
            // check if the ball hit in the part two of the paddle
        } else if ((x >= startPoint + lengthRect) && (x <= startPoint + (lengthRect * 2))) {
            newVelocity = currentVelocity.fromAngleAndSpeed(330, speedHit);
            // check if the ball hit in the part three of the paddle
        } else if ((x >= startPoint + (lengthRect * 2)) && (x <= startPoint + (lengthRect * 3))) {
            double dy = (-1) * currentVelocity.getDy();
            double dx = currentVelocity.getDx();
            newVelocity = new Velocity(dx, dy);
            // check if the ball hit in the part four of the paddle
        } else if ((x >= startPoint + (lengthRect * 3)) && (x <= startPoint + (lengthRect * 4))) {
            newVelocity = currentVelocity.fromAngleAndSpeed(30, speedHit);
            // check if the ball hit in the part five of the paddle
        } else if ((x >= startPoint + (lengthRect * 4)) && (x <= startPoint + (lengthRect * 5))) {
            newVelocity = currentVelocity.fromAngleAndSpeed(60, speedHit);
        } else {
            newVelocity.setDy(currentVelocity.getDy() * (-1));
            newVelocity.setDx(currentVelocity.getDx() * (-1));
        }
        return newVelocity;
    }

    /**
     * add this paddle to the game.
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * set the gui methods.
     * @param g Gui
     */
    public void setGui(GUI g) {
        this.gui = g;
        this.d = gui.getDrawSurface();
    }

    /**
     * set key board.
     */
    public void setKeyboard() {
        this.keyboard = this.gui.getKeyboardSensor();
    }
}