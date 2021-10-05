package basicobjects;
/**
 * @author Or Nasri
 * @version 2.0
 * since 24.04.2021
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;
    /**
     * Constructor.
     * @param dx the velocity specifies the change in position on the `x` axes
     * @param dy the velocity specifies the change in position on the `y` axes
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     * @param p the point with position
     * @return the new point
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }
    /**
     * @return the dx the velocity
     */
    public double getDx() {
        return dx;
    }
    /**
     * @return the dy the velocity
     */
    public double getDy() {
        return dy;
    }

    /**
     * A method for specifying velocity in terms and velocities.
     * @param angle the angle of the direction
     * @param speed the velocity
     * @return the new velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // casting to radians
        double inRadians = Math.toRadians(angle);
        // calculate the new velocity
        double dx = speed *  Math.sin(inRadians);
        double dy = (-1) * speed *  Math.cos(inRadians);
        return new Velocity(dx, dy);
    }

    /**
     *
     * @param v the dy of the velocity
     */
    public void setDy(double v) {
        this.dy = v;
    }

    /**
     *
     * @param v the dx of the velocity
     */
    public void setDx(double v) {
        this.dx = v;
    }
}
