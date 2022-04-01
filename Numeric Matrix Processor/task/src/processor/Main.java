package processor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int aRows = scanner.nextInt();
        int aColumns = scanner.nextInt();

        int[][] matrixA = new int[aRows][aColumns];
        fillMatrixA(matrixA);
        int constant = scanner.nextInt();
        multiplyMatrix(matrixA, constant);
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

    public static void multiplyMatrix(int[][] matrixA, int constant) {
        for (int[] ints : matrixA) {
            for (int j = 0; j < matrixA[0].length; j++) {
                System.out.print(ints[j] * constant + " ");
            }
            System.out.println();
        }

    }
}
