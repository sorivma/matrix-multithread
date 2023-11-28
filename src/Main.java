import calculator.DeterminantCalculator;
import calculator.impl.Calculator;
import calculator.impl.ThreadedCalculator;

import java.util.Random;

public class Main {
    private static final double[][] matrix = generateRandomMatrix(11, 11);

    public static double[][] generateRandomMatrix(int rows, int cols) {
        Random random = new Random();
        double[][] matrix = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextDouble();
            }
        }

        return matrix;
    }

    public static void main(String[] args) {
        DeterminantCalculator threadedCalculator = new ThreadedCalculator(matrix, 4);
        DeterminantCalculator calculator = new Calculator(matrix);
        long startTimeThreaded = System.currentTimeMillis();
        System.out.println(threadedCalculator.calculateDeterminant());
        long endTimeThreaded = System.currentTimeMillis();
        System.out.printf("Took [%s] ms\n", endTimeThreaded - startTimeThreaded);


        long startTime = System.currentTimeMillis();
        System.out.println(calculator.calculateDeterminant());
        long endTime = System.currentTimeMillis();
        System.out.printf("Took [%s] ms\n", endTime - startTime);

        System.out.println("Для проверки заменим первую строку на 0.0 det(M) = 0");
        for (int i = 0; i < matrix.length; i++) {
            matrix[0][i] = 0;
        }

        DeterminantCalculator testThreadedCalculator = new ThreadedCalculator(matrix, 4);
        DeterminantCalculator testCalculator = new Calculator(matrix);
        System.out.printf("Паралельный [%s] = Однопоточный [%s]",
                testThreadedCalculator.calculateDeterminant(),
                testCalculator.calculateDeterminant());
    }
}