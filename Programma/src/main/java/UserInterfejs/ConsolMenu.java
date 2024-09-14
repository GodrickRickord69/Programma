package UserInterfejs;

import java.util.Scanner;
import Controller.*;
import Exception.NekorrektData;
import Modeli.PetomecTipe;

public class ConsolMenu {
    ControllerPetomcev  controllerPetomcev;

    public ConsolMenu( ControllerPetomcev controll){
        this.controllerPetomcev = controll;
    }

    public void start() {
        System.out.println();
        try (Scanner in = new Scanner(System.in, ); Schetchik schetchik = new Schetchik()){

            Boolean flag = true;
            int id;
            while (flag) {

                System.out.println(
                        "- Список всех животных\ - Завести новое животное\ - Изменить данные о животном\ - Что умеет животное\ - Дрессировка\ - Удалить запись\ - Выход");
                String key = in.next();
                switch (key) {
                    case "1":
                        controllerPetomcev.getAllPetomec();
                        break;
                    case "2":
                        PetomecTipe tipe = menuChoice(in);
                        if (tipe != null) {
                            try {
                                controllerPetomcev.createPetomec(tipe);
                                schetchik.add();
                                System.out.println("ОК");
                            } catch (NekorrektData e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        break;
                    case "3":
                        while (true) {
                            id = menuChoicePet(in);
                            if (id != 0)
                                try {
                                    controllerPetomcev.updatePetomec(id);
                                } catch (NekorrektData e) {
                                    System.out.println(e.getMessage());
                                }
                            else
                                break;
                        }
                        break;
                    case "4":
                        while (true) {
                            id = menuChoicePet(in);
                            if (id != 0)
                                controllerPetomcev.getComandy(id);
                            else
                                break;
                        }
                        break;
                    case "5":
                        id = menuChoicePet(in);
                        if (id != 0)
                            menuTrainPet(id, in);
                        break;
                    case "6":
                        id = menuChoicePet(in);
                        if (id != 0)
                            controllerPetomcev.delete(id);
                        break;
                    case "0":
                        flag = false;
                        break;
                    default:
                        System.out.println("такого варианта нет");
                        break;
                }
            }
        }
    }

    private PetomecTipe menuChoice(Scanner in){
        System.out.println("Какое животное добавить:\ - Кошка\ - Собака\ - Хомяк\ - Возврат в меню");

        while (true) {
            String key = in.next();
            switch (key) {
                case "1":
                    return PetomecTipe.Cat;
                case "2":
                    return PetomecTipe.Dog;
                case "3":
                    return PetomecTipe.Hamster;
                case "0":
                    return null;
                default:
                    System.out.println("Такого варианта нет, введите число от 0 до 3");
                    break;
            }
        }
    }

    private int menuChoicePet(Scanner in) {
        System.out.println("\nВведите номер животного, 0 для возврата в основное меню: ");
        while (true) {
            int id = in.nextInt();
            in.nextLine();
            if (id == 0)
                return id;
            if (controllerPetomcev.getById(id) == null) {
                System.out.println("Животного с таким номером нет, попробуйте еще раз, 0 для возврата в основное меню:");
            } else
                return id;

        }
    }

    private void menuTrainPet(int petId, Scanner in) {
        Scanner sc = in;
        while (true) {
            System.out.println("Введите новую команду, 0 для возврата в основное меню: ");
            String command = sc.nextLine();
            if (command.length() == 1 && command.equals("0"))
                return;
            if (controllerPetomcev.trainPet(petId, command))
                System.out.println("получилось!");
        }
    }
}
