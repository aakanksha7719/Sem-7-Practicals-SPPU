import java.util.Scanner;

public class Fibonacci {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the index of the Fibonacci number to calculate: ");
        int n = scanner.nextInt();

        // Calculate the Fibonacci number using the non-recursive method
        int fibonacciNumberNonRecursive = fibNonRecursive(n);
        System.out.println(
                "The " + n + "th Fibonacci number calculated non-recursively is: " + fibonacciNumberNonRecursive);

        // Calculate the Fibonacci number using the recursive method
        int fibonacciNumberRecursive = fibRecursive(n);
        System.out.println("The " + n + "th Fibonacci number calculated recursively is: " + fibonacciNumberRecursive);
    }

    // Non-recursive method to calculate the Fibonacci number
    public static int fibNonRecursive(int n) {
        int a = 0, b = 1, c = 0;
        for (int i = 0; i < n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return a;
    }

    // Recursive method to calculate the Fibonacci number
    public static int fibRecursive(int n) {
        if (n == 0 || n == 1) {
            return n;
        } else {
            return fibRecursive(n - 1) + fibRecursive(n - 2);
        }
    }
}