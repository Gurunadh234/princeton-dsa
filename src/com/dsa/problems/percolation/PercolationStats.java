package com.dsa.problems.percolation;

import java.security.SecureRandom;

public class PercolationStats {
    private final int grid;
    private final int trials;
    private final SecureRandom rand;
    private final double[] thresholds;

    public PercolationStats(int n, int trials) {
        if(n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n or trials less than or equal to zero");
        }
        this.grid = n;
        this.trials = trials;
        rand = new SecureRandom();
        this.thresholds = new double[trials];
    }

    public double mean() {
        double thresholdSum = 0;
        for(double threshold : this.getThresholds()) {
            thresholdSum += threshold;
        }

        return thresholdSum/this.getTrials();
    }

    public double stddev() {
        double sumation = 0;
        double mean = mean();

        for(int trial=0; trial < this.getTrials(); trial++) {
            double diff = this.thresholds[trial] - mean;
            sumation += Math.pow(diff, 2);
        }
        return Math.sqrt(sumation / (this.getTrials() - 1 ));
    }

    public double confidenceLo() {
        return mean() - ( (1.96 * stddev()) / Math.sqrt(trials));
    }

    public double confidenceHi() {
        return mean() + ( (1.96 * stddev()) / Math.sqrt(trials));
    }

    public int getRandInt() {
        return this.getRand().nextInt(1, grid + 1);
    }

    public static void main(String[] args) {
        int grid = 5, trials = 5;
        PercolationStats stats = new PercolationStats(grid, trials);

        for(int trial=0; trial<stats.getTrials(); trial++) {
            System.out.printf("Trial: %d%n", trial+1);
            Percolation percolation = new Percolation(grid);
            while (!percolation.percolates()) {
                int row = stats.getRandInt();
                int col = stats.getRandInt();
                percolation.open(row, col);
            }
            double threshold = (double) percolation.numberOfOpenSites() / (stats.getGrid() * stats.getGrid());
            System.out.printf("Threshold: %f%n%n", threshold);
            stats.getThresholds()[trial] = threshold;
        }
    }

    public int getGrid() {
        return this.grid;
    }

    public int getTrials() {
        return this.trials;
    }

    public double[] getThresholds() {
        return this.thresholds;
    }

    public SecureRandom getRand() {
        return this.rand;
    }
}
