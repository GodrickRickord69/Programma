package Modeli;

public enum PetomecTipe {

    Cat,
    Dog,
    Hamster,
    Camel,
    Danke,
    Horse;

    public static PetomecTipe getTipe (int tipeId){
        switch (tipeId){
            case 1:
                return PetomecTipe.Cat;
            case 2:
                return PetomecTipe.Dog;
            case 3:
                return PetomecTipe.Hamster;
            case 4:
                return PetomecTipe.Camel;
            case 5:
                return PetomecTipe.Danke;
            case 6:
                return PetomecTipe.Horse;
            default:
                return null;
        }
    }
}
