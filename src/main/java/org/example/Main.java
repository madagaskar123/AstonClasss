package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Задание 1: Управление студентами ===");
        task1();

        System.out.println("\n=== Задание 2: Телефонный справочник ===");
        task2();
    }

    private static void task1() {
        Set<Student> students = new HashSet<>();
        students.add(new Student("Иван Иванов", "Группа 101", 1, Map.of("Математика", 4, "Физика", 3)));
        students.add(new Student("Петр Петров", "Группа 102", 1, Map.of("Математика", 2, "Физика", 2)));
        students.add(new Student("Артем Сидоров", "Группа 201", 2, Map.of("Математика", 5, "Физика", 4)));
        students.add(new Student("Анна Казакова", "Группа 202", 2, Map.of("Математика", 3, "Физика", 2)));

        System.out.println("\nВсе студенты:");
        for (Student student : students) {
            System.out.println(  student.getName() + ", Курс: " + student.getCourse());
        }

    }

    private static void task2() {
        PhoneDirectory directory = new PhoneDirectory();

        directory.add("Иванов", "123-456");
        directory.add("Петров", "234-567");
        directory.add("Сидоров", "345-678");
        directory.add("Иванов", "456-789");
        directory.add("Иванов", "567-890");


        System.out.println("\nПоиск номеров:");
        searchAndPrint(directory, "Иванов");
        searchAndPrint(directory, "Петров");
        searchAndPrint(directory, "Несуществующий");
    }

    private static void searchAndPrint(PhoneDirectory directory, String surname) {
        List<String> phones = directory.get(surname);
        if (phones.isEmpty()) {
            System.out.println("Фамилия '" + surname + "' не найдена в справочнике");
        } else {
            System.out.println("Номера для фамилии '" + surname + "': " + String.join(", ", phones));
        }
    }
}