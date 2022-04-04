package processor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            System.out.println("1. Add matrices\n" +
                    "2. Multiply matrix by a constant\n" +
                    "3. Multiply matrices\n" +
                    "4. Transpose matrix\n" +
                    "0. Exit");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case 0:
                    exit = true;
                    break;
                case 1:
                    double[][] matrixA = fillMatrixA();
                    double[][] matrixB = fillMatrixB();
                    if (isAddingPossible(matrixA, matrixB)) {
                        addMatrices(matrixA, matrixB);
                    } else {
                        System.out.println("Error");
                        return;
                    }
                    break;
                case 2:
                    matrixA = fillMatrixA();
                    int constant = scanner.nextInt();
                    multiplyMatrix(matrixA, constant);
                    break;
                case 3:
                    matrixA = fillMatrixA();
                    matrixB = fillMatrixB();
                    if (isMatrixValid(matrixA, matrixB)) {
                        multiplyMatrix(matrixA, matrixB);
                    } else {
                        System.out.println("Error");
                        return;
                    }
                    break;
                case 4:
                    selectTransposeMode();
                    break;
                default:
                    System.out.println("Please choose correct function.");

            }
        }
    }

    public static boolean isAddingPossible(double[][] matrixA, double[][] matrixB) {
        return matrixA.length == matrixB.length && matrixA[0].length == matrixB[0].length;
        // matrixA[0].lenght its eqal to "j" value in matrixA[i][j], size of columns
    }

    public static boolean isMatrixValid(double[][] matrixA, double[][] matrixB) {
        return matrixA[0].length == matrixB.length;
    }

    public static void selectTransposeMode() {

        System.out.println(" 1. Main diagonal\n" +
                "2. Side diagonal\n" +
                "3. Vertical line\n" +
                "4. Horizontal line");
        Scanner scanner = new Scanner(System.in);
        int mode = scanner.nextInt();
        System.out.println("The result is: ");
        switch (mode) {

            case 1:
                double[][] matrixA = fillMatrixA();
                transposeMainDiagonal(matrixA);
                break;
            case 2:
                matrixA = fillMatrixA();
                transposeSideDiagonal(matrixA);
                break;
            case 3:
                matrixA = fillMatrixA();
                transposeVertical(matrixA);
                break;
            case 4:
                matrixA = fillMatrixA();
                transposeHorizontal(matrixA);
                break;
        }
    }

    public static double[][] fillMatrixA() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter size of first matrix:");
        int aRows = scanner.nextInt();
        int aColumns = scanner.nextInt();
        double[][] matrixA = new double[aRows][aColumns];
        System.out.println("Enter first matrix:");
        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixA[0].length; j++) {
                matrixA[i][j] = scanner.nextDouble();
            }
        }
        return matrixA;
    }

    public static double[][] fillMatrixB() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter size of second matrix:");
        int bRows = scanner.nextInt();
        int bColumns = scanner.nextInt();
        System.out.println("Enter second matrix:");
        double[][] matrixB = new double[bRows][bColumns];
        for (int i = 0; i < matrixB.length; i++) {
            for (int j = 0; j < matrixB[0].length; j++) {
                matrixB[i][j] = scanner.nextDouble();
            }
        }
        return matrixB;
    }

    public static void addMatrices(double[][] matrixA, double[][] matrixB) {
        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixA[0].length; j++) {
                System.out.println(matrixA[i][j] + matrixB[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void multiplyMatrix(double[][] matrixA, int constant) {
        for (double[] doubles : matrixA) {
            for (int j = 0; j < matrixA[0].length; j++) {
                System.out.print(doubles[j] * constant + " ");
            }
            System.out.println();
        }

    }

    public static void multiplyMatrix(double[][] matrixA, double[][] matrixB) {

        for (int i = 0; i < matrixA.length; i++) {
            double multiplicationSum = 0;
            int nextColumn = 0;                     // musi być długość mtrycy B dla warunku mnożenia tablic
            while (nextColumn < matrixB[0].length) { // sprawdza wszystie rzędy w drugiej matrycy (tu columnu
                // zanim przejdzie do nowego rzędu w drugiej matrycy
                for (int j = 0; j < matrixA[0].length; j++) {

                    multiplicationSum += matrixA[i][j] * matrixB[j][nextColumn];
                }
                System.out.print("  " + multiplicationSum + " ");
                multiplicationSum = 0;
                nextColumn++;
            }
            System.out.println();
        }

    }

    public static void transposeMainDiagonal(double[][] matrixA) {  // axis from left upper corner
        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixA[0].length; j++) {
                System.out.println(matrixA[j][i] + " ");
            }
            System.out.println();
        }
    }

    public static void transposeSideDiagonal(double[][] matrixA) { // axis from right upper corner
        for (int i = matrixA.length - 1; i >= 0; i--) {
            for (int j = matrixA[0].length - 1; j >= 0; j--) {
                System.out.println(matrixA[j][i] + " ");
            }
            System.out.println();
        }
    }

    public static void transposeVertical(double[][] matrixA) {
        for (int i = 0; i < matrixA.length; i++) {
            for (int j = matrixA[0].length - 1; j >= 0; j--) {  // vertical axis 0,0 becomes 0,3 and vice versa
                System.out.println(matrixA[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void transposeHorizontal(double[][] matrixA) {
        for (int i = matrixA.length - 1; i >= 0; i--) {    // horizontal axis 0,0 becomes 3,0 and vice versa
            for (int j = 0; j < matrixA[0].length; j++) {
                System.out.println(matrixA[i][j] + " ");
            }
            System.out.println();
        }
    }

}
