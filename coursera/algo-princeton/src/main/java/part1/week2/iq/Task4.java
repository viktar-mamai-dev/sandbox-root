package part1.week2.iq;

import edu.princeton.cs.algs4.Point2D;

/**
 * Given two arrays a[] and b[], each containing nn distinct 2D points in the plane, design a subquadratic algorithm
 * to count the number of points that are contained both in array a[] and array b[]
 */
public class Task4 {
    public int intersectionCount(Point2D[] a, Point2D[] b) {
        int count = 0;
        for (Point2D aItem : a) {
            for (Point2D bItem : b) {
                if (aItem.equals(bItem)) {
                    count++;
                }
            }
        }
        return count;
    }
}
