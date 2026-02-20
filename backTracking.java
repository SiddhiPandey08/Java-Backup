public class backTracking {
    // used when : decision or optimisation or find all possible ways (enumeration)
    public static void changeArr(int arr[], int i, int val) {
        if (i == arr.length) {
            printArr(arr);
            return;
        }
        arr[i] = val;
        changeArr(arr, i + 1, val + 1);// recursion
        arr[i] = arr[i] - 1;// backtracking step
    }

    public static void printArr(int arr[]) {
        for (int index = 0; index < arr.length; index++) {
            System.out.print(arr[index] + " ");
        }
        System.out.println();
    }

    public static void findSubsets(int i, String str, String ans) {
        // base case
        if (i == str.length()) {
            if (ans.length() == 0) {
                System.out.println("null");
            } else {
                System.out.println(ans);
            }
            return;

        }
        // recursion
        // yes choice
        findSubsets(i + 1, str, ans + str.charAt(i));
        // no coice
        findSubsets(i + 1, str, ans);
    }

    public static void sbFindSubsets(int i, String str, StringBuilder ans) {
        // base case
        if (i == str.length()) {
            if (ans.length() == 0) {
                System.out.println("null");
            } else {
                System.out.println(ans.toString());
            }
            return;
        }

        // recursion
        // yes choice → include charAt(i)
        ans.append(str.charAt(i)); // add current char
        sbFindSubsets(i + 1, str, ans); // recursive call
        ans.deleteCharAt(ans.length() - 1); // backtrack (remove last char)

        // no choice → skip charAt(i)
        sbFindSubsets(i + 1, str, ans);
    }

    public static void permutations(String str, String ans) { // TC = O(n*n!)
        if (str.length() == 0) {
            System.out.println(ans);
        }
        for (int index = 0; index < str.length(); index++) {
            char curr = str.charAt(index);
            // exclude curr char-> "abcde" = "ab" + "de"
            String Newstr = str.substring(0, index) + str.substring(index + 1);
            permutations(Newstr, ans + curr);
        }
    }

    public static boolean isSafe(char board[][], int row, int col) {
        // check vertical up
        for (int i = row - 1; i >= 0; i--) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }
        // diagonal left up
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        // diagonal right up
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    static int count = 0; // will count number of ways of arranging n queens
    // time complexity = O(n!)

    public static void Nqueens(char board[][], int row) {
        if (row == board.length) {
            count++; // count th enumber of ways to arrange n queens
            printBoard(board);
            return;
        }
        for (int col = 0; col < board.length; col++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 'Q';
                Nqueens(board, row + 1);
                board[row][col] = 'x';// backtracking step (remove queen from that position because it did not lead to
                                      // a solution) and to explore other possibilities
            }
        }
    }

    public static int gridWays(int i, int j, int n, int m) {
        if (i == n - 1 && j == m - 1) {
            return 1; // for target cell/last cell reached
        } else if (i == n || j == m) {
            return 0; // came out of the grid
        }
        int w1 = gridWays(i + 1, j, n, m);
        int w2 = gridWays(i, j + 1, n, m);
        return w1 + w2;
    }

    public static boolean isSafe(int sudoku[][], int row, int col, int digit) {
        for (int index = 0; index < 8; index++) {
            if (sudoku[row][index] == digit) {
                return false;
            }
        }
        for (int index = 0; index < 8; index++) {
            if (sudoku[index][col] == digit) {
                return false;
            }
        }
        int sr = (row / 3) * 3;
        int sc = (col / 3) * 3;
        for (int index = sr; index < +sr + 3; index++) {
            for (int j = sc; j < sc + 3; j++) {
                if (sudoku[index][j] == digit) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean sudokuSolve(int sudoku[][], int col, int row) {
        if (row == 9 && col == 0) {
            return true;
        }
        int nextRow = row, nextCol = col + 1;
        if (col + 1 == 9) {
            nextRow = row + 1;
            nextCol = 0;
        }
        if (sudoku[row][col] != 0) {
            return sudokuSolve(sudoku, nextCol, nextRow);
        }
        for (int digit = 1; digit <= 9; digit++) {
            if (isSafe(sudoku, row, col, digit)) {
                sudoku[row][col] = digit;
                if (sudokuSolve(sudoku, nextCol, nextRow)) {
                    return true;
                }
                sudoku[row][col] = 0;
            }
        }
        return false;
    }

    public static void printBoard(char board[][]) {
        System.out.println("-----chess board-----");
        for (int index = 0; index < board.length; index++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[index][j] + " ");
            }
            System.out.println();
        }
    }

    public static void printSudoku(int sudoku[][]) {
        for (int index = 0; index < 9; index++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudoku[index][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int arr[] = new int[5];
        changeArr(arr, 0, 1);
        printArr(arr);
        String str = "abc";
        findSubsets(0, str, "");
        System.out.println("Using StringBuilder:");
        sbFindSubsets(0, str, new StringBuilder());
        permutations(str, "");
        char board[][] = new char[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = 'x';
            }
        }
        Nqueens(board, 0);
        System.out.println("Total number of ways to solve n queens =" + count);
        System.out.println("Total number of ways to reach the end of grid:");
        int k = 3, m = 3;
        System.out.println(gridWays(0, 0, k, m));

        int sudoku[][] = { { 0, 0, 8, 0, 0, 0, 0, 0, 0 },
                { 4, 9, 0, 1, 5, 7, 0, 0, 2 },
                { 0, 0, 3, 0, 0, 4, 1, 9, 0 },
                { 1, 8, 5, 0, 6, 0, 0, 2, 0 },
                { 0, 0, 0, 0, 2, 0, 0, 6, 0 },
                { 9, 6, 0, 4, 0, 5, 3, 0, 0 },
                { 0, 3, 0, 0, 7, 2, 0, 0, 4 },
                { 0, 4, 9, 0, 3, 0, 0, 5, 7 },
                { 8, 2, 7, 0, 0, 9, 0, 1, 3 } };
        if (sudokuSolve(sudoku, 0, 0)) {
            System.out.println("solution exists");
            printSudoku(sudoku);

        } else {
            System.out.println("solution does not exist");
        }
    }
}
