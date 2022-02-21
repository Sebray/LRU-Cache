package inputs;

import java.util.Scanner;

public class InputStrInt implements InputData {
    @Override
    public Object inputKey(Scanner in) {
        return Input.getStr(in, "Введите ключ");
    }

    @Override
    public Object inputValue(Scanner in) {
        return Input.getInt(in, "Введите значение");
    }
}
