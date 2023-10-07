import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.Queue;

public class Fast {
    private Point[] points;
    private Queue<Queue<Point>> lineQueue = new Queue<>();

    public Fast(Point[] points) {
        this.points = points;
        Arrays.sort(this.points);
        this.find_segments();
    }

    public void find_segments() {
        int n = points.length;

        for (int i = 0; i < n; i++) {
            Arrays.sort(this.points, i, n);
            Point p = points[i];

            // Sort points by SLOPE_ORDER with p
            Arrays.sort(this.points, i, n, p.SLOPE_ORDER);

            for (int j = i; j < n - 2; j++) {
                if (isLine(p, points[j], points[j + 1], points[j + 2])) {
                    Arrays.sort(this.points, j, j + 3);

                    Queue<Point> segmentInnerQueue = new Queue<>();
                    segmentInnerQueue.enqueue(p);
                    for (int k = 0; k < 3; k++) {
                        segmentInnerQueue.enqueue(this.points[(j + k)]);
                    }
                    lineQueue.enqueue(segmentInnerQueue);
                }
            }
        }
    }

    public boolean isLine(Point p, Point q, Point r, Point s) {
        // Checks if the given slopes form a line
        if (p.slopeTo(q) == p.slopeTo(r) && p.slopeTo(q) == p.slopeTo(s)) {
            return true;
        }
        return false;
    }

    public Queue<Queue<Point>> segments() {
        return this.lineQueue;
    }

    public int numberOfSegments() {
        return lineQueue.size();
    }

    public static void main(String[] args) {
        In in = new In();
        Out out = new Out();
        int n = in.readInt();

        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt(), y = in.readInt();
            points[i] = new Point(x, y);

        }
        Fast fast = new Fast(points);
        out.printf("Fast method\n");
        for (Queue<Point> q : fast.lineQueue) {
            out.printf("\n");
            for (Point p : q) {
                out.printf(p.toString() + ", ");
            }
        }
    }
}