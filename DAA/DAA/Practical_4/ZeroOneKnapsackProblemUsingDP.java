import java.util.Scanner;

public class ZeroOneKnapsackProblemUsingDP {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of items: ");
        int n = scanner.nextInt();

        System.out.println("Enter the profits of the items: ");
        int[] profits = new int[n];
        for (int i = 0; i < n; i++) {
            profits[i] = scanner.nextInt();
        }

        System.out.println("Enter the weights of the items: ");
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            weights[i] = scanner.nextInt();
        }

        System.out.println("Enter the capacity of the knapsack: ");
        int capacity = scanner.nextInt();

        // Solve the 0-1 knapsack problem using dynamic programming
        int[][] knapsackTable = new int[n + 1][capacity + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (i == 0 || j == 0) {
                    knapsackTable[i][j] = 0;
                } else if (weights[i - 1] > j) {
                    knapsackTable[i][j] = knapsackTable[i - 1][j];
                } else {
                    knapsackTable[i][j] = Math.max(knapsackTable[i - 1][j], profits[i - 1] + knapsackTable[i - 1][j - weights[i - 1]]);
                }
            }
        }

        // Print the maximum profit
        System.out.println("The maximum profit is: " + knapsackTable[n][capacity]);
    }
}