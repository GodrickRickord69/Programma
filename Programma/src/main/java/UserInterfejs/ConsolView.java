package UserInterfejs;

import java.util.Scanner;
import java.util.List;
import Modeli.*;

public class ConsolView implements View <Petomec>{

    Scanner in;

    @Override
    public ConsolView() {
        in = new Scanner(System.in, );
    }

    @Override
    public String getName(){
        System.out.printf("Имя: ");
        return in.nextLine();
    }

    @Override
    public String getBirthday() {
        System.out.printf("Дата рождения в форме 'dd.mm.yyyy': ");
        return in.nextLine();
    }

    @Override
    public <T> void printAll (List <T> list, Class <T> clas) {
        System.out.print("");
        if (list.isEmpty())
            System.out.println("В списке ничего нету");
        else {
            if (clas == Petomec.class)
                System.out.println(" Наши питомцы:");
            for (T item : list) {
                System.out.println(item);
            }
        }
    }

    @Override
    public void showMessage(String message){
        System.out.println(message);
    }
}
