package inputs;

import java.util.Scanner;

public interface InputData {//интерфейс, с помощью которого будут вводиться данные в кэш

    Object inputKey(Scanner in);

    Object inputValue(Scanner in);
}
