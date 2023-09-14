// import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    public static int size;
    public static int iter;
    public static int x;
    public static int y;
    public static int tests[];
    public static int counter;


    public PercolationStats(int N, int T){
        // perform T independent experiments on an N-by-N grid
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        iter = T;
        size = N;
        tests = new int[T];
        for (int i = 0; i < T; i++) {
            testPercolation(i);
            System.out.println(tests[i]);
        }

    }   
    public static void testPercolation(int index) {
        Percolation p = new Percolation(size);
        counter = 0;
        while (p.percolates() == false) {
            x = StdRandom.uniformInt(size);
            y = StdRandom.uniformInt(size);
            p.open(x, y);
            counter++;
        }
        tests[index] = p.numberOfOpenSites();
    }
    

    public double mean(){
        return 0;
    }

    public double stddev(){
        return 0;
    }

    public static void main(String[] args) {
        PercolationStats per = new PercolationStats(4, 20);
    }
}