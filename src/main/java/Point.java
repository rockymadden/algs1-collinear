
/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {
    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        int y0 = this.y - that.y;
        int x0 = this.x - that.x;

        if (x0 == 0 && y0 == 0) {
            return Double.NEGATIVE_INFINITY;
        } else if (y0 == 0) {
            return 0d;
        } else if (x0 == 0) {
            return Double.POSITIVE_INFINITY;
        }

        return y0 * 1d / x0;
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point that) {
        if (this.y < that.y) {
            return -1;
        } else if (this.y > that.y) {
            return 1;
        } else if (this.x < that.x) {
            return -1;
        } else if (this.x > that.x) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Compares two points by the slope they make with this point. The slope is defined as in the
     * slopeTo() method. The slopeOrder() method should return a comparator that compares its two
     * argument points by the slopes they make with the invoking point (x0, y0). Formally, the
     * point (x1, y1) is less than the point (x2, y2) if and only if the slope (y1 − y0) / (x1 − x0)
     * is less than the slope (y2 − y0) / (x2 − x0). Treat horizontal, vertical, and degenerate
     * line segments as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        return (a, b) -> {
            double fst = this.slopeTo(a);
            double snd = this.slopeTo(b);

            return Double.compare(fst, snd);
        };
    }


    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        System.out.println(new Point(335, 0).slopeTo(new Point(335, 446)));
        // Point p01 = new Point(0, 1);
        // Point p10 = new Point(1, 0);
        // Point p11 = new Point(1, 1);
        // Point p02 = new Point(0, 2);
        // Point p20 = new Point(2, 0);
        // Point p22 = new Point(2, 2);
        // Point p03 = new Point(0, 3);
        // Point p30 = new Point(3, 0);
        // Point p33 = new Point(3, 3);

        // System.out.println("p11.compareTo(p11): " + p11.compareTo(p11)); // 0
        // System.out.println("p11.compareTo(p22): " + p11.compareTo(p22)); // -1
        // System.out.println("p11.compareTo(p33): " + p11.compareTo(p33)); // -1
        // System.out.println("p22.compareTo(p33): " + p22.compareTo(p33)); // -1
        // System.out.println("p33.compareTo(p22): " + p33.compareTo(p22)); // 1
        // System.out.println("p33.compareTo(p11): " + p33.compareTo(p11)); // 1
        // System.out.println("p10.compareTo(p01): " + p10.compareTo(p01)); // -1
        // System.out.println("p22.compareTo(p03): " + p22.compareTo(p03)); // -1
        // System.out.println("p22.compareTo(p30): " + p22.compareTo(p30)); // 1
        // System.out.println();
        // System.out.println("p11.slopeTo(p11): " + p11.slopeTo(p11)); // - Infinity
        // System.out.println("p01.slopeTo(p02): " + p01.slopeTo(p02)); // 0d
        // System.out.println("p10.slopeTo(p20): " + p10.slopeTo(p20)); // Infinity
        // System.out.println("p11.slopeTo(p22): " + p11.slopeTo(p22)); // 1d
        // System.out.println("p11.slopeTo(p33): " + p11.slopeTo(p33)); // 1d
        // System.out.println();
        // System.out.println("p22.slopeOrder().compare(p11, p33): " + p22.slopeOrder().compare(p11, p33)); // 0
        // System.out.println("p02.slopeOrder().compare(p03, p33): " + p02.slopeOrder().compare(p03, p33)); // -1
        // System.out.println("p02.slopeOrder().compare(p33, p03): " + p02.slopeOrder().compare(p33, p03)); // 1
    }
}
