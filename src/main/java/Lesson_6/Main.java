package Lesson_6;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Задание 1 и 2 (Товары)
        Product[] productsArray = {
                new Product("Samsung S25 Ultra", "01.02.2025", "Samsung Corp.", "Korea", 5599, true),
                new Product("iPhone 16 Pro", "15.03.2025", "Apple Inc.", "USA", 6499, false),
                new Product("Xiaomi 14", "10.01.2025", "Xiaomi Corp.", "China", 3999, true),
                new Product("OnePlus 12", "20.12.2024", "OnePlus Ltd.", "China", 4899, false),
                new Product("Google Pixel 9", "05.02.2025", "Google LLC", "USA", 5299, true)
        };

        System.out.println("=== Массив товаров ===");
        Arrays.stream(productsArray).forEach(Product::displayInfo);

        // Задание 3 (Парк)
        Park disneyland = new Park("Disneyland");
        Park.Attraction rollerCoaster = new Park.Attraction("Горки", "10:00-20:00", 500);
        Park.Attraction ferrisWheel = new Park.Attraction("Колесо обозрения", "09:00-22:00", 300);

        System.out.println("=== Аттракционы в парке ===");
        rollerCoaster.displayInfo(disneyland.getParkName());
        ferrisWheel.displayInfo(disneyland.getParkName());


    }
}