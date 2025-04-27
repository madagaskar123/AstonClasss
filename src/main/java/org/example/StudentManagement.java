package org.example;
import java.util.*;

public class StudentManagement {
     static void removeUnderperformingStudents(Set<Student> students) {
        students.removeIf(student -> student.calculateAverageGrade() < 3.0);
    }

     static void promoteStudents(Set<Student> students) {
        students.forEach(student -> {
            if (student.calculateAverageGrade() >= 3.0) {
                student.setCourse(student.getCourse() + 1);
            }
        });
    }

     static void printStudents(Set<Student> students, int course) {
        System.out.println("Студенты " + course + " курса:");
        boolean hasStudents = false;

        for (Student student : students) {
            if (student.getCourse() == course && student.calculateAverageGrade() >= 3.0) {
                System.out.println(student.getName());
                hasStudents = true;
            }
        }

        if (!hasStudents) {
            System.out.println("(нет студентов)");
        }
    }
}