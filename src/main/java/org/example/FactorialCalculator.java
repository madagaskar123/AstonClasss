package org.example;

public class FactorialCalculator {

    public static long calculateFactorial(int n) {
        if (n < 0 || n > 20) {
            throw new IllegalArgumentException("Факториал определён только для 0 ≤ n ≤ 20.");
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}

