package calculator.impl;

import java.util.concurrent.RecursiveTask;

public class MatrixDeterminantTask extends RecursiveTask<Double> {
    private final double[][] matrix;

    public MatrixDeterminantTask(double[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    protected Double compute() {
        int size = matrix.length;

        if (size == 1) {
            return matrix[0][0];
        }

        if (size == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        double determinant = 0;
        MatrixDeterminantTask[] tasks = new MatrixDeterminantTask[size];

        for (int i = 0; i < size; i++) {
            double[][] submatrix = getSubMatrix(matrix, 0, i);
            tasks[i] = new MatrixDeterminantTask(submatrix);
            tasks[i].fork();
        }

        for (int i = 0; i < size; i++) {
            double sign = (i % 2 == 0) ? 1 : -1;
            determinant += sign * matrix[0][i] * tasks[i].join();
        }

        return determinant;
    }

    private double[][] getSubMatrix(double[][] matrix, int rowToRemove, int colToRemove) {
        int size = matrix.length - 1;
        double[][] subMatrix = new double[size][size];

        for (int i = 0, ii = 0; i < matrix.length; i++) {
            if (i == rowToRemove) {
                continue;
            }

            for (int j = 0, jj = 0; j < matrix[i].length; j++) {
                if (j == colToRemove) {
                    continue;
                }

                subMatrix[ii][jj] = matrix[i][j];
                jj++;
            }

            ii++;
        }

        return subMatrix;
    }
}
