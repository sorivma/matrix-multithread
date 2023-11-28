package calculator.impl;

import calculator.DeterminantCalculator;

public class Calculator implements DeterminantCalculator {
    private final double[][] matrix;

    public Calculator(double[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public double calculateDeterminant() {
        if (matrix.length != matrix[0].length) {
            throw new IllegalArgumentException("Matrix must be square.");
        }

        return calculateDeterminantRecursively(matrix);
    }

    private static double calculateDeterminantRecursively(double[][] matrix) {
        int size = matrix.length;

        if (size == 1) {
            return matrix[0][0];
        }

        if (size == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        double determinant = 0;

        for (int col = 0; col < size; col++) {
            double sign = (col % 2 == 0) ? 1 : -1;
            double subMatrixDeterminant = createSubMatrix(matrix, col);
            determinant += sign * matrix[0][col] * subMatrixDeterminant;
        }

        return determinant;
    }

    private static double createSubMatrix(double[][] matrix, int excludedCol) {
        int size = matrix.length;
        double[][] subMatrix = new double[size - 1][size - 1];

        for (int i = 1; i < size; i++) {
            int subCol = 0;
            for (int j = 0; j < size; j++) {
                if (j != excludedCol) {
                    subMatrix[i - 1][subCol++] = matrix[i][j];
                }
            }
        }

        return calculateDeterminantRecursively(subMatrix);
    }
}