package basicobjects;
import gameobjects.Rectangle;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Or Nasri
 * @version 3.0
 * @since 30.05.2021
 * one line
 */
public class Line {
    private final Point start;
    private final Point end;
    private final Point realStart;
    private final Point realEnd;
    private final double x1;
    private final double x2;
    private final double y1;
    private final double y2;
    private final double m;
    private final double n;
    private final boolean isStraightLine;
    private final boolean isPoint;
    /**
     * Constructor.
     * @param start the start point of the line
     * @param end the end point of the line
     */
    public Line(Point start, Point end) {
        // make the low point to be the start
        if (start.getY() > end.getY()) {
            this.end = start;
            this.start = end;
        } else if (start.getY() < end.getY()) {
            this.end = end;
            this.start = start;
        } else {
            if (start.getX() >= end.getX()) {
                this.end = start;
                this.start = end;
            } else {
                this.end = end;
                this.start = start;
            }
        }
        this.realEnd = end;
        this.realStart = start;
        this.x1 = start.getX();
        this.x2 = end.getX();
        this.y1 = start.getY();
        this.y2 = end.getY();
        // check if is point
        this.isPoint = x1 == x2 && y1 == y2;
        // check if is straight line
        this.isStraightLine = x1 == x2;
        // calculate m - calculate the 'm' in y=mx+n.
        if (x1 == x2) {
            this.m = 0;
        } else {
            this.m =  ((y1 - y2) / (x1 - x2));

        }
        // calculate n - calculate the 'n' in y=mx+n.
        if (isStraightLine) {
             this.n = x1;
        } else if (this.m == 0) {
            this.n = y1;
        } else {
            this.n = (y1 - (this.m * x1));
        }
    }
    /**
     * Constructor.
     * @param x1 the x coordinate of the start point
     * @param y1 the y coordinate of the start point
     * @param x2 the x coordinate of the end point
     * @param y2 the y coordinate of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        if (y2 > y1) {
            this.start = new Point(x1, y1);
            this.end = new Point(x2, y2);
        } else if (y2 < y1) {
            this.start = new Point(x2, y2);
            this.end = new Point(x1, y1);
        } else {
            if (x1 >= x2) {
                this.start = new Point(x2, y2);
                this.end = new Point(x1, y1);
            } else {
                this.start = new Point(x1, y1);
                this.end = new Point(x2, y2);
            }
        }
        this.realStart = new Point(x1, y1);
        this.realEnd = new Point(x2, y2);
        this.x1 = start.getX();
        this.x2 = end.getX();
        this.y1 = start.getY();
        this.y2 = end.getY();
        // check if is point
        this.isPoint = x1 == x2 && y1 == y2;
        // check if is straight line
        this.isStraightLine = x1 == x2;
        // calculate m - calculate the 'm' in y=mx+n.
        if (x1 == x2) {
            this.m = 0;
        } else {
            this.m =  ((y1 - y2) / (x1 - x2));

        }
        // calculate n - calculate the 'n' in y=mx+n.
        if (isStraightLine) {
            this.n = x1;
        } else if (this.m == 0) {
            this.n = y1;
        } else {
            this.n = (y1 - (this.m * x1));
        }
    }

    /**
     * @return the length of the line
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * @return the point start of the line
     */
    public Point start() {
        return this.realStart;
    }
    /**
     * @return the point end of the line
     */
    public Point end() {
        return this.realEnd;
    }
    /**
     * check if two lines are equal.
     * @param other the line to check
     * @return if the lines are equal
     */
    public boolean equals(Line other) {
        return m == other.m && n == other.n;
    }

    /**
     * when 2 lines had the same m - check if they are intersecting.
     * @param other - the line to check
     * @return if the lines are intersecting
     */
    public boolean checkSameM(Line other) {
        // check if they had the same n
        if (this.n != other.n) {
            return false;
        }
        //if the lines are points, check if they are the same point
        if (other.isPoint && this.isPoint) {
            return other.start.equals(this.start);
        }
        //if one of the lines is a point
        if (!(other.isPoint) && this.isPoint) {
            return (this.y1 >= other.y2) && (this.y2 <= other.y2);
        }
        if (other.isPoint) {
            return (other.y2 >= this.y1) && (other.y2 <= this.y2);
        }
        // check if they are merge - other line is on the line but is not a point
        if ((other.start.equals(this.end))) {
            return true;
        }
        return other.end.equals(this.start);
    }

    /**
     * check if point is exist on the line.
     * @param toCheck the point to check if it on the line
     * @return if the point is on the line
     */
    public boolean isPointExist(Point toCheck) {
        double y = ((m * toCheck.getX()) + n);
        return toCheck.getX() >= Math.min(x1, x2) && toCheck.getX() <= Math.max(x1, x2)
                && (y >= Math.min(y1, y2) && y <= Math.max(y1, y2));
    }


    /**
     * check if there are intersection between two lines.
     * @param other the line to check the intersection
     * @return if the lines are intersecting
     */
    public boolean isIntersecting(Line other) {
        //there is an incline for both lines(same incline)
        if (this.m == other.m) {
            if (!this.isStraightLine && !other.isStraightLine) {
                return checkSameM(other);
            }
        }
        // 2 lines are straight lines
        if (this.isStraightLine == other.isStraightLine) {
            if (this.isStraightLine) {
                return checkSameM(other);
            }
        }
        // find the intersection point
        Point intersect;
        double intersectX = (other.n - n) / (m - other.m);
        double intersectY = other.m * intersectX + other.n;
        // check if one of the lines is vertical
        if (this.isStraightLine || other.isStraightLine) {
            // if this line is straight
            if (this.isStraightLine) {
                // update the intersection point value
                intersectX = this.start.getX();
                intersectY = (other.m * intersectX) + other.n;
                intersect = new Point(intersectX, intersectY);
                return (other.isPointExist(intersect))
                        && (intersectY <= Math.max(this.y1, this.y2) && intersectY >= Math.min(this.y1, this.y2));
            } else {
                // update the intersection point value
                intersectX = other.start.getX();
                intersectY = (this.m * intersectX) + this.n;
                intersect = new Point(intersectX, intersectY);
                return (this.isPointExist(intersect))
                        && (intersectY <= Math.max(other.y1, other.y2) && intersectY >= Math.min(other.y1, other.y2));
            }
        }
        intersect = new Point(intersectX, intersectY);
        return (this.isPointExist(intersect)) && (other.isPointExist(intersect));
    }


    /**
     * check the intersection point.
     * @param other the line to check the intersection
     * @return point intersection
     */
    public Point intersectionWith(Line other) {
        Point intersect;
        double intersectX;
        double intersectY;
        //when twe lines are straight or two lines had the same m
        if ((this.isStraightLine && other.isStraightLine)
            || ((this.m == other.m) && (!this.isStraightLine && !other.isStraightLine))) {
            // check the case that one of lines is a point
            if (other.isPoint && !this.isPoint) {
                return other.end;
            }
            if (!other.isPoint && this.isPoint) {
                return this.start;
            }
            //check if the lines continue each other
            if (this.start.equals(other.end)) {
                return other.end;
            }
            if (other.start.equals(this.end)) {
                return other.start;
            }
            intersectX = this.start.getX();
            if (Math.min(other.start.getY(), other.end.getY()) == Math.max(this.start.getY(), this.end.getY())) {
                intersect = new Point(intersectX, Math.max(this.start.getY(), this.end.getY()));
                return intersect;
            }
            if (Math.max(this.start.getY(), this.end.getY()) == Math.max(other.start.getY(), other.end.getY())) {
                intersect = new Point(intersectX,  Math.min(this.start.getY(), this.end.getY()));
                return intersect;
            }
            return null;
        }
        // check the case if one of the line is straight
        if (this.isStraightLine || other.isStraightLine) {
            // calculate the intersection point of straight line
            if (this.isStraightLine) {
                intersectX = this.start.getX();
                intersectY = (other.m * intersectX) + other.n;
            } else {
                intersectX = other.start.getX();
                intersectY = (this.m * intersectX) + this.n;
            }
            intersect = new Point(intersectX, intersectY);
            return intersect;
        }
        // calculate the intersection point
        intersectX = (other.n - this.n) / (this.m - other.m);
        intersect = new Point(intersectX,  (this.m * intersectX) + this.n);
        return intersect;
    }

    /**
     *  If this line does not intersect with the rectangle, return null.
     *  Otherwise, return the closest intersection point to the start of the line.
     * @param rect the rectangle to check the closet intersection
     * @return point that closet intersection to start line
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // create array of intersection points
        List<Point> intersectionPoints = new ArrayList<>();
        // check intersecting with each line of the rectangle
        if (rect.getUpLine().isIntersecting(this)) {
            // if there is intersection point add it to the array of points
            if (rect.getUpLine().intersectionWith(this) != null) {
                intersectionPoints.add(rect.getUpLine().intersectionWith(this));
            }
        }
        if (rect.getDownLine().isIntersecting(this)) {
            if (rect.getDownLine().intersectionWith(this) != null) {
                intersectionPoints.add(rect.getDownLine().intersectionWith(this));
            }
        }
        if (rect.getLeftLine().isIntersecting(this)) {
            if (rect.getLeftLine().intersectionWith(this) != null) {
                intersectionPoints.add(rect.getLeftLine().intersectionWith(this));
            }
        }
        if (rect.getRightLine().isIntersecting(this)) {
            if (rect.getRightLine().intersectionWith(this) != null) {
                intersectionPoints.add(rect.getRightLine().intersectionWith(this));
            }
        }
        // if not found ant intersection point return null
        if (intersectionPoints.size() == 0) {
            return null;
        }
        int index = 0;
        // check which point is closet to the line of the rectangle
        double distance = intersectionPoints.get(index).distance(this.start());
        for (int i = 1; i < intersectionPoints.size(); i++) {
            if (intersectionPoints.get(i).distance(this.start()) < distance) {
                distance = intersectionPoints.get(i).distance(this.start());
                index = i;
            }
        }
        return intersectionPoints.get(index);
    }
}
