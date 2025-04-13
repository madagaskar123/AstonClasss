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


