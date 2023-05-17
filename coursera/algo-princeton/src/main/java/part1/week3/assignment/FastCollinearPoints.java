package part1.week3.assignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FastCollinearPoints {
  private final Point[] points;
  private int numberOfSegments = 0;

  /** finds all line segments containing 4 points */
  public FastCollinearPoints(Point[] points) {
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

  /** the number of line segments */
  public int numberOfSegments() {
    return numberOfSegments;
  }

  public LineSegment[] segments() {
    int n = points.length;
    Arrays.sort(points);

    // System.out.println("Fast algorithm");
    List<LineSegment> lineSegmentList = new ArrayList<LineSegment>();

    Point[] sortedPoints = new Point[n];
    System.arraycopy(points, 0, sortedPoints, 0, n);
    List<Point> usedMins = new ArrayList<>();
    List<Double> usedSlopes = new ArrayList<>();
    for (Point point : points) {
      Arrays.sort(sortedPoints, point.slopeOrder());
      Point p = sortedPoints[0];
      for (int i = 1; i < n - 2; i++) {
        List<Point> collinearList = new ArrayList<>();
        collinearList.add(p);
        collinearList.add(sortedPoints[i]);
        double slope = p.slopeTo(sortedPoints[i]);

        while (i < n - 1 && slopeEqual(sortedPoints, 0, i, i + 1)) {
          collinearList.add(sortedPoints[i + 1]);
          i++;
        }
        if (collinearList.size() >= 4) { // we found at least 4 points
          Point maxPoint = Collections.max(collinearList);
          Point minPoint = Collections.min(collinearList);

          boolean usedCase = false;
          for (int pi = 0; pi < usedMins.size(); pi++) {
            if (usedMins.get(pi).compareTo(minPoint) == 0 && usedSlopes.get(pi).equals(slope)) {
              usedCase = true;
              break;
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
          // System.out.println(lineSegment);
        }
      }
    }

    // System.out.println("Fast algorithm. Segments: " + numberOfSegments);
    LineSegment[] lineArray = new LineSegment[lineSegmentList.size()];
    return lineSegmentList.toArray(lineArray);
  }

  private boolean slopeEqual(Point[] points, int k, int i2, int i3) {
    return Double.compare(points[k].slopeTo(points[i2]), points[i2].slopeTo(points[i3])) == 0;
  }
}
