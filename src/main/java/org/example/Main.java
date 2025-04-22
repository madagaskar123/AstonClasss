package org.example;

public class Main {
    public static void main(String[] args) {
        // Тестовые данные
        String[][] testCases = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        String[][] invalidData = {
                {"1", "2", "3", "4"},
                {"5", "6", "X", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        String[][] wrongSize = {
                {"1", "2", "3"},
                {"4", "5", "6"},
                {"7", "8", "9"}
        };

        // Тестирование обработки массива
        System.out.println("=== Тест обработки массива ===");
        testArrayProcessing(testCases);
        testArrayProcessing(invalidData);
        testArrayProcessing(wrongSize);

        // Тестирование ArrayIndexOutOfBounds
        System.out.println("\n=== Тест выхода за границы массива ===");
        testArrayIndexOutOfBounds();
    }

    private static void testArrayProcessing(String[][] array) {
        try {
            int result = ArrayProcessor.sumArray(array);
            System.out.println("Успех! Сумма: " + result);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void testArrayIndexOutOfBounds() {
        try {
            ArrayProcessor.demonstrateIndexOutOfBounds();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Поймано исключение: " + e.toString());
            System.out.println("Неверный индекс: " + e.getMessage().replace("Index ", ""));
        }
    }
}