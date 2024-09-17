package UserInterfejs;

import java.util.List;

public interface View <T> {
    String getName();
    String getBirthday();
    <u> void printAll (List <U> list, Class <U> clas);
    void showMessage(String s);
}
