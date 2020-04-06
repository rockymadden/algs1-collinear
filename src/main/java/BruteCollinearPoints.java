import java.util.Arrays;

import edu.princeton.cs.algs4.MergeX;

public class BruteCollinearPoints {
    private final LineSegment[] lineSegments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Argument 'points' cannot be null.");
        }

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException("Points cannot be null.");
            }
        }

        MergeX.sort(points);
        Point last = null;

        for (int i = 0; i < points.length; i++) {
            if (last != null && points[i].compareTo(last) == 0) {
                throw new IllegalArgumentException("Points cannot be duplicated.");
            }

            last = points[i];
        }

        int idx = 0;
        LineSegment[] segs = new LineSegment[points.length];

        for (int a = 0; a < points.length; a++) {
            for (int b = a + 1; b < points.length; b++) {
                for (int c = b + 1; c < points.length; c++) {
                    for (int d = c + 1; d < points.length; d++) {
                        double atob = points[a].slopeTo(points[b]);
                        double btoc = points[b].slopeTo(points[c]);
                        double ctod = points[c].slopeTo(points[d]);

                        if (atob == btoc && btoc == ctod) {
                            Point[] pnts = {points[a], points[b], points[c], points[d]};
                            MergeX.sort(pnts);
                            segs[idx++] = new LineSegment(pnts[0], pnts[3]);
                        }
                    }
                }
            }
        }

        lineSegments = new LineSegment[idx];

        for (int i = 0; i < idx; i++) {
            lineSegments[i] = segs[i];
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return lineSegments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return Arrays.copyOf(lineSegments, lineSegments.length);
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        Point[] points = {
            new Point(1, 1),
            new Point(2, 2),
            new Point(3, 3),
            new Point(4, 4),
            new Point(1, 0),
            new Point(2, 0),
            new Point(3, 0),
            new Point(4, 0),
            new Point(5, 6),
            new Point(7, 3),
            new Point(8, 3),
            new Point(9, 3)
        };

        BruteCollinearPoints bcp = new BruteCollinearPoints(points);

        System.out.println(Arrays.toString(bcp.segments()));
        System.out.println(bcp.numberOfSegments());
    }
 }
