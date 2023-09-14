// import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    public static int N;
    public static int T;
    public static int x;
    public static int y;

    public PercolationStats(int N, int T){
        // perform T independent experiments on an N-by-N grid
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        Percolation p = new Percolation(N);
        while (p.percolates() == false)
            for (int i = 0; i < T; i++) {
                x = StdRandom.uniformInt(N-1);
                y = StdRandom.uniformInt(N-1);
                p.open(x, y);
            } 
        System.out.println(p.numberOfOpenSites());
    }

    public double mean(){
        return 0;
    }

    public double stddev(){
        return 0;
    }

    public static void main(String[] args) {
        PercolationStats per = new PercolationStats(3, 10000);
    }
}