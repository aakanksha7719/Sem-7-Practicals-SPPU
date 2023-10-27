import java.util.Scanner;

class MatrixMultiplication {
    private int[][] matrixA;
    private int[][] matrixB;
    private int[][] resultMatrix;
    private int rowsA, colsA, rowsB, colsB;

    public MatrixMultiplication(int[][] matrixA, int[][] matrixB) {
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.rowsA = matrixA.length;
        this.colsA = matrixA[0].length;
        this.rowsB = matrixB.length;
        this.colsB = matrixB[0].length;
        this.resultMatrix = new int[rowsA][colsB];
    }

    public void multiplySingleThreaded() {
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    resultMatrix[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }
    }

    public void multiplyMultiThreadedRow() throws InterruptedException {
        Thread[] threads = new Thread[rowsA];

        for (int i = 0; i < rowsA; i++) {
            final int row = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < colsB; j++) {
                    for (int k = 0; k < colsA; k++) {
                        resultMatrix[row][j] += matrixA[row][k] * matrixB[k][j];
                    }
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
    }

    public void printResult() {
        System.out.println("Resultant Matrix:");
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                System.out.print(resultMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of rows for matrix A: ");
        int rowsA = scanner.nextInt();
        System.out.print("Enter the number of columns for matrix A: ");
        int colsA = scanner.nextInt();

        int[][] matrixA = new int[rowsA][colsA];
        System.out.println("Enter elements for matrix A:");
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsA; j++) {
                matrixA[i][j] = scanner.nextInt();
            }
        }

        System.out.print("Enter the number of rows for matrix B: ");
        int rowsB = scanner.nextInt();
        System.out.print("Enter the number of columns for matrix B: ");
        int colsB = scanner.nextInt();

        int[][] matrixB = new int[rowsB][colsB];
        System.out.println("Enter elements for matrix B:");
        for (int i = 0; i < rowsB; i++) {
            for (int j = 0; j < colsB; j++) {
                matrixB[i][j] = scanner.nextInt();
            }
        }

        scanner.close();

        if (colsA != rowsB) {
            System.out.println("Matrix multiplication is not possible. Columns of matrix A must be equal to rows of matrix B.");
            return;
        }

        MatrixMultiplication matrixMultiplier = new MatrixMultiplication(matrixA, matrixB);

        // Single-threaded matrix multiplication
        System.out.println("Single-threaded matrix multiplication:");
        matrixMultiplier.multiplySingleThreaded();
        matrixMultiplier.printResult();

        // Multi-threaded matrix multiplication - one thread per row
        System.out.println("Multi-threaded matrix multiplication (one thread per row):");
        try {
            matrixMultiplier = new MatrixMultiplication(matrixA, matrixB);
            matrixMultiplier.multiplyMultiThreadedRow();
            matrixMultiplier.printResult();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
