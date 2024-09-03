package UserInterfejs;

public interface View <T> {
    String getName();
    String getBirthday();
    <u> void printAll (List <U> list, Class <U> clas);
    void showeMessage (String s);
}
