package Controller;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Validator {
    public void validate (String[] data){

        StringBuilder stringBuilder = new StringBuilder();
        boolean flag = true;

        for (int i=0; i < data.length; i++){
            try {
                if(i == 0)
                    isValidName(data[i]);
                if(i == 1)
                    isValidDate (data[i]);
            }catch (UncorrectDataException e){
                stringBuilder.append("\n");
                stringBuilder.append(e.getMessage());
                flag = false;
            }
        }
        if(flag == false){
            throw new UncorrectDataException(stringBuilder.toString());
        }
    }

    private boolean isValidName (String name){
        for (int i=0; i < name.length; i++){
            if(! Character.UnicodeBlock.of(name.charAt(i)).equals(Character.UnicodeBlock.CYRILLIC)){
                throw new UncorrectDataException(String.format("некорректное имя, допустимы только буквы кирилицы"));
            }
        }
        return true;
    }

    private boolean isValidDate (String birthday){

        LocalDate date;
        Integer [] month_30 = {4, 6, 9, 11};
        int day;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.mm.yyyy");
            date = LocalDate.parse(birthday, formatter);
            day = date.getDayOfMonth();
        }catch (DateTimeException e){
            throw new UncorrectDataException("некорректный формат даты");
        }
        if ((Arrays.asList(month_30).contains(date.getMonthValue()) && day > 30) ||
                (date.isLeapYear() && date.getMonthValue() == 2 && day > 29) ||
                (!date.isLeapYear() && date.getMonthValue() == 2 && day > 28){

            throw new UncorrectDataException("некорректная дата рождения");
        }else
            return true;
    }
}
