import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.Queue;

public class Brute {
    private Point[] points;
    private Queue<Queue<Point>> queue = new Queue<>();

    public Brute(Point[] points) {
        this.points = points;
        Arrays.sort(this.points);
        segment_points();
    }

    public int numberOfSegments() {
        return queue.size();
    }

    public boolean isLine(Point p1, Point p2, Point p3, Point p4) {
        if ((p1.slopeTo(p2) == p1.slopeTo(p3)) && (p1.slopeTo(p2) == p1.slopeTo(p4))) {
            return true;
        }
        return false;
    }

    public Queue<Queue<Point>> segment() {
        return queue;
    }

    public void segment_points() {
        for (int i = 0; i < points.length - 3; i++) {
            for (int j = i + 1; j < points.length - 2; j++) {
                for (int k = j + 1; k < points.length - 1; k++) {
                    for (int l = k + 1; l < points.length; l++) {
                        if (isLine(points[i], points[j], points[k], points[l]) == true) {
                            Queue<Point> resultSegments = new Queue<>();
                            resultSegments.enqueue(points[i]);
                            resultSegments.enqueue(points[j]);
                            resultSegments.enqueue(points[k]);
                            resultSegments.enqueue(points[l]);
                            // Queue the resulting segment queue;
                            queue.enqueue(resultSegments);
                        }
                    }
                }
            }
        }
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
        Brute brute = new Brute(points);
        out.printf("Brute method...\n");
        for (Queue<Point> q : brute.queue) {
            out.printf("\n");
            for (Point p : q) {
                out.printf(p.toString() + ", ");
            }
        }
    }
}
