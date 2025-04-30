package org.example;

public class Main {

    public static void main(String[] args) {
        // Примеры использования:
        // 1. Факториал числа
        int number = 5;
        long factorial = FactorialCalculator.calculateFactorial(number);
        System.out.println("Факториал числа " + number + " = " + factorial);

        // 2. Площадь треугольника
        double base = 10.0;
        double height = 5.0;
        double area = TriangleAreaCalculator.calculateArea(base, height);
        System.out.println("Площадь треугольника = " + area);

        // 3. Арифметические операции
        int x = 10;
        int y = 3;
        System.out.println(x + " + " + y + " = " + ArithmeticOperations.add(x, y));
        System.out.println(x + " - " + y + " = " + ArithmeticOperations.subtract(x, y));
        System.out.println(x + " * " + y + " = " + ArithmeticOperations.multiply(x, y));
        System.out.println(x + " / " + y + " = " + ArithmeticOperations.divide(x, y));

        // 4. Сравнение чисел
        int a = 7;
        int b = 12;
        System.out.println("Сравнение: " + NumberComparator.compare(a, b));
    }
}