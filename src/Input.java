import java.util.Scanner;

public class Input {
    public static int getInt(Scanner in, String message) {
        System.out.println(message);
        while (!in.hasNextInt()) {
            System.out.println("Не верное значение. Повторите ввод");
            in.next();
        }
        return in.nextInt();

    }

    public static String getStr(Scanner in, String message) {
        System.out.println(message);
        return in.next();
    }

    public static double getDouble(Scanner in, String message) {
        System.out.println(message);
        while (!in.hasNextDouble()) {
            System.out.println("Не верное значение. Повторите ввод");
            in.next();
        }
        return in.nextDouble();

    }
}
