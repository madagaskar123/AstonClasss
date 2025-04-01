package Lesson_6;

import java.time.LocalDate;

public class Product {
    private String name;
    private LocalDate productionDate;
    private String manufacturer;
    private String countryOfOrigin;
    private double price;
    private boolean isReserved;

    // Конструктор класса
    public Product(String name, LocalDate productionDate, String manufacturer,
                   String countryOfOrigin, double price, boolean isReserved) {
        this.name = name;
        this.productionDate = productionDate;
        this.manufacturer = manufacturer;
        this.countryOfOrigin = countryOfOrigin;
        this.price = price;
        this.isReserved = isReserved;
    }

    // Метод для вывода информации о товаре
    public void printProductInfo() {
        System.out.println("Информация о товаре:");
        System.out.println("Название: " + name);
        System.out.println("Дата производства: " + productionDate);
        System.out.println("Производитель: " + manufacturer);
        System.out.println("Страна происхождения: " + countryOfOrigin);
        System.out.println("Цена: " + price + " руб.");
        System.out.println("Состояние бронирования: " + (isReserved ? "Забронирован" : "Свободен"));
        System.out.println("----------------------");
    }

    // Пример использования
    public static void main(String[] args) {
        // Создаем объект товара
        Product product1 = new Product(
                "Смартфон XYZ",
                LocalDate.of(2023, 5, 15),
                "TechCorp",
                "Китай",
                49999.99,
                false
        );

        // Выводим информацию о товаре
        product1.printProductInfo();

        // Еще один товар
        Product product2 = new Product(
                "Ноутбук ABC",
                LocalDate.of(2024, 1, 10),
                "CompSystems",
                "США",
                89999.99,
                true
        );

        product2.printProductInfo();
    }
}