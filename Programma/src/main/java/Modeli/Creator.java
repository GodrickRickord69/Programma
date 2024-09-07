package Modeli;

import java.time.LocalDate;

public abstract class Creator {

    protected abstract  Petomec createNewPetomec(PetomecTipe tipe);

    public Petomec createPetomec(PetomecTipe tipe, String name, LocalDate date){

        Petomec petomec = createNewPetomec(tipe);
        petomec.setName(name);
        petomec.setBirthday(date);
        return petomec;
    }
}
