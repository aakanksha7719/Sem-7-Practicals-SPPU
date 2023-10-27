import java.util.Scanner;

public class NQueens {

    private int n;
    private int[][] board;

    public NQueens(int n) {
        this.n = n;
        this.board = new int[n][n];
    }

    public boolean solve() {
        return solve(0);
    }

    private boolean solve(int row) {
        if (row == n) {
            return true;
        }

        for (int col = 0; col < n; col++) {
            if (isSafe(row, col)) {
                board[row][col] = 1;
                if (solve(row + 1)) {
                    return true;
                }
                board[row][col] = 0;
            }
        }

        return false;
    }

    private boolean isSafe(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) {
                return false;
            }
        }

        for (int i = 0; i < col; i++) {
            if (board[row][i] == 1) {
                return false;
            }
        }

        int i = row - 1;
        int j = col - 1;
        while (i >= 0 && j >= 0) {
            if (board[i][j] == 1) {
                return false;
            }
            i--;
            j--;
        }

        i = row + 1;
        j = col - 1;
        while (i < n && j >= 0) {
            if (board[i][j] == 1) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }

    public int[][] getBoard() {
        return board;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of Queens: ");
        int n = scanner.nextInt();

        NQueens nQueens = new NQueens(n);

        if (nQueens.solve()) {
            int[][] board = nQueens.getBoard();

            // Print the n-queen's matrix
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("No solution found.");
        }

        scanner.close();
    }
}