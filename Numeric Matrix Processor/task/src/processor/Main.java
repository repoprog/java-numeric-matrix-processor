package processor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        boolean exit = false;

        while (!exit) {
            System.out.println("1. Add matrices");
            System.out.println("2. Multiply matrix by a constant");
            System.out.println("3. Multiply matrices");
            System.out.println("0. Exit");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
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
                case 0:
                    exit = true;
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
        for (double[] ints : matrixA) {
            for (int j = 0; j < matrixA[0].length; j++) {
                System.out.print(ints[j] * constant + " ");
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
//                System.out.println("\n nextcolumn: "+ nextColumn);
//                System.out.println(" i: "+ i);
                for (int j = 0; j < matrixA[0].length; j++) {
//                System.out.println(" j: "+ j);
                    multiplicationSum += matrixA[i][j] * matrixB[j][nextColumn];
//                System.out.print((int)matrixA[i][j] + "*" + (int) matrixB[j][nextColumn] +"=" + (matrixA[i][j] * matrixB[j][nextColumn]));
                }
                System.out.print("  " + multiplicationSum + " ");
                multiplicationSum = 0;
                nextColumn++;
            }
            System.out.println();
        }

    }
}

