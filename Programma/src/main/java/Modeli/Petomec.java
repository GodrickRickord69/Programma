package Modeli;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Petomec {

    protected int petomecId;
    protected String name;
    protected LocalDate birthday;

    public void setPetomecId(int petomecId){this.petomecId = petomecId;}

    public int getPetomecId() {return petomecId;}

    public void setName(String name) {this.name = name;}

    public String getName() {return name;}

    public void setBirthday(LocalDate date) {this.birthday = date;}

    public LocalDate getBirthdayDate(){return  birthday;}

    public String getBirthday(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.mm.yyyy");
        return formatter.format(birthday);
    }

    @Override
    public String toString() {
        return String.format("имя: , дата рождения: ", getPetomecId(), getClass().getSimpleName(), name, getBirthday());
    }
}
