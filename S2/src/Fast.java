import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.Queue;

public class Fast {
    private Point[] points;
    private Queue<Queue<Double>> queue = new Queue<>();

    public Fast(Point[] points) {
        this.points = points;
        Arrays.sort(this.points);
    }

    public void find_segments() {
        for (Point p : this.points) {
            Queue<Double> segment = new Queue<>();
            for (Point q : this.points) {
                if (p != q) {
                    // Need to sort segments somehow
                    segment.enqueue(p.slopeTo(q));
                }
                // Arrays.sort(segment)
            }
            queue.enqueue(segment);
        }
    }

    public Queue<Queue<Double>> segments() {
        return this.queue;
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
        for (Queue<Double> q : fast.queue) {
            out.printf("\n");
            for (Double p : q) {
                out.printf(p.toString() + ", ");
            }
        }
    }
}