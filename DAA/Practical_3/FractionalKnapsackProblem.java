import java.util.Arrays;
import java.util.Scanner;

public class FractionalKnapsackProblem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of items: ");
        int n = scanner.nextInt();

        System.out.println("Enter the profits of the items: ");
        double[] profits = new double[n];
        for (int i = 0; i < n; i++) {
            profits[i] = scanner.nextDouble();
        }

        System.out.println("Enter the weights of the items: ");
        double[] weights = new double[n];
        for (int i = 0; i < n; i++) {
            weights[i] = scanner.nextDouble();
        }

        System.out.println("Enter the capacity of the knapsack: ");
        double capacity = scanner.nextDouble();

        // Calculate the value/weight ratio for each item
        Double[] valueToWeightRatios = new Double[n];
        for (int i = 0; i < n; i++) {
            valueToWeightRatios[i] = new Double(profits[i] / weights[i]);
        }

        // Sort the items in decreasing order of value/weight ratio
        Arrays.sort(valueToWeightRatios, (a, b) -> Double.compare(b, a));

        // Fill the knapsack with the items in the sorted order
        double totalProfit = 0;
        double currentWeight = 0;
        for (int i = 0; i < n; i++) {
            if (currentWeight + weights[i] <= capacity) {
                totalProfit += profits[i];
                currentWeight += weights[i];
            } else {
                // Add a fraction of the current item to the knapsack
                double fraction = (capacity - currentWeight) / weights[i];
                totalProfit += profits[i] * fraction;
                break;
            }
        }

        // Print the total profit
        System.out.println("The total profit is: " + totalProfit);
    }
}

//Enter the number of items: 3
//Enter the profits of the items: 10.0 20.0 30.0
//Enter the weights of the items: 10.0 20.0 30.0
//Enter the capacity of the knapsack: 50.0
//The total profit is: 50.0