import java.util.*;

public class Arrays2D {
    public static void searchLargestInMatrix(int matrix[][]) { // THIS WORKS FOR NEGATIVE TOOOO!!
        int largest = Integer.MIN_VALUE;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] > largest) {
                    largest = matrix[i][j];
                }
            }
        }
        System.out.println(largest);
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> res = new ArrayList<>();
        int startRow = 0;
        int startCol = 0;
        int endRow = matrix.length - 1;
        int endCol = matrix[0].length - 1;

        while (startRow <= endRow && startCol <= endCol) {
            // top section
            for (int i = startCol; i <= endCol; i++) {
                res.add(matrix[startRow][i]);
            }

            // right section
            for (int i = startRow + 1; i <= endRow; i++) {
                res.add(matrix[i][endCol]);
            }

            // bottom section
            for (int i = endCol - 1; i >= startCol; i--) {
                res.add(matrix[endRow][i]);
            }

            // left section
            for (int i = endRow - 1; i >= startRow + 1; i--) {
                res.add(matrix[i][startCol]);
            }

            startRow++;
            startCol++;
            endRow--;
            endCol--;
        }
        return res;

    }

    public static int diagonalSum(int[][] mat) {
        int res = 0;
        for (int i = 0; i < mat.length; i++) {
            res += mat[i][i];
            if (i != mat.length - 1 - i)
                res += mat[i][mat.length - 1 - i];
        }
        return res;
    }

    public static boolean staircaseSearch(int matrix[][], int key) {
        int row = 0, col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (key == matrix[row][col]) {
                System.out.println("key found at (" + row + "," + col + ")");
                return true;
            } else if (key < matrix[row][col]) {
                col--;
            } else {
                row++;
            }
        }
        System.out.println("key not found");
        return false;
    }

    public static void main(String[] args) {
        int matrix[][] = { { 10, 20, 30, 40 },
                { 15, 25, 35, 45 },
                { 27, 29, 37, 48 },
                { 32, 33, 39, 50 } };
        int key = 100;
        // staircaseSearch(matrix, key);
        System.out.println(spiralOrder(matrix));
        System.out.println(diagonalSum(matrix));
        // int n = matrix.length, m = matrix[0].length;
        // Scanner sc = new Scanner(System.in);

        // System.out.println("Enter elements of the matrix:");
        // for (int i = 0; i < n; i++) {
        // for (int j = 0; j < m; j++) {
        // matrix[i][j] = sc.nextInt();
        // }
        // }

        // System.out.println("Matrix is:");
        // for (int i = 0; i < n; i++) {
        // for (int j = 0; j < m; j++) {
        // System.out.print(matrix[i][j] + " ");
        // }
        // System.out.println();
        // }

        // searchMatrix(matrix);

    }
}
