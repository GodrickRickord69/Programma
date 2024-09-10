package org.example;

public class Programma {
    public static void main(String[] args) throws Iskluchenie{
        Repositorij <Petomec> Petomnik = new  PetomecRepositorij();
        PetControll controll = new PetControll(Petomnik);
        new ConsolMenu (controll).start();
    }
}