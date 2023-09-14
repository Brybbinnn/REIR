import edu.princeton.cs.algs4.StdStats;
public class PercolationStats {
    public PercolationStats(int N, int T){
        // perform T independent experiments on an N-by-N grid
        if (N <= 0 || T <= 0) {
        throw new IllegalArgumentException();
    }

    public double mean(){
        return 0;
    }

    public double stddev(){
        return 0;
    }
}