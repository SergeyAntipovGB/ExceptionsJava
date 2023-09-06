package homeTask3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.lang.model.util.ElementScanner6;

public class DataMaster {
    
    private String surname;
    private String name;
    private String patronymic;
    private String phone;
    private String birthday;
    private char sex;
    
    public String getSurname() {
        return surname;
    }
    public String getName() {
        return name;
    }
    public String getPatronymic() {
        return patronymic;
    }
    public String getPhone() {
        return phone;
    }
    public String getBirthday() {
        return birthday;
    }
    public char getSex() {
        return sex;
    }

    public String toString() {
        return String.format(
            "<%s><%s><%s><%s><%s><%s>",
            getSurname(),
            getName(),
            getPatronymic(),
            getBirthday(),
            getPhone(),
            getSex()
        );
    }

    public DataMaster() {
        this.surname = null;
        this.name = null;
        this.patronymic = null;
        this.phone = null;
        this.birthday = null;
        this.sex = 0;
    }

    public char findSex(ArrayList<String> array) {
        if (array.indexOf("f") == -1) {
            if (array.indexOf("m") == -1) {
                throw new NoDataSexException("Отсутствует запись с указанием пола или пол указан неверно!");
            }else {
                if (array.indexOf("m") == array.lastIndexOf("m")) return 'm';
            }
        }else {
            if (array.indexOf("f") == array.lastIndexOf("f"))
            return 'f';
        }
        throw new ExtraDataException("Дублируются данные про пол!");
    }

    public String findPhone(ArrayList<String> array) {
        ArrayList<String> correctData = new ArrayList<>();
        for (String item : array) {
            if (item.length() == 11) {
                if (isDigit(item)) correctData.add(item);
            }
        }
        if (correctData.size() != 1) {
            throw new BadPhoneDataException("Неверные телефонные данные! Номер телефона должен содержать 11 цифр без знаков!");
        }else return correctData.get(0);
    }

    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // public Map<String, String> parseData(ArrayList<String> array) {
    //     Map<String, String> datMap = new HashMap<>();

    // }
}
