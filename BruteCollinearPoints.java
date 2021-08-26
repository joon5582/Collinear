/* *****************************************************************************
 *  Name: Junwoo Lee
 *  Date: 6/3/2020
 *  Description: https://coursera.cs.princeton.edu/algs4/assignments/collinear/specification.php
 *  I have done all the coding by myself
 **************************************************************************** */
import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private int n;
    private int linenum;
    private ArrayList<LineSegment> plist;


    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        linenum = 0;
        plist = new ArrayList<>();
        n = points.length;
        for (int i = 0; i < n; i++) {
            if (points[i] == null) throw new java.lang.IllegalArgumentException();
            for (int j = i + 1; j < n; j++) {
                if (points[j] == null || points[i].compareTo(points[j]) == 0) {
                    throw new java.lang.IllegalArgumentException();
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (points[i].slopeOrder().compare(points[j], points[k]) == 0) {
                        for (int l = k + 1; l < n; l++) {
                            if (points[i].slopeOrder().compare(points[j], points[l]) == 0) {
                                Point[] a = new Point[4];
                                a[0] = points[i];
                                a[1] = points[j];
                                a[2] = points[k];
                                a[3] = points[l];
                                Arrays.sort(a);

                                plist.add(new LineSegment(a[0], a[3]));
                                linenum += 1;

                            }
                        }
                    }


                }
            }
        }

    }// finds all line segments containing 4 points

    public int numberOfSegments() {
        return plist.size();

    }// the number of line segments

    public LineSegment[] segments() {
        LineSegment[] a = new LineSegment[numberOfSegments()];
        plist.toArray(a);
        return a;

    }// the line segments
}
