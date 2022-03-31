package processor;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int aRows = scanner.nextInt();
        int aColumns = scanner.nextInt();

        int[][] matrixA = new int[aRows][aColumns];
        fillMatrixA(matrixA);

        int bRows = scanner.nextInt();
        int bColumns = scanner.nextInt();

        int[][] matrixB = new int[bRows][bColumns];
        fillMatrixB(matrixB);

        if (isMatrixValid(aRows, aColumns, bRows, bColumns)) {
            addMatrices(matrixA, matrixB);
        } else {
            System.out.println("ERROR");
        }
    }

    public static boolean isMatrixValid(int aRows, int aColumns, int bRows, int bColumns) {
        return aRows == bRows && aColumns == bColumns;
    }

    public static void fillMatrixA(int[][] matrixA) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixA[0].length; j++) {
                matrixA[i][j] = scanner.nextInt();
//                System.out.print(matrixA[i][j]);
            }
//            System.out.println();
        }
    }

    public static void fillMatrixB(int[][] matrixB) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < matrixB.length; i++) {
            for (int j = 0; j < matrixB[0].length; j++) {
                matrixB[i][j] = scanner.nextInt();
//                System.out.print(matrixB[i][j]);
            }
//            System.out.println();
        }
    }

    public static void addMatrices(int[][] matrixA, int[][] matrixB) {
        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixA[0].length; j++) {
                System.out.print(matrixA[i][j] + matrixB[i][j] + " ");
            }
            System.out.println();
        }

    }
}
