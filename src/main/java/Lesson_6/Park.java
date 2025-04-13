package Lesson_6;
public class Park {
    private final String parkName;

    public Park(String parkName) {
        this.parkName = parkName;
    }
    public String getParkName() {
        return parkName;
    }

    // Внутренний класс (можно сделать record, но оставим как класс)
    public static class Attraction {
        private final String name;
        private final String workingHours;
        private final double price;

        public Attraction(String name, String workingHours, double price) {
            this.name = name;
            this.workingHours = workingHours;
            this.price = price;
        }

        public void displayInfo(String parkName) {
            System.out.printf("""
                Парк: %s
                Аттракцион: %s
                Время работы: %s
                Стоимость: %.2f руб.
                --------------------------
                """,
                    parkName, name, workingHours, price);
        }
    }
}

// Пример использования

