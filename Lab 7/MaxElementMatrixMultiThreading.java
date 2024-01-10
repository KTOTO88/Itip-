import java.util.Arrays;
import java.util.Random;

public class MaxElementMatrixMultiThreading {
    private static int[][] matrix = new int[2][2];
    private static int maxElement = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = random.nextInt(100);
            }
        }

        Thread[] threads = new Thread[matrix.length];


        for (int i = 0; i < matrix.length; i++) {
            final int row = i;
            threads[i] = new Thread(() -> {
                int maxInRow = Integer.MIN_VALUE;
                for (int j = 0; j < matrix[row].length; j++) {
                    if (matrix[row][j] > maxInRow) {
                        maxInRow = matrix[row][j];
                    }
                }
                synchronized (MaxElementMatrixMultiThreading.class) {
                    if (maxInRow > maxElement) {
                        maxElement = maxInRow;
                    }
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Результат: " + maxElement);
    }
}
