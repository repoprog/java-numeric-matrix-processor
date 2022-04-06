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
                    "5. Calculate a determinant\n" +
                    "6. Inverse matrix\n" +
                    "7. Calculate cofactors\n" +
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
                        System.out.println("Error, addition not possible");
                        return;
                    }
                    break;
                case 2:
                    matrixA = fillMatrixA();
                    double constant = scanner.nextInt();
                    printMatrix(multiplyMatrix(matrixA, constant));
                    break;
                case 3:
                    matrixA = fillMatrixA();
                    matrixB = fillMatrixB();
                    if (isMatrixValid(matrixA, matrixB)) {
                        multiplyMatrix(matrixA, matrixB);
                    } else {
                        System.out.println("Error, multiplication not possible.");
                        return;
                    }
                    break;
                case 4:
                    selectTransposeMode();
                    break;
                case 5:
                    matrixA = fillMatrixA();
                    int n = matrixA.length;
                    System.out.println(getDeterminant(matrixA, n));
                    break;
                case 6:
                    matrixA = fillMatrixA();
                    n = matrixA.length;
                    if (getDeterminant(matrixA, n) != 0) { // required to inverse matrix
                        double determinant = getDeterminant(matrixA, n);
                        coFactors = new double[matrixA.length][matrixA.length];
                        printMatrix(inverseMatrix(matrixA, determinant));
                    } else {
                        System.out.println("Error, inverse not possible.");
                        return;
                    }
                    break;
                case 7:
                    matrixA = fillMatrixA();
                    coFactors = new double[matrixA.length][matrixA.length];
                    n = coFactors.length;
                    getCoFactorMatrix(matrixA, n);
                    printMatrix(coFactors);
                    break;
                default:
                    System.out.println("Please choose correct function.");

            }
        }
    }

    public static boolean isAddingPossible(double[][] matrixA, double[][] matrixB) {
        return matrixA.length == matrixB.length && matrixA[0].length == matrixB[0].length;
        // matrixA[0].length its equal to "j" value in matrixA[i][j], size of columns
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
                printMatrix(transposeMainDiagonal(matrixA));
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

    public static double[][] coFactors;

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

    public static double[][] multiplyMatrix(double[][] matrixA, double constant) {
        double[][] result = new double[matrixA.length][matrixA.length];
        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixA[0].length; j++) {
                result[i][j] = matrixA[i][j] * constant;
            }
        }
        return result;
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

    public static double[][] transposeMainDiagonal(double[][] matrixA) {  // axis from left upper corner
        double[][] result = new double[matrixA.length][matrixA.length];
        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixA[0].length; j++) {
                result[i][j] = matrixA[j][i];
            }
        }
        return result;
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
        for (double[] doubles : matrixA) {
            for (int j = matrixA[0].length - 1; j >= 0; j--) {  // vertical axis 0,0 becomes 0,3 and vice versa
                System.out.println(doubles[j] + " ");
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

    public static double[][] inverseMatrix(double[][] matrixA, double determinant) {
        // to inverse: find 1/|A| * adj(A), so 1/determinant(A) * transposedMD matrix from cofactors of matrixA
        int n = matrixA.length;
        getCoFactorMatrix(matrixA, n);
        double[][] adj = transposeMainDiagonal(coFactors);// adj(A) =  C(matrix of cofactors) T(transosed)
        return multiplyMatrix(adj, 1 / determinant);
    }

    // Function to get cofactor of
    // matrix[p][q] in temp[][]. n is
    // current dimension of mat[][]
    static void getCofactor(double matrix[][], double temp[][], int p, int q, int n) {
        int i = 0, j = 0;

        // Looping for each element of
        // the matrix
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                // Copying into temporary matrix
                // only those element which are
                // not in given row and column
                if (row != p && col != q) {
                    temp[i][j++] = matrix[row][col];
                    // Row is filled, so increase
                    // row index and reset col
                    // index
                    if (j == n - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }

    /* Recursive function for finding determinant
    of matrix. n is current dimension of mat[][]. */
    static double getDeterminant(double[][] matrix, int n) {
        double D = 0; // Initialize result

        // Base case : if matrix contains single
        // element
        if (n == 1)
            return matrix[0][0];

        // To store cofactors
        double[][] temp = new double[n][n];

        // To store sign multiplier
        int sign = 1;

        // Iterate for each element of first row
        for (int f = 0; f < n; f++) {
            // Getting Cofactor of matrix[0][f]
            getCofactor(matrix, temp, 0, f, n);
            D += sign * matrix[0][f] * getDeterminant(temp, n - 1);

            // terms are to be added with
            // alternate sign
            sign = -sign;
        }
        return D;
    }

    // Function to get cofactors of matrix[N][N] in coFactor[N][N].
    static void getCoFactorMatrix(double matrix[][], int n) {
        if (n == 1) {
            matrix[0][0] = 1;
            return;
        }
        // temp is used to store cofactors of A[][]
        int sign = 1;
        double[][] temp = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Get cofactor of matrix[i][j]
                getCofactor(matrix, temp, i, j, n);
                // sign of coFactors[j][i] positive if sum of row
                // and column indexes is even.
                sign = ((i + j) % 2 == 0) ? 1 : -1;

                // Interchanging rows and columns to get the
                // transpose of the cofactor matrix
                coFactors[i][j] = (sign) * (getDeterminant(temp, n - 1));
            }
        }
    }

    public static void printMatrix(double[][] matrix) {
        for (double[] values : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(values[j] + " ");
            }
            System.out.println();
        }
    }
}
