package gameobjects;
import basicobjects.Line;
import basicobjects.Point;
import interfaces.Collidable;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Or Nasri
 * @version 1.0
 * @since 24.04.2021
 *  environment of the game
 */
public class GameEnvironment {
    private final ArrayList<Collidable> collidableList;

    /**
     * Constructor.
     * create the game environment
     */
    public GameEnvironment() {
        this.collidableList = new ArrayList<>();
    }
    /**
     * add the given collidable to the environment.
     * @param c collidable to add
     */
    public void addCollidable(Collidable c) {
        this.collidableList.add(c);
    }
    /**
     * remove the given collidable to the environment.
     * @param c collidable to add
     */
    public void removeCollidable(Collidable c) {
        this.collidableList.remove(c);
    }

    /**
     * check if this object will not collide with any of the collidables in this collection, return null.
     * Else, return the information about the closest collision that is going to occur.
     * @param trajectory the line that the object is moving
     * @return collision info of the closet collision
     * it's implemented with 3 for loops
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        int index = 0;
        // create array of collision points
        List<Point> collisionPoints = new ArrayList<>();
        for (int i = 0; i < collidableList.size(); i++) {
            // check is the object intersection with each collidable
            List<Point> points = collidableList.get(i).intersectionPoints(trajectory);
            for (int j = 0; j < points.size(); j++) {
                points.get(j).setRectangle(collidableList.get(i).getCollisionRectangle());
            }
            // add all the collision points to the array
            collisionPoints.addAll(points);
        }
        // if there are not collision points
        if (collisionPoints.size() == 0) {
            return null;
        }
        // save the distance between the first collision point to the line
        double distance = collisionPoints.get(index).distance(trajectory.start());
        for (int i = 1; i < collisionPoints.size(); i++) {
            // check the minimum distance each time
            if (collisionPoints.get(i).distance(trajectory.start()) < distance) {
                distance = collisionPoints.get(i).distance(trajectory.start());
                index = i;
            }
        }
        Point collision = collisionPoints.get(index);
        // check if collision point is paddle and add and return it
        if (collision.getRect().getPuddle()) {
            return new CollisionInfo(collision, new Paddle(collision.getRect()));
        } else {
            Block block = collision.getBlock();
            return new CollisionInfo(collision, block);
        }
    }
}