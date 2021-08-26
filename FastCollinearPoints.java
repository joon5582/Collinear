/* *****************************************************************************
 *  Name: Junwoo Lee
 *  Date: 6/3/2020
 *  Description: https://coursera.cs.princeton.edu/algs4/assignments/collinear/specification.php
 *  I have done all the coding by myself
 **************************************************************************** */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FastCollinearPoints {
    private int n;
    private ArrayList<LineSegment> plist;
    private Point[] clone1;
    private Point[] points1;


    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        plist = new ArrayList<>();
        ArrayList<Point> templist = new ArrayList<>();
        n = points.length;
        for (int i = 0; i < n; i++) {
            if (points[i] == null) throw new java.lang.IllegalArgumentException();
            for (int j = i + 1; j < n; j++) {
                if (points[j] == null || points[i].compareTo(points[j]) == 0) {
                    throw new java.lang.IllegalArgumentException();
                }
            }
        }


        clone1 = new Point[n];
        for (int i = 0; i < n; i++) {
            clone1[i] = points[i];
        }
        points1 = new Point[n];
        for (int i = 0; i < n; i++) {
            points1[i] = points[i];
        }


        for (int i = 0; i < n; i++) {
            for (int s = 0; s < n; s++) {
                points1[s] = clone1[s];
            }
            Point temp1 = points1[0];
            points1[0] = points1[i];
            points1[i] = temp1;

            Arrays.sort(points1, points1[0].slopeOrder());
            

            int count = 0;
            for (int j = 1; j < n; j++) {

                if (Double.isInfinite(points1[0].slopeTo(points1[j]))) {
                    boolean isdup = false;

                    ArrayList<Point> temp = new ArrayList<>();
                    for (int k = j; k < n; k++) {
                        for (int d = 0; d < i; d++) {
                            if (points1[k] == clone1[d]) {
                                isdup = true;
                                break;
                            }

                        }

                        if (isdup) break;
                        temp.add(points1[k]);
                    }
                    if (!isdup) {
                        temp.add(points1[0]);
                        if (temp.size() <= 3)
                            break;

                        /*
                        for (int a = 0; a < arr1.length; a++) {
                            System.out.println(arr1[a].toString());
                        }
                        System.out.println("line");
                        */
                        //Collections.min(temp,Point::compareTo);
                        //Collections.max(temp, Point::compareTo );
                        plist.add(new LineSegment(Collections.max(temp, Point::compareTo), Collections.min(temp, Point::compareTo)));
                    }
                    break;

                } else if (j == n - 1) {
                    if (count >= 2) {
                        count++;
                        boolean isdup = false;
                        ArrayList<Point> temp = new ArrayList<>();
                        for (int k = j; count > 0; k--, count--) {
                            for (int d = 0; d < i; d++) {
                                if (points1[k] == clone1[d]) {
                                    isdup = true;
                                    break;
                                }

                            }

                            if (isdup) break;
                            temp.add(points1[k]);
                        }
                        if (!isdup) {
                            temp.add(points1[0]);

                            plist.add(new LineSegment(Collections.max(temp, Point::compareTo), Collections.min(temp, Point::compareTo)));
                        }
                        count = 0;
                    } else break;
                } else if (points1[0].slopeTo(points1[j]) == points1[0].slopeTo(points1[j + 1])) {
                    count++;
                } else if (count >= 2) {
                    count++;
                    boolean isdup = false;
                    ArrayList<Point> temp = new ArrayList<>();
                    for (int k = j; count > 0; k--, count--) {
                        for (int d = 0; d < i; d++) {
                            if (points1[k] == clone1[d]) {
                                isdup = true;
                                break;
                            }

                        }

                        if (isdup) break;
                        temp.add(points1[k]);
                    }
                    if (!isdup) {
                        temp.add(points1[0]);

                        plist.add(new LineSegment(Collections.max(temp, Point::compareTo), Collections.min(temp, Point::compareTo)));
                    }
                    count = 0;
                } else count = 0;


            }
        }

    }


// finds all line segments containing 4 points

    public int numberOfSegments() {
        return plist.size();

    }// the number of line segments

    public LineSegment[] segments() {
        LineSegment[] a = new LineSegment[numberOfSegments()];
        plist.toArray(a);
        return a;

    }// the line segments
}
