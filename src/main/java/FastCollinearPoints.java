import java.util.Arrays;

import edu.princeton.cs.algs4.MergeX;

public class FastCollinearPoints {
    private final LineSegment[] lineSegments;

    // finds all line segments containing 4 points
    public FastCollinearPoints(Point[] points) {
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
        LineSegment[] segs = new LineSegment[points.length * points.length];

        for (int i = 0; i < points.length; i++) {
            MergeX.sort(points);

            Point pnt = points[i];
            int start = -1;
            int end = -1;
            double tmpSlope;
            double slope = Double.POSITIVE_INFINITY;

            MergeX.sort(points, pnt.slopeOrder());

            for (int j = 0; j < points.length; j++) {
                tmpSlope = pnt.slopeTo(points[j]);

                if (tmpSlope != slope) {
                    if (start != -1 && end != -1 && end - start >= 2 && isMin(points, start, end)) {
                        segs[idx++] = new LineSegment(pnt, points[end]);
                    }

                    start = j;
                    end = -1;
                    slope = tmpSlope;
                } else {
                    end = j;
                }
            }

            if (start != -1 && end != -1 && end - start >= 2 && isMin(points, start, end)) {
                segs[idx++] = new LineSegment(pnt, points[end]);
            }
        }

        lineSegments = new LineSegment[idx];

        for (int i = 0; i < idx; i++) {
            lineSegments[i] = segs[i];
        }
    }

    private static boolean isMin(Point[] points, int start, int end) {
        Point pnt = points[0];

        for (int i = start; i <= end; i++) {
            if (pnt.compareTo(points[i]) >= 0) {
                return false;
            }
        }

        return true;
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
            new Point(5, 5),
            new Point(1, 0),
            new Point(2, 0),
            new Point(3, 0),
            new Point(4, 0),
            new Point(5, 6),
            new Point(7, 3),
            new Point(8, 3),
            new Point(9, 3)
        };

        FastCollinearPoints fcp = new FastCollinearPoints(points);

        System.out.println(Arrays.toString(fcp.segments()));
        System.out.println(fcp.numberOfSegments());
    }
 }
