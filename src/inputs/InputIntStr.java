package inputs;

import java.util.Scanner;

public class InputIntStr implements InputData {
    @Override
    public Object inputKey(Scanner in) {
        return Input.getInt(in, "Введите ключ");
    }

    @Override
    public Object inputValue(Scanner in) {
        return Input.getStr(in, "Введите значение");
    }
}
