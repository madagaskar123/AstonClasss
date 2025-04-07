package Lesson_6;
import java.util.Arrays;
public class Product {
    private final String name;
    private final String productionDate;
    private final String manufacturer;
    private final String countryOfOrigin;
    private final double price;
    private final boolean isReserved;

    public Product(String name, String productionDate, String manufacturer,
                   String countryOfOrigin, double price, boolean isReserved) {
        this.name = name;
        this.productionDate = productionDate;
        this.manufacturer = manufacturer;
        this.countryOfOrigin = countryOfOrigin;
        this.price = price;
        this.isReserved = isReserved;
    }

    // Геттеры (можно заменить на record, но оставим как класс для примера)
    public String getName() { return name; }
    public String getProductionDate() { return productionDate; }
    public String getManufacturer() { return manufacturer; }
    public String getCountryOfOrigin() { return countryOfOrigin; }
    public double getPrice() { return price; }
    public boolean isReserved() { return isReserved; }

    public void displayInfo() {
        System.out.printf("""
            Название: %s
            Дата производства: %s
            Производитель: %s
            Страна происхождения: %s
            Цена: %.2f руб.
            Забронирован: %s
            --------------------------
            """,
                name, productionDate, manufacturer, countryOfOrigin,
                price, isReserved ? "Да" : "Нет");
    }
}

 class ProductArrayExample {
    public static void run() {
        Product[] productsArray = {
                new Product("Samsung S25 Ultra", "01.02.2025", "Samsung Corp.", "Korea", 5599, true),
                new Product("iPhone 16 Pro", "15.03.2025", "Apple Inc.", "USA", 6499, false),
                new Product("Xiaomi 14", "10.01.2025", "Xiaomi Corp.", "China", 3999, true),
                new Product("OnePlus 12", "20.12.2024", "OnePlus Ltd.", "China", 4899, false),
                new Product("Google Pixel 9", "05.02.2025", "Google LLC", "USA", 5299, true)
        };

        System.out.println("=== Массив товаров ===");
        Arrays.stream(productsArray).forEach(Product::displayInfo);
    }
}