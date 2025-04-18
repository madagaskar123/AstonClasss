package Lesson_11;

interface GeometricShape {
    // Основные методы, которые должны реализовать все фигуры
    double calculateArea();
    String getFillColor();
    String getBorderColor();

    // Дефолтный метод для расчета периметра (может быть переопределен)
    default double calculatePerimeter() {
        return 0;
    }

    // Дефолтный метод для вывода информации о фигуре
    default void printInfo() {
        System.out.println("Площадь: " + calculateArea());
        System.out.println("Периметр: " + calculatePerimeter());
        System.out.println("Цвет фона: " + getFillColor());
        System.out.println("Цвет границ: " + getBorderColor());
        System.out.println();
    }
}
