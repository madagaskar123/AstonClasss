package org.example;

public class MyArraySizeException extends Exception {
    public MyArraySizeException(int rows, int cols) {
        super("Неверный размер массива: " + rows + "x" + cols + " (требуется 4x4)");
    }
}
