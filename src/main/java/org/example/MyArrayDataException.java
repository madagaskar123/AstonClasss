package org.example;

public class MyArrayDataException extends Exception {
    public MyArrayDataException(int row, int col, String value) {
        super("Ошибка в ячейке [" + row + "][" + col + "]: '" + value + "' не является числом");
    }
}