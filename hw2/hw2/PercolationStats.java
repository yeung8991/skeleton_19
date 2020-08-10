package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] thresholds;
    private double stddev;
    private double mean;
    private int expTimes;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N and T must greater than 0.");
        }

        expTimes = T;
        thresholds = new double[T];

        for (int i = 0; i < expTimes; i++) {
            Percolation p = pf.make(N);
            for (int j = 1, row, col; j <= N * N; j++) {
                do {
                    row = StdRandom.uniform(0, N);
                    col = StdRandom.uniform(0, N);
                } while (p.isOpen(row, col));
                p.open(row, col);

                if (p.percolates()) {
                    thresholds[i] = j / N * N;
                    break;
                }
            }
        }
    }

    public double mean() {
        mean = StdStats.mean(thresholds);
        return mean;
    }

    public double stddev() {
        stddev = StdStats.stddev(thresholds);
        return stddev;
    }

    public double confidenceLow() {
        return mean - ((1.96 * stddev) / Math.sqrt(expTimes));
    }

    public double confidenceHigh() {
        return mean + ((1.96 * stddev) / Math.sqrt(expTimes));
    }
}
