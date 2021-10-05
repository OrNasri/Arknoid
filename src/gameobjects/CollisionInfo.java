package gameobjects;

import basicobjects.Point;
import interfaces.Collidable;

/**
 * @author Or Nasri
 * @version 1.0
 * @since 24.04.2021
 *  information of collision objects
 */
public class CollisionInfo {
    private final Point collisionPoint;
    private final Collidable object;

    /**
     * Constructor.
     *
     * @param collision point collision of the ball
     * @param object the object that collidable
     */
    public CollisionInfo(Point collision,  Collidable object) {
        this.collisionPoint = collision;
        this.object = object;
    }

    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.object;
    }
}