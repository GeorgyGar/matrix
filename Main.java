import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[][] matrix;
        int[][] transposedMatrix;
        int[][] multipliedMatrix;

        System.out.println("Введите матрицу:");
        try {
            matrix = inputMatrix();
        }
        catch (IOException | IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return;
        }

        transposedMatrix = transpose(matrix);
        multipliedMatrix = multiplyMatrices(matrix, transposedMatrix);

        printMatrix(matrix, "Исходная матрица:");
        printMatrix(transposedMatrix,"Транспонированная матрица:");
        printMatrix(multipliedMatrix, "Результат умножения:");
    }

    public static int[][] inputMatrix() throws IOException, IllegalArgumentException {
        Scanner scanner = new Scanner(System.in);
        String[] firstRow = scanner.nextLine().split(" ");
        int[][] matrix = new int[firstRow.length][];

        checkElements(firstRow);
        matrix[0] = Arrays.stream(firstRow)
                .mapToInt(Integer::parseInt)
                .toArray();
        for (int i = 1; i < firstRow.length; i++) {
            String[] matrixRow = scanner.nextLine().split(" ");
            checkElements(matrixRow);
            checkSize(matrixRow.length, matrix.length);
            matrix[i] = Arrays.stream(matrixRow)
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        return matrix;
    }

    public static void checkElements(String[] matrixRow) throws IllegalArgumentException {
        for (int i = 0; i < matrixRow.length; i++) {
            try {
                Integer.parseInt(matrixRow[i]);
            }
            catch (NumberFormatException e) {
                throw new IllegalArgumentException("Элементами матрицы могут быть только целые числа");
            }
        }
    }

    public static void checkSize(int lengthRow, int lengthMatrix) throws IOException{
        if (lengthRow != lengthMatrix) {
            throw new IOException("Введено " + lengthRow + " элементов в строке при вводе матрицы " + lengthMatrix + "X" + lengthMatrix);
        }
    }

    public static int[][] transpose(int[][] matrix) {
        int[][] transposedMatrix = new int[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                transposedMatrix[i][j] = matrix[j][i];
            }
        }

        return transposedMatrix;
    }

    public static void printMatrix(int[][] matrix, String message) {
        System.out.println(message);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int[][] multiplyMatrices(int[][] leftMatrix, int[][] rightMatrix) {
        int[][] result = new int[leftMatrix.length][leftMatrix.length];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result.length; j++) {
                for (int k = 0; k < result.length; k++) {
                    result[i][j] = result[i][j] + leftMatrix[i][k] * rightMatrix[k][j];
                }
            }
        }

        return result;
    }
}

