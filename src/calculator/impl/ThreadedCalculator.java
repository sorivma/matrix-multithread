package calculator.impl;

import calculator.DeterminantCalculator;

import java.util.concurrent.ForkJoinPool;

public class ThreadedCalculator implements DeterminantCalculator {
    private final double[][] matrix;
    private final int parallelism;

    public ThreadedCalculator(double[][] matrix, int parallelism) {
        this.matrix = matrix;
        this.parallelism = parallelism;
    }

    @Override
    public double calculateDeterminant() {
        if (matrix.length != matrix[0].length) {
            throw new IllegalArgumentException("Matrix must be square.");
        }

        ForkJoinPool forkJoinPool = new ForkJoinPool(parallelism);

        return forkJoinPool.invoke(new MatrixDeterminantTask(matrix));
    }
}
