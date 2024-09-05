package Modeli;

public enum PetomecTipe {

    Cat,
    Dog,
    Hamster;

    public static PetomecTipe getTipe (int tipeId){
        switch (tipeId){
            case 1:
                return PetomecTipe.Cat;
            case 2:
                return PetomecTipe.Dog;
            case 3:
                return PetomecTipe.Hamster;
            default:
                return null;
        }
    }
}
