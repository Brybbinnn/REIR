// import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    public static int N;
    public static int T;

    public PercolationStats(int N, int T){
        // perform T independent experiments on an N-by-N grid
        if (N <= 0 || T <= 0) {
        throw new IllegalArgumentException();
        }
        for (int i = 0; i < T; i++) {
            i = StdRandom.uniformInt(N);
            
        }

        
    }

    public double mean(){
        return 0;
    }

    public double stddev(){
        return 0;
    }

    public static void main(String[] args) {
        PercolationStats per = new PercolationStats(10, 10);
    }
}