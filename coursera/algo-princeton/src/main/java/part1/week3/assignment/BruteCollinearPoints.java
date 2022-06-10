package part1.week3.assignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BruteCollinearPoints {
    private final Point[] points;
    private int numberOfSegments = 0;

    /**
     * finds all line segments containing 4 points
     */
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Array of points is null");
        }
        int n = points.length;
        this.points = new Point[n];
        System.arraycopy(points, 0, this.points, 0, n);

        assertNoNullPoint();
        assertNoDuplicates();
    }

    private void assertNoNullPoint() {
        for (Point p : points) {
            if (p == null) {
                throw new IllegalArgumentException("Null element in array");
            }
        }
    }

    private void assertNoDuplicates() {
        for (int i = 0; i < points.length; i++) {
            Point p = points[i];
            for (int j = i + 1; j < points.length; j++) {
                if (p.compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException("Duplicated point");
                }
            }
        }
    }

    /**
     * the number of line segments
     */
    public int numberOfSegments() {
        return numberOfSegments;
    }

    public LineSegment[] segments() {
        //System.out.println("Brute algorithm");
        int n = points.length;
        Arrays.sort(points);
        List<LineSegment> lineSegmentList = new ArrayList<LineSegment>();
        List<Double> usedSlopes = new ArrayList<>();
        List<Point> usedMins = new ArrayList<>();
        for (int i1 = 0; i1 < n; i1++) {
            for (int i2 = i1 + 1; i2 < n; i2++) {
                boolean flag = true;
                double slope = points[i1].slopeTo(points[i2]);
                for (int i3 = i2 + 1; i3 < n && flag; i3++) {
                    if (slopeEqual(i1, i2, i3)) {
                        for (int i4 = i3 + 1; i4 < n && flag; i4++) {
                            if (slopeEqual(i2, i3, i4)) { // found at least 4 collinear

                                flag = false;
                                List<Point> collinearList = new ArrayList<>(Arrays.asList(points[i1], points[i2], points[i3], points[i4]));
                                while (++i4 < n) {
                                    if (slopeEqual(i2, i3, i4)) {
                                        collinearList.add(points[i4]);
                                    }
                                }

                                Point maxPoint = Collections.max(collinearList);
                                Point minPoint = Collections.min(collinearList);

                                boolean usedCase = false;
                                for (int pi = 0; pi < usedMins.size(); pi++) {
                                    if ((usedMins.get(pi).slopeTo(minPoint) == slope
                                            || usedMins.get(pi).compareTo(minPoint) == 0)
                                            && usedSlopes.get(pi).equals(slope)) {
                                        usedCase = true;
                                    }
                                }
                                if (usedCase) {
                                    break;
                                }

                                usedSlopes.add(slope);
                                usedMins.add(minPoint);
                                LineSegment lineSegment = new LineSegment(minPoint, maxPoint);
                                lineSegmentList.add(lineSegment);
                                numberOfSegments++;
                                //System.out.println(lineSegment);
                            }
                        }
                    }
                }

            }
        }
        //System.out.println("Brute algorithm. Segments: " + numberOfSegments);
        LineSegment[] lineArray = new LineSegment[lineSegmentList.size()];
        return lineSegmentList.toArray(lineArray);
    }

    private boolean slopeEqual(int i1, int i2, int i3) {
        return Double.compare(points[i1].slopeTo(points[i2]), points[i2].slopeTo(points[i3])) == 0;
    }
}
