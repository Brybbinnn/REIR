import java.util.Arrays;

import edu.princeton.cs.algs4.Queue;

public class Fast {
    private Point[] points;
    private Queue<Queue<Double>> queue = new Queue<>();

    public Fast(Point[] points) {
        this.points = points;
        Arrays.sort(this.points);
    }

    public Queue<Double> segments() {
        for (Point p : this.points) {
            Queue<Double> segment = new Queue<>();
            for (Point q : this.points) {
                if (p != q) {
                    // Need to sort segments somehow
                    segment.enqueue(p.slopeTo(q));
                }
                // Arrays.sort(segment)
            }
        }
    }
}