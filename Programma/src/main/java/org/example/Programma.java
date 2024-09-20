package org.example;

import Controller.ControllerPetomcev;
import Modeli.Petomec;
import Servis.PetomecRepositorij;
import Servis.Repositorij;
import UserInterfejs.ConsolMenu;

public class Programma {
    public static void main(String[] args) throws Exception{
        Repositorij <Petomec> Petomnik = new  PetomecRepositorij();
        ControllerPetomcev controll = new ControllerPetomcev(Petomnik);
        new ConsolMenu (controll).start();
    }
}