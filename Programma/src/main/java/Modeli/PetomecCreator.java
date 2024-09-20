package Modeli;

public class PetomecCreator extends Creator{

    @Override
    protected Petomec createNewPetomec (PetomecTipe tipe){

        switch (tipe) {
            case Cat:
                return new Cat();
            case Dog:
                return new Dog();
            case Hamster:
                return new Hamster();
        }
        return null;
    }
}
