import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.Queue;

public class Fast {
    private Point[] points;
    private Queue<Queue<Point>> queue = new Queue<>();

    public Fast(Point[] points) {
        this.points = points;
        Arrays.sort(this.points);
    }

    public void find_segments() {
        Point initialPoint = points[0];
        double prevSlope = initialPoint.slopeTo(points[1]);
        int matchingSlopes = 0;

        for (int i = 1; i < points.length - 1; i++) {
            double slopeToCheck = initialPoint.slopeTo(points[i + 1]);
            if(slopeToCheck == prevSlope){
                matchingSlopes ++;
                else if(matchingSlopes >= 3){

                }
            }
        }
    }

    public Queue<Queue<Point>> segments() {
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
        for (Queue<Point> q : fast.queue) {
            out.printf("\n");
            for (Point p : q) {
                out.printf(p.toString() + ", ");
            }
        }
    }
}