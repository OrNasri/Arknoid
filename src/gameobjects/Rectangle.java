package gameobjects;
import basicobjects.Line;
import basicobjects.Point;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Or Nasri
 * @version 1.0
 * @since 24.04.2021
 * create rectangle
 */
public class Rectangle {
    private Point upperLeft;
    private final double width;
    private final double height;
    private Line upLine;
    private Line downLine;
    private Line rightLine;
    private Line leftLine;
    private java.awt.Color color;
    private boolean isPaddle;

    /**
     * Constructor.
     * @param upperLeft the point up left of the rectangle - the location
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.upLine = new Line(upperLeft, new Point(upperLeft.getX() + width, upperLeft.getY()));
        this.downLine =  new Line(new Point(upperLeft.getX(), upperLeft.getY() + height),
                new Point(upperLeft.getX() + width, upperLeft.getY() + height));
        this.rightLine = new Line(new Point(upperLeft.getX() + width, upperLeft.getY()),
                new Point(upperLeft.getX() + width, upperLeft.getY() + height));
        this.leftLine = new Line(upperLeft, new Point(upperLeft.getX(), upperLeft.getY() + height));
        this.isPaddle = false;
    }


    /**
     * @param line to check the intersection with the rectangle
     * @return a (possibly empty) List of intersection points with the specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> intersectionPoints = new ArrayList<>();
        // check if the line intersect with each line of the rectangle
        if (upLine.isIntersecting(line)) {
            if (upLine.intersectionWith(line) != null) {
                intersectionPoints.add(upLine.intersectionWith(line));
            }
        }
        if (downLine.isIntersecting(line)) {
            if (downLine.intersectionWith(line) != null) {
                intersectionPoints.add(downLine.intersectionWith(line));
            }
        }
        if (leftLine.isIntersecting(line)) {
            if (leftLine.intersectionWith(line) != null) {
                intersectionPoints.add(leftLine.intersectionWith(line));
            }
        }
        if (rightLine.isIntersecting(line)) {
            if (rightLine.intersectionWith(line) != null) {
                intersectionPoints.add(rightLine.intersectionWith(line));
            }
        }
        return intersectionPoints;
    }


    /**
     * @return width of the rectangle
     */
    public double getWidth() {
        return width;
    }

    /**
     * @return height of the rectangle
     */
    public double getHeight() {
        return height;
    }

    /**
     *
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     *
     * @return up line of the rectangle
     */
    public Line getUpLine() {
        return upLine;
    }
    /**
     *
     * @return down line of the rectangle
     */
    public Line getDownLine() {
        return downLine;
    }
    /**
     *
     * @return left line of the rectangle
     */
    public Line getLeftLine() {
        return leftLine;
    }
    /**
     *
     * @return right line of the rectangle
     */
    public Line getRightLine() {
        return rightLine;
    }

    /**
     *
     * @return the color of the rectangle
     */
    public Color getColor() {
        return color;
    }

    /**
     * set color to the rectangle.
     * @param c color of the rectangle
     */
    public void setColor(Color c) {
        this.color = c;
    }

    /**
     *
     * @param value if the rectangle is paddle
     */
    public void setIsPuddle(boolean value) {
        this.isPaddle = value;
    }

    /**
     *
     * @return true if the rectangle is paddle
     */
    public boolean getPuddle() {
        return this.isPaddle;
    }


    /**
     * set rectangle.
     * @param upperLeftPoint the point up left of the rectangle - the location
     */
    public void setRectangle(Point upperLeftPoint) {
        this.upperLeft = upperLeftPoint;
        this.upLine = new Line(upperLeft, new Point(upperLeft.getX() + width, upperLeft.getY()));
        this.downLine =  new Line(new Point(upperLeft.getX(), upperLeft.getY() + height),
                new Point(upperLeft.getX() + width, upperLeft.getY() + height));
        this.rightLine = new Line(new Point(upperLeft.getX() + width, upperLeft.getY()),
                new Point(upperLeft.getX() + width, upperLeft.getY() + height));
        this.leftLine = new Line(upperLeft, new Point(upperLeft.getX(), upperLeft.getY() + height));
    }

    /**
     * draw and set color of the block.
     * @param d methods of draw surface
     */
    public void drawOn(DrawSurface d) {
        // set color
        d.setColor(color);
        // draw the ball
        d.fillRectangle((int) this.getUpperLeft().getX(), (int) this.getUpperLeft().getY(),
                (int) this.getWidth(), (int) this.getHeight());
        d.drawRectangle((int) this.getUpperLeft().getX(), (int) this.getUpperLeft().getY(),
                (int) this.getWidth(), (int) this.getHeight());
    }
}
