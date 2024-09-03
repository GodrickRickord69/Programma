package Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ControllerPetomcev {

    public ControllerPetomcev(){

    }

    public void createPetomec(PetomecTipe tipe){
        String[] data = new String[] {view.getName(), view.getBirthday()};
        validator.validate(data);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.mm.yyyy");
        LocalDate birthday = LocalDate.parse(data[1], formatter);
        try {
            int res = PetomecRepositorij.create(petomecCreator.createPetomec(tipe, data[0], birthday));
            view.showMessage(String.format("Запись добавлена", res));
        } catch (RuntimeException e){
            view.showMessage(e. getMessage());
        }
    }

    public void updatePetomec(int id){
        Petomec petomec = getById(id);
        String[] data = new String[] {view.getName(), view.getBirthday()};

        validator.validate(data);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.mm.yyyy");
        LocalDate birthday = LocalDate.parse(data[1], formatter);
        petomec.setName(data[0]);
        petomec.setBirthday(birthday);
        try{
            int res = PetomecRepositorij.update(petomec);
            view.showMessage(String.format("Запись изменена \n", res));
        }catch (RuntimeException e){
            view.showMessage(e. getMessage());
        }
    }

    public void getAllPetomec(){
        try{
            view.printAll(petomecRepositorij.getAll(),Petomec.class);
        } catch (RuntimeException e){
            view.showMessage(e.getMessage());
        }
    }

    @Override
    public boolean trainPetomec(int id, String comanda) {
        try{
            if(((PetomecRepositorij)petomecRepositorij).getComandasById(id,1).contains(comanda))
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
            view.printAll(((PetomecRepositorij)petomecRepositorij).getComandasById(id, 1), String.class);
        }catch (RuntimeException e) {
            view.showMessage(e.getMessage());
        }
    }
}
