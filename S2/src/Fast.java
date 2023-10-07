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
        find_segments();
    }

    public void find_segments() {
        Point initialPoint = points[0];
        Queue<Point> line = new Queue<>();
        double prevSlope = initialPoint.slopeTo(points[1]);
        int matchingSlopes = 0;

        for (int i = 1; i < points.length - 1; i++) {
            double slopeToCheck = initialPoint.slopeTo(points[i + 1]);
            if (slopeToCheck == prevSlope) {
                matchingSlopes++;
            } else {
                if (matchingSlopes == 3) {
                    lineQueue.enqueue(line);
                }
                matchingSlopes = 1;
                line.dequeue();
                initialPoint = line.dequeue();
                line = new Queue<>();
            }
            prevSlope = slopeToCheck;
        }
    }

    public Queue<Queue<Point>> segments() {
        return this.lineQueue;
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
        out.printf("Brute method...\n");
        for (Queue<Point> q : fast.lineQueue) {
            out.printf("\n");
            for (Point p : q) {
                out.printf(p.toString() + ", ");
            }
        }
    }
}