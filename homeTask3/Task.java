package homeTask3;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Task {
    
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        
        /** Ввод данных пользователем с клавиатуры */
        ArrayList<String> userDataArray = new ArrayList<>();
        try {
            String userDataString = "фамилия m 17.12.1905 имя 80001234567 отчество";
            // String userDataString = inputUserDataString();
            for (String item : userDataString.split(" ")) {
                userDataArray.add(item);
            }
        } catch (Exception e) {
            // ловим возможную ошибку парсинга
            e.printStackTrace();
        }

        /** Опреледение кода ошибки */
        int exceptionCode = userDataArrayLength(userDataArray);

        /** Проверка соответствия количества введенных данных */
        switch (exceptionCode) {
            case -1:
                try {
                    throw new NotEnoughDataException("Введено меньше данных, чем необходимо!", exceptionCode);
                } catch (NotEnoughDataException e) {
                    System.out.printf("%s Код ошибки: %d\n",
                    e.getMessage(), e.getExceptionCode());
                }
                break;
            case 1:
                try {
                    throw new ExtraDataException("Введено больше данных, чем необходимо!", exceptionCode);
                } catch (ExtraDataException e) {
                    System.out.printf("%s Код ошибки: %d\n",
                    e.getMessage(), e.getExceptionCode());
                }
                break;
            default:
                break;
        }

        DataMaster dataMaster = new DataMaster();

        /** Проверка корректности данных */
        try {
            dataMaster.findSex(userDataArray);
            dataMaster.findPhone(userDataArray);
            dataMaster.findBirthday(userDataArray);
            dataMaster.findFIO(userDataArray);
            T.print(dataMaster.toString());
        } catch (NoDataSexException e) {
            e.printStackTrace();
        } catch (ExtraDataException e) {
            e.printStackTrace();
        } catch (BadDataException e) {
            e.printStackTrace();
        }

        try(FileWriter writer = new FileWriter(dataMaster.getSurname() + ".txt", true)) {
            writer.write(dataMaster.toString());
            writer.append('\n');
            writer.flush();
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /** Метод запрашивает у пользователя данные
     * <Фамилия Имя Отчество Номер_телефона Дата_рождения Пол>
     * в свободном порядке, разделенные пробелом
     * @return строку с данными
     */
    public static String inputUserDataString() {
        System.out.printf(
            "Введите через пробел следующие данные:\n" +
            "Фамилию Имя Отчество Дату_рождения Номер_телефона Пол\n" +
            "Дату рождения в формате dd.mm.yyyy\n" +
            "Номер телефона в формате целого числа без знаков\n" +
            "Пол - латинские буквы f или m\n" +
            "!!! ВАЖНО: фамилия должна быть введена раньше чем имя, " +
            "а имя раньше, чем отчество!\n> "
        );
        return scanner.nextLine();
    }

    /** Метод проверяет количество введенных пользователем данных.
     * @param array - Массив из введенных данных
     * @return -1 если данных меньше чем необходимо,
     * 1 если данных больше чем необходимо,
     * 0 если данных достаточно
     */
    public static int userDataArrayLength(ArrayList<String> array) {
        if (array.size() < 6) return -1;
        else if (array.size() > 6) return 1;
        else return 0;
    }
}
