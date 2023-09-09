package homeTask3;

import java.util.ArrayList;

/** Класс хранения и обработки данных пользователя
 * формирует строку корректных данных для записи в файл
 */
public class DataMaster {
    
    private String surname;
    private String name;
    private String patronymic;
    private String phone;
    private String birthday;
    private char sex;
    
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public void setSex(char sex) {
        this.sex = sex;
    }
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
    public void findSex(ArrayList<String> array) throws NoDataSexException, ExtraDataException {
        if (array.indexOf("f") == -1) {
            if (array.indexOf("m") == -1) {
                throw new NoDataSexException("Отсутствует запись с указанием пола или пол указан неверно!");
            }else {
                if (array.indexOf("m") == array.lastIndexOf("m")) setSex('m');
            }
        }else {
            if (array.indexOf("f") == array.lastIndexOf("f"))
            setSex('f');
            else throw new ExtraDataException("Дублируются данные про пол!");
        }
        array.remove(String.valueOf(getSex()));
    }

    /** Метод проверяет наличие валидного номера телефона
     * @param ArrayList<String> array
     * @return String 
     * @throws BadDataException
     * @throws ExtraDataException
     */
    public void findPhone(ArrayList<String> array) throws BadDataException, ExtraDataException {
        ArrayList<String> correctData = new ArrayList<>();
        for (String item : array) {
            if (item.length() == 11) {
                if (isDigit(item.substring(0, 5)) &
                    isDigit(item.substring(5))
                ) correctData.add(item);
            }
        }
        if (correctData.isEmpty()) throw new BadDataException("Отсутствует номер телефона или введены неверные телефонные данные! Номер телефона должен содержать 11 цифр без знаков!");
        else if (correctData.size() != 1) throw new ExtraDataException("Введено более одного номера телефона!");
        else {
            setPhone(correctData.get(0));
            array.remove(getPhone());
        }
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
     * @throws BadDataException
     * @throws ExtraDataException
     */
    public void findBirthday(ArrayList<String> array) throws BadDataException, ExtraDataException {
        ArrayList<Integer> ddmmyyyy = new ArrayList<>();
        ArrayList<String> correctData = new ArrayList<>();
        String tempString = "";
        String tempDay = "0";
        String tempMonth = "0";
        for (String item : array) {
            if (item.length() == 10) {
                try {//разбираем дату для проверки частей
                    ddmmyyyy.add(Integer.parseInt(item.substring(0, 2)));
                    ddmmyyyy.add(Integer.parseInt(item.substring(3, 5)));
                    ddmmyyyy.add(Integer.parseInt(item.substring(6)));
                    if (ddmmyyyy.get(0) > 0 &
                        ddmmyyyy.get(0) < 32 &
                        ddmmyyyy.get(1) > 0 &
                        ddmmyyyy.get(1) < 13 &
                        ddmmyyyy.get(2) > 1900 &
                        ddmmyyyy.get(2) < 2024
                    ) { //сборка даты с разделителем "."
                        if (ddmmyyyy.get(0) < 10) {
                            tempDay = tempDay + ddmmyyyy.get(0).toString();
                        }else tempDay = ddmmyyyy.get(0).toString();
                        if (ddmmyyyy.get(1) < 10) {
                            tempMonth = tempMonth + ddmmyyyy.get(1).toString();
                        }else tempMonth = ddmmyyyy.get(1).toString();
                        correctData.add(
                            tempDay + "." +
                            tempMonth + "." +
                            ddmmyyyy.get(2).toString()
                        );
                        tempString = item;
                    } else throw new NumberFormatException("");
                } catch (NumberFormatException e) {
                    // проверяем невалидные для даты данные
                    throw new BadDataException("Неправильная дата! \n 01<дд<31, 01<мм<12, 1900<гггг<2024");
                }
            }
        }
        if (correctData.isEmpty()) throw new BadDataException("Отсутствует дата рожденья или неправильный формат даты! Дата должна быть в формате дд.мм.гггг");
        else if (correctData.size() != 1) throw new ExtraDataException("Вы ввели несколько дат рождения!");
        else {
            setBirthday(correctData.get(0));
            array.remove(tempString);
        }
    }

    /** Метод проверяет валидность данных ФИО.
     * В качестве фамилии принимается первый текстовый элемент из списка,
     * в качестве имени - второй, в качестве отчества - третий!
     * @param ArrayList<String> array
     */
    public void findFIO(ArrayList<String> array) {
        String[] fio = new String[array.size()];
        for (int i = 0; i < array.size(); i++) {
            if (!isAlpha(array.get(i)))
                throw new BadDataException("Неверные данные ФИО! Должны содержать только буквы.");
            //форматируем текст с заглавной первой буквы
            fio[i] =
            array.get(i).toUpperCase().substring(0, 1) +
            array.get(i).toLowerCase().substring(1);
        }
        setSurname(fio[0]);
        setName(fio[1]);
        setPatronymic(fio[2]);
    }

    /** Метод проверяет строку на отсутствие посторонних знаков кроме букв латинского и русского алфавитов
     * @param String s
     * @return Boolean
     */
    public static boolean isAlpha(String s) {
        if (s == null) return false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!(c >= 'A' && c <= 'Z') &&
                !(c >= 'a' && c <= 'z') &&
                !(c >= 'А' && c <= 'Я') &&
                !(c >= 'а' && c <= 'я')) {
                return false;
            }
        }
        return true;
    }
}
