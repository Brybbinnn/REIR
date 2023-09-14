// import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    public static int N;
    public static int T;
    public static int x;
    public static int y;
    public static int tests[];

    public PercolationStats(int N, int T){
        // perform T independent experiments on an N-by-N grid
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
    }

    public static void test() {
        tests = new int[T];
        for (int i = 0; i < T; i++) {
            testPercolation(i);
        }
    }   
    public static void testPercolation(int index) {
        Percolation p = new Percolation(N);
            for (int i = 0; i < T; i++) {
                    x = StdRandom.uniformInt(N-1);
                    y = StdRandom.uniformInt(N-1);
                    p.open(x, y);
            if (p.percolates() == true) {
                tests[index] = p.numberOfOpenSites();
            }
        }
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