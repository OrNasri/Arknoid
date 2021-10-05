package interfaces;

import basicobjects.Ball;
import basicobjects.Line;
import basicobjects.Point;
import basicobjects.Velocity;
import gameobjects.Rectangle;

/**
 * @author Or Nasri
 * @version 2.0
 * @since 30.05.2021
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     * @return collision rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * return list of intersection points.
     * @param line of intersection
     * @return list
     */
     java.util.List<Point> intersectionPoints(Line line);
    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     * @param collisionPoint coordinates of the collision point
     * @param currentVelocity the velocity
     * @param hitter the ball that hit
     * @return update velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}