package basicobjects;

import gameobjects.Block;
import gameobjects.Rectangle;
/**
 * @author Or Nasri
 * @version 3.0
 * @since 30.05.2021
 * one dimensional point
 */
public class Point {
    private final double x;
    private final double y;
    private Rectangle rect;
    private Block block;
    /**
     * Constructor.
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * calculate the distance between 2 points.
     * @param other the other point
     * @return distance
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow((other.x - x), 2) + Math.pow((other.y - y), 2));
    }
    /**
     * check if 2 points are equals.
     * @param other the other point
     * @return if two points are equal
     */
    public boolean equals(Point other) {
        double epsilon = Math.pow(10, -2);
        return (Math.abs(this.x - other.x) < epsilon && Math.abs(this.y - other.y) < epsilon);
    }
    /**
     * @return the x coordinate of the point
     */
    public double getX() {
        return x;
    }
    /**
     * @return the y coordinate of the point
     */
    public double getY() {
        return y;
    }

    /**
     * @return the rectangle
     */
    public Rectangle getRect() {
        return rect;
    }

    /**
     * set rectangle.
     * @param b the rectangle to set
     */
    public void setRectangle(Rectangle b) {
        this.rect = b;
    }

    /**
     * set block.
     * @param b the block to set
     */
    public void setBlock(Block b) {
        this.block = b;
    }

    /**
     * get block.
     * @return the block
     */
    public Block getBlock() {
        return block;
    }
}
