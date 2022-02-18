import java.util.Collection;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main (String... args){
        LruCacheImp<Integer, String> lruCacheImp = new LruCacheImp<>();
        Scanner in = new Scanner(System.in);
        int currentAction = menu(in);
        while(currentAction != 0){
            switch (currentAction) {
                case 1 -> {
                    String str = lruCacheImp.get(getInt(in, "Введите ключ"));
                    if (str == null)
                        System.out.println("Данный ключ не найден");
                    else
                        System.out.println("Значение: " + str);
                }
                case 2 -> {
                    lruCacheImp.set(getInt(in, "Введите ключ"), getStr(in, "Введите значение"));
                    System.out.println("Элемент добавлен");
                }
                case 3 -> System.out.println("Кэш: " + lruCacheImp.toStringValue());
                case 4 -> System.out.println("Кол-во элементов: " + lruCacheImp.getSize());
                case 5 -> System.out.println("Максимальное кол-во элементов: " + lruCacheImp.getLimit());
            }
            currentAction = menu(in);
        }
    }
    private static int menu (Scanner in) {
        System.out.println("Выберите действие");
        System.out.println("0 - выход");
        System.out.println("1 - получть элемент по ключу");
        System.out.println("2 - установить элемент");
        System.out.println("3 - Напечатать");
        System.out.println("4 - показать кол-во элементов");
        System.out.println("5 - показать максимальный размер");
        return getInt(in, 0, 5, "введите число");
    }

    private static int getInt (Scanner in,String str){
        System.out.println(str);
        return in.nextInt();
    }

    private static String getStr (Scanner in,String message){
        System.out.println(message);
        return in.next();
    }

    private static int getInt (Scanner in, int left, int right, String str){
        System.out.println(str);
        int num = in.nextInt();
        while (num < left || num > right){
            System.out.println("Повторите ввод");
            num = in.nextInt();
        }
        return num;
    }
}

