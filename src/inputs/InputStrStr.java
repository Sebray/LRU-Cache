package inputs;

import java.util.Scanner;

public class InputStrStr implements InputData {
    @Override
    public Object inputKey(Scanner in) {
        return Input.getStr(in, "Введите ключ");
    }

    @Override
    public Object inputValue(Scanner in) {
        return Input.getStr(in, "Введите значение");
    }
}
