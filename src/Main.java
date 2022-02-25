import factory.IFactory;
import factory.LruCacheIntStrFactory;
import factory.LruCacheStrDFactory;
import factory.LruCacheStrIntFactory;
import lruCache.ILruCache;

import java.util.Scanner;

public class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final Menu menu = new Menu();
    private static final int typeId = menu.menuTypeChose(in);//сохраняю номер типа
    private static final IFactory factory = createLruCacheFactory();//создаю фарику, основываясь на выбранный тип
    private static final ILruCache lruCache = factory.createLruCache();//создаю кэш
    private static Types K, V;

    public static void main(String... args) {
        convertTypeIdToKeyAndValueTypes();//отдельно сохраняю тип ключа и тип значения по номеру типа. Это нужно для корректного ввода
        int currentAction = menu.menuAction(in);
        while (currentAction != 0) {
            doAction(currentAction);
            currentAction = menu.menuAction(in);
        }
    }

    private static IFactory createLruCacheFactory() {//Создается фабрика с выбранным типом
        return switch (typeId) {
            case 1:
                yield new LruCacheStrIntFactory();
            case 2:
                yield new LruCacheStrDFactory();
            case 3:
                yield new LruCacheIntStrFactory();
            default:
                throw new IllegalStateException("Неожиданное значение");
        };
    }

    private static void doAction(int currentAction) {
        switch (currentAction) {
            case 1 -> outputValueFromKey();
            case 2 -> setElementToLruCache();
            case 3 -> System.out.println("Кэш: " + lruCache.toStringValue());
            case 4 -> System.out.println("Кол-во элементов: " + lruCache.getSize());
            case 5 -> System.out.println("Максимальное кол-во элементов: " + lruCache.getLimit());
            default -> throw new IllegalStateException("Неожиданное значение");
        }
    }

    private static void outputValueFromKey() {//метод для получения значения по ключу
        Object val = lruCache.get(input(K, "Введите ключ"));
        if (val == null)
            System.out.println("Данный ключ не найден");
        else
            System.out.println("Значение ключа: " + val);
    }

    private static void setElementToLruCache() {
        lruCache.set(input(K, "Введите ключ"), input(V, "Введите значение"));
        System.out.println("Элемент добавлен");
    }

    private static void convertTypeIdToKeyAndValueTypes(){
        switch (typeId){
            case 1 -> {
                K = Types.STRING;
                V = Types.INTEGER;
            }
            case 2 -> {
                K = Types.STRING;
                V = Types.DOUBLE;
            }
            case 3 -> {
                K = Types.INTEGER;
                V = Types.STRING;
            }
        }
    }

    private static Object input(Types type, String message){
        return switch (type){
            case INTEGER -> Input.getInt(in, message);
            case STRING -> Input.getStr(in, message);
            case DOUBLE -> Input.getDouble(in, message);
        };
    }
}

