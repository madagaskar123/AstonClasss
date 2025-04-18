package Lesson_11;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

     // Задание 1

        // Тестирование животных
        Dog dog = new Dog("Бобик");
        Cat cat = new Cat("Мурзик");
        Cat cat2 = new Cat("Барсик");

        dog.run(150);
        dog.run(600);
        dog.swim(5);
        dog.swim(15);

        cat.run(100);
        cat.run(250);
        cat.swim(10);

        // Тестирование кормления котов
        Bowl bowl = new Bowl(30);
        System.out.println("\nСоздаем массив котов и кормим их:");

        List<Cat> cats = new ArrayList<>();
        cats.add(new Cat("Васька"));
        cats.add(new Cat("Рыжик"));
        cats.add(new Cat("Черныш"));
        cats.add(cat);
        cats.add(cat2);

        // Кормим всех котов по 7 еды
        for (Cat c : cats) {
            c.eat(bowl, 7);
        }

        // Выводим информацию о сытости
        System.out.println("\nИнформация о сытости котов:");
        for (Cat c : cats) {
            System.out.println(c.getName() + ": " + (c.isFed() ? "сыт" : "голоден"));
        }

        // Добавляем еду в миску
        bowl.addFood(20);

        // Пробуем покормить голодных котов снова
        System.out.println("\nПробуем покормить голодных котов снова:");
        for (Cat c : cats) {
            if (!c.isFed()) {
                c.eat(bowl, 7);
            }
        }

        // Выводим статистику
        System.out.println("\nСтатистика:");
        System.out.println("Всего животных: " + Animal.getAnimalCount());
        System.out.println("Собак: " + Dog.getDogCount());
        System.out.println("Котов: " + Cat.getCatCount());



// Задание 2

        // Создаем фигуры
        GeometricShape circle = new Circle(5, "Красный", "Черный");
        GeometricShape rectangle = new Rectangle(4, 6, "Синий", "Белый");
        GeometricShape triangle = new Triangle(3, 4, 5, "Зеленый", "Желтый");

        // Выводим информацию о фигурах
        System.out.println("=== Круг ===");
        circle.printInfo();

        System.out.println("=== Прямоугольник ===");
        rectangle.printInfo();

        System.out.println("=== Треугольник ===");
        triangle.printInfo();
    }
}