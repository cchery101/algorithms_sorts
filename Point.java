/*************************************************************************
 * Name:         Simon Bowly
 * Email:        simon.bowly@gmail.com
 *
 * Compilation:  javac-algs4 Point.java
 * Execution:    java-algs4 Point
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new BySlope();

    private class BySlope implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            double slope1 = slopeTo(p1);
            double slope2 = slopeTo(p2);
            if (slope1 < slope2)        return -1;
            else if (slope1 > slope2)   return 1;
            else                        return 0;
        }
    }

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        return (1.0 * (that.y - y)) / (1.0 * (that.x - x));
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (y < that.y)             return -1;
        else if (y == that.y) {
            if (x < that.x)         return -1;
            else if (x == that.x)   return 0;
            else                    return 1;
        }
        else                        return 1;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(3, 2);
        Point p3 = new Point(1, 2);
        Point p4 = new Point(2, -1);
        System.out.println(p1.slopeTo(p2));
        System.out.println(p2.slopeTo(p3));
        System.out.println(p2.slopeTo(p1));
        System.out.println(p1.slopeTo(p4));
        System.out.println(p1.compareTo(p2));
        System.out.println(p2.compareTo(p1));
        System.out.println(p3.compareTo(p2));
        System.out.println(p2.compareTo(p3));
        System.out.println(p1.compareTo(p1));
        System.out.println(p1.SLOPE_ORDER.compare(p2, p3));
        System.out.println(p1.SLOPE_ORDER.compare(p3, p2));
        System.out.println(p1.SLOPE_ORDER.compare(p4, p4));
    }
}
