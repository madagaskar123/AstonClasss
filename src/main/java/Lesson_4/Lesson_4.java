package Lesson_4;

public class Lesson_4 {
    public static void main(String[] args) {
        printThreeWords();
        checkSumSign();
        printColor();
        compareNumbers();
        checkSumInRange(5, 6);
        checkPosAndNegNum(5);
        checkNum(3);
        stringNum(4, "Здравствуйте");
        leapYear(1300);
        InvertArray();
        FillArray();
        ArrayMultiplier();
        DiagonalArray();
        Arrays();

    }

    // 1.
    static void printThreeWords() {
        System.out.println("Orange" + "\n" + "Banana" + "\n" + "Apple");
    }

    // 2.
    static void checkSumSign() {

        int a = 1;
        int b = 2;
        int sum = a + b;
        if (sum >= 0)
            System.out.println("Сумма положительная");
        else {
            System.out.println("Сумма отрицательная");
        }
    }

// 3.

    static void printColor() {
        int value = 50;

        if (value <= 0) {
            System.out.println("Красный");
        } else if (value <= 100) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }


// 4.

    static void compareNumbers() {
        int a = 15; // Первая переменная (можно изменить)
        int b = 10; // Вторая переменная (можно изменить)

        if (a >= b) {
            System.out.println("a >= b");
        } else if (a == 0) {
            System.out.println("a < b");
        }
    }
    //5.

    static boolean checkSumInRange(int a, int b) {


        int LowerBound = 10;
        int UpperBound = 20;
        int sum = a + b;
        boolean result = sum >= LowerBound && sum <= UpperBound;
        System.out.println(result);
        return result;

    }

    // 6.
    static void checkPosAndNegNum(int a) {

        if (a >= 0) {
            System.out.println("Число положительное");
        } else {
            System.out.println("Число отрицательоне");
        }
    }

    //7.
    static boolean checkNum(int a) {

        boolean result = a >= 0;
        System.out.println(result);
        return result;
    }

    //8.
    static void stringNum(int b, String a) {

        if (b <= 0) {
            System.out.println("Количество повторений должно быть положительным числом");
        }

        for (int i = 0; i < b; i++) {
            System.out.println(a);
        }
    }

    // 9.
    static boolean leapYear(int year) {

        boolean result = year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
        System.out.println(result);
        return result;
    }
    // 10.
    static void InvertArray() {
        int[] arr = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};

        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] == 0 ? 1 : 0;
            System.out.println(arr[i] + " ");

        }
    }
    // 11.
    static void  FillArray() {

        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
            System.out.println(arr[i] + " ");
        }

    }
    // 12.
    static void ArrayMultiplier() {
        int[] array = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

        // Сначала изменяем массив (числа < 6 умножаем на 2)
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 6) {
                array[i] *= 2;
            }
        }
        // Затем выводим изменённый массив
        System.out.println("Изменённый массив:");
        for (int num : array) {
            System.out.print(num + " ");


        }

    }
    // 13.
    static void DiagonalArray(){

        int size = 5; // Размер квадратного массива
        int[][] matrix = new int[size][size];
        // Заполняем главную диагональ единицами
        for (int i = 0; i < size; i++) {
            matrix[i][i] = 1; // Главная диагональ
            matrix[i][size - 1 - i] = 1; // Побочная диагональ (дополнительно)
        }
        // Выводим массив
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

    }
    // 14.

    static void  Arrays() {
        int len = 4, initialValue = 7; //адаёт длину массива (4) и значение для заполнения (7)
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) arr[i] = initialValue;
        System.out.println(java.util.Arrays.toString(arr)); // [7, 7, 7, 7]
    }



}

