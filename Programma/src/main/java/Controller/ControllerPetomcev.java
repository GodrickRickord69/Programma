package Controller;

import Modeli.*;
import Servis.Repositorij;
import Servis.PetomecRepositorij;
import UserInterfejs.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import Exception.NekorrektData;

public class ControllerPetomcev {
    private Repositorij<Petomec> petomecRepositorij;
    private Creator petomecCreator;
    private final View<Petomec> view;
    private Validator validator;

    public ControllerPetomcev(Repositorij<Petomec> petomecRepositorij){
        this.petomecRepositorij = petomecRepositorij;
        this.petomecCreator = new PetomecCreator();
        this.view = new ConsolView();
        this.validator = new Validator();
    }

    public void createPetomec(PetomecTipe tipe){
        String[] data = new String[] {view.getName(), view.getBirthday()};
        validator.validate(data);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate birthday = LocalDate.parse(data[1], formatter);
        try {
            int res = petomecRepositorij.create(petomecCreator.createPetomec(tipe, data[0], birthday));
            view.showMessage(String.format("%d Запись добавлена", res));
        } catch (RuntimeException e){
            view.showMessage(e. getMessage());
        }
    }

    public void updatePetomec(int id){
        Petomec petomec = getById(id);
        String[] data = new String[] {view.getName(), view.getBirthday()};

        validator.validate(data);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate birthday = LocalDate.parse(data[1], formatter);
        petomec.setName(data[0]);
        petomec.setBirthday(birthday);
        try{
            int res = petomecRepositorij.update(petomec);
            view.showMessage(String.format("%d Запись изменена \n", res));
        }catch (RuntimeException e){
            view.showMessage(e. getMessage());
        }
    }

    public void getAllPetomec(){
        try{
            view.printAll(petomecRepositorij.getAll(), Petomec.class);
        } catch (RuntimeException e){
            view.showMessage(e.getMessage());
        }
    }


    public boolean trainPetomec(int id, String comanda) {
        try{
            if(((PetomecRepositorij)petomecRepositorij).getComandasById(id, 1).contains(comanda))
                view.showMessage("это мы умеем");
            else {
                if (!((PetomecRepositorij)petomecRepositorij).getComandasById(id,2).contains(comanda))
                    view.showMessage("эту команду нельзя выполнить");
                else {
                    ((PetomecRepositorij)petomecRepositorij).train(id,comanda);
                    view.showMessage("команда разучена\n");
                    return true;
                }
            }
        }catch (RuntimeException e){
            view.showMessage(e.getMessage());
        }
        return false;
    }

    public Petomec getById(int id){
        try{
            return petomecRepositorij.getById(id);
        }catch (RuntimeException e) {
            view.showMessage(e.getMessage());
        }
        return null;
    }

    public void delete(int id){
        try{
            petomecRepositorij.delete(id);
            view.showMessage("запись удалена");
        }catch (RuntimeException e) {
            view.showMessage(e.getMessage());
        }
    }

    public void getComandy(int id){
        try{
            view.printAll(((PetomecRepositorij)petomecRepositorij).getComandsById(id, 1), String.class);
        }catch (RuntimeException e) {
            view.showMessage(e.getMessage());
        }
    }
}
