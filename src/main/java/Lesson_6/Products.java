package Lesson_6;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Products {
    private final String name;
    private final LocalDate productionDate;
    private final String manufacturer;
    private final String countryOfOrigin;
    private final double price;
    private final boolean isReserved;

    // Конструктор принимает LocalDate вместо String для даты
    public Products(String name, LocalDate productionDate, String manufacturer,
                   String countryOfOrigin, double price, boolean isReserved) {
        this.name = name;
        this.productionDate = productionDate;
        this.manufacturer = manufacturer;
        this.countryOfOrigin = countryOfOrigin;
        this.price = price;
        this.isReserved = isReserved;
    }

    public void printProductInfo() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        System.out.println("Информация о товаре:");
        System.out.println("Название: " + name);
        System.out.println("Дата производства: " + productionDate.format(dateFormatter));
        System.out.println("Производитель: " + manufacturer);
        System.out.println("Страна происхождения: " + countryOfOrigin);
        System.out.println("Цена: " + price + " $");
        System.out.println("Состояние бронирования: " + (isReserved ? "Забронирован" : "Свободен"));
        System.out.println("----------------------");
    }

    public static void main(String[] args) {
        // Форматтер для преобразования строк в LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        // Создаем массив из 5 товаров
        Product[] productsArray = {
                new Product("Samsung S25 Ultra", LocalDate.parse("01.02.2025", formatter),
                        "Samsung Corp.", "Korea", 5599, true),
                new Product("iPhone 16 Pro", LocalDate.parse("15.09.2024", formatter),
                        "Apple Inc.", "USA", 1299, false),
                new Product("Xiaomi 14", LocalDate.parse("10.11.2023", formatter),
                        "Xiaomi", "China", 799, true),
                new Product("Pixel 8 Pro", LocalDate.parse("05.10.2023", formatter),
                        "Google", "USA", 999, false),
                new Product("Huawei P60", LocalDate.parse("20.03.2023", formatter),
                        "Huawei", "China", 899, true)
        };

        // Выводим информацию о товарах
        for (Product product : productsArray) {
            product.printProductInfo();
        }
    }
}