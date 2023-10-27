import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Knapsack
 {
    static class Node {
        int level;
        int bound;
        int profit;
        int weight;
        ArrayList<Integer> include;

        public Node(int level, int bound, int profit, int weight, ArrayList<Integer> include) {
            this.level = level;
            this.bound = bound;
            this.profit = profit;
            this.weight = weight;
            this.include = include;
        }
    }

    public static int knapsack(int[] profits, int[] weights, int capacity) {
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(new Node(-1, 0, 0, 0, new ArrayList<>()));
        int maxProfit = 0;

        while (!nodes.isEmpty()) {
            Node node = nodes.remove(nodes.size() - 1);
            if (node.level == profits.length - 1) {
                if (node.profit > maxProfit) {
                    maxProfit = node.profit;
                }
            } else {
                int nextLevel = node.level + 1;
                int profitBound = node.profit + (capacity - node.weight) * profits[nextLevel] / weights[nextLevel];
                if (profitBound > maxProfit) {
                    nodes.add(new Node(nextLevel, profitBound, node.profit + profits[nextLevel], node.weight + weights[nextLevel], new ArrayList<>(node.include)));
                    nodes.add(new Node(nextLevel, node.bound, node.profit, node.weight, new ArrayList<>(node.include)));
                }
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of items:");
        int n = scanner.nextInt();

        int[] profits = new int[n];
        int[] weights = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Enter the profit of item " + i + ":");
            profits[i] = scanner.nextInt();

            System.out.println("Enter the weight of item " + i + ":");
            weights[i] = scanner.nextInt();
        }

        System.out.println("Enter the capacity of the knapsack:");
        int capacity = scanner.nextInt();

        int maxProfit = knapsack(profits, weights, capacity);

        System.out.println("The maximum profit is: " + maxProfit);
    }
}
