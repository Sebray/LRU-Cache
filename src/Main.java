import inputs.*;
import lruCache.Factory;
import lruCache.LruCache;

import java.util.Scanner;

public class Main {
    private static InputData input;
    private static final Factory factory = new Factory();
    private static final Scanner in = new Scanner(System.in);
    private static final Menu menu = new Menu();
    private static final LruCache lruCache = createLruCache();


    public static void main(String... args) {
        int currentAction = menu.menuAction(in);
        while (currentAction != 0) {
            doAction(currentAction);
            currentAction = menu.menuAction(in);
        }
    }

    private static LruCache createLruCache() {//Создается кэш с выбранным типом
        return switch (menu.menuTypeChose(in)) {
            case 1 -> {
                input = new InputIntInt();
                yield factory.createLruCache(Types.INTEGER, Types.INTEGER);
            }
            case 2 -> {
                input = new InputIntStr();
                yield factory.createLruCache(Types.INTEGER, Types.STRING);
            }
            case 3 -> {
                input = new InputStrStr();
                yield factory.createLruCache(Types.STRING, Types.INTEGER);
            }
            case 4 -> {
                input = new InputStrStr();
                yield factory.createLruCache(Types.STRING, Types.STRING);
            }
            default -> throw new IllegalStateException("Неожиданное значение");
        };
    }

    private static void doAction(int currentAction) {
        switch (currentAction) {
            case 1 -> outputValueFromKey();
            case 2 -> {
                lruCache.set(input.inputKey(in), input.inputValue(in));
                System.out.println("Элемент добавлен");
            }
            case 3 -> System.out.println("Кэш: " + lruCache.toStringValue());
            case 4 -> System.out.println("Кол-во элементов: " + lruCache.getSize());
            case 5 -> System.out.println("Максимальное кол-во элементов: " + lruCache.getLimit());
            default -> throw new IllegalStateException("Неожиданное значение");
        }
    }

    private static void outputValueFromKey() {//метод для полченя значения по ключу
        Object val = lruCache.get(input.inputKey(in));
        if (val == null)
            System.out.println("Данный ключ не найден");
        else
            System.out.println("Значение ключа: " + val);
    }
}

