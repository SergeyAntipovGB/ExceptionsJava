package homeTask3;

import java.util.ArrayList;

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

    /** Метод проверяет наличие корректных данных про пол
     * @param ArrayList<String> array
     * @return char
     * @throws NoDataSexException
     * @throws ExtraDataException
     */
    public char findSex(ArrayList<String> array) throws NoDataSexException, ExtraDataException {
        if (array.indexOf("f") == -1) {
            if (array.indexOf("m") == -1) {
                throw new NoDataSexException("Отсутствует запись с указанием пола или пол указан неверно!");
            }else {
                if (array.indexOf("m") == array.lastIndexOf("m")) return (char) 'm';
            }
        }else {
            if (array.indexOf("f") == array.lastIndexOf("f"))
            return (char) 'f';
        }
        throw new ExtraDataException("Дублируются данные про пол!");
    }

    /** Метод проверяет наличие валидного номера телефона
     * @param ArrayList<String> array
     * @return String 
     * @throws BadNumberException
     * @throws ExtraDataException
     */
    public String findPhone(ArrayList<String> array) throws BadNumberException, ExtraDataException {
        ArrayList<String> correctData = new ArrayList<>();
        for (String item : array) {
            if (item.length() == 11) {
                if (isDigit(item.substring(0, 5)) &
                    isDigit(item.substring(5))
                ) correctData.add(item);
            }
        }
        if (correctData.isEmpty()) throw new BadNumberException("Отсутствует номер телефона или введены неверные телефонные данные! Номер телефона должен содержать 11 цифр без знаков!");
        else if (correctData.size() != 1) throw new ExtraDataException("Введено более одного номера телефона!");
        else return correctData.get(0);
    }

    /** Метод проверяет является ли строка числом
     * @param s (String)
     * @return boolean (true если является числом, false если в строке есть не числовые символы)
     */
    private static boolean isDigit(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /** Метод проверяет наличие правильной даты
     * @param ArrayList<String> array
     * @return String
     * @throws BadNumberException
     * @throws ExtraDataException
     */
    public String findBirthday(ArrayList<String> array) throws BadNumberException, ExtraDataException {
        ArrayList<Integer> ddmmyyyy = new ArrayList<>();
        ArrayList<String> correctData = new ArrayList<>();
        for (String item : array) {
            if (item.length() == 10) {
                try {
                    ddmmyyyy.add(Integer.parseInt(item.substring(0, 2)));
                    ddmmyyyy.add(Integer.parseInt(item.substring(3, 5)));
                    ddmmyyyy.add(Integer.parseInt(item.substring(6)));
                    if (ddmmyyyy.get(0) > 0 &
                        ddmmyyyy.get(0) < 32 &
                        ddmmyyyy.get(1) > 0 &
                        ddmmyyyy.get(1) < 13 &
                        ddmmyyyy.get(2) > 1900 &
                        ddmmyyyy.get(2) < 2024
                    )correctData.add(
                        ddmmyyyy.get(0).toString() + "." +
                        ddmmyyyy.get(1).toString() + "." +
                        ddmmyyyy.get(2).toString()
                    );
                    else throw new NumberFormatException("");
                } catch (NumberFormatException e) {
                    // проверяем невалидные для даты данные
                    throw new BadNumberException("Неправильная дата! \n 01<дд<31, 01<мм<12, 1900<гггг<2024");
                }
            }
        }
        if (correctData.isEmpty()) throw new BadNumberException("Отсутствует дата рожденья или неправильный формат даты! Дата должна быть в формате дд.мм.гггг");
        else if (correctData.size() != 1) throw new ExtraDataException("Вы ввели несколько дат рождения!");
        else return correctData.get(0);
    }
}
