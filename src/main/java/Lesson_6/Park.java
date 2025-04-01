package Lesson_6;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Park {
    // Внутренний класс для хранения информации об аттракционах
    public class Attraction {
        private String name;
        private LocalTime openingTime;
        private LocalTime closingTime;
        private double price;

        public Attraction(String name, LocalTime openingTime, LocalTime closingTime, double price) {
            this.name = name;
            this.openingTime = openingTime;
            this.closingTime = closingTime;
            this.price = price;
        }

        // Геттеры
        public String getName() {
            return name;
        }

        public LocalTime getOpeningTime() {
            return openingTime;
        }

        public LocalTime getClosingTime() {
            return closingTime;
        }

        public double getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return String.format("Аттракцион: %s | Время работы: %s - %s | Цена: %.2f руб.",
                    name, openingTime, closingTime, price);
        }
    }

    private String parkName;
    private List<Attraction> attractions;

    public Park(String parkName) {
        this.parkName = parkName;
        this.attractions = new ArrayList<>();
    }

    // Метод для добавления аттракциона
    public void addAttraction(String name, LocalTime openingTime, LocalTime closingTime, double price) {
        attractions.add(new Attraction(name, openingTime, closingTime, price));
    }

    // Метод для вывода информации о всех аттракционах
    public void printAllAttractions() {
        System.out.println("Парк: " + parkName);
        System.out.println("Список аттракционов:");
        for (Attraction attraction : attractions) {
            System.out.println(attraction);
        }
    }

    public static void main(String[] args) {
        Park disneyland = new Park("Disneyland");

        // Добавляем аттракционы
        disneyland.addAttraction("Колесо обозрения",
                LocalTime.of(10, 0),
                LocalTime.of(22, 0),
                350.50);
        disneyland.addAttraction("Американские горки",
                LocalTime.of(11, 0),
                LocalTime.of(20, 0),
                500.0);
        disneyland.addAttraction("Дом с привидениями",
                LocalTime.of(12, 0),
                LocalTime.of(23, 0),
                400.75);

        // Выводим информацию
        disneyland.printAllAttractions();
    }
}