import java.util.Scanner;

public class Menu {

    public int menuTypeChose(Scanner in) {
        System.out.println("Выберите параметры кэша");
        System.out.println("1 - ключ — String, значение — Integer");
        System.out.println("2 - ключ — String, значение — Double");
        System.out.println("3 - ключ — Integer, значение — String");
        return getInt(in, 1, 3, "введите число");
    }

    public int menuAction(Scanner in) {
        System.out.println("Выберите действие");
        System.out.println("0 - выход");
        System.out.println("1 - получть элемент по ключу");
        System.out.println("2 - установить элемент");
        System.out.println("3 - Напечатать");
        System.out.println("4 - показать кол-во элементов");
        System.out.println("5 - показать максимальный размер");
        return getInt(in, 0, 5, "введите число");
    }

    private int getInt(Scanner in, int left, int right, String str) {
        System.out.println(str);
        int num = in.nextInt();
        while (num < left || num > right) {
            System.out.println("Повторите ввод");
            num = in.nextInt();
        }
        return num;

    }
}
