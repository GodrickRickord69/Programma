package Controller;

public class Schetchik implements {
    static int summa;
    {
        summa = 0;
    }

    public void add() {summa++;}

    @Override
    public void close() {
        System.out.println("Schetchik closer");
    }
}
