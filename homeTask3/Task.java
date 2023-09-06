package homeTask3;

import java.util.ArrayList;
import java.util.Scanner;

public class Task {
    
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        ArrayList<String> userDataArray = new ArrayList<>();
        try {
            String userDataString = inputUserDataString();
            for (String item : userDataString.split(" ")) {
                userDataArray.add(item);
            }
        } catch (Exception e) {
            // ловим возможную ошибку парсинга
            e.printStackTrace();
        }

        int exceptionCode = userDataArrayLength(userDataArray);
        /**
         * Проверка соответствия количества введенных данных
         */
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

        try {
            DataMaster dataMaster = new DataMaster();
            char sex = dataMaster.findSex(userDataArray);
            String phone = dataMaster.findPhone(userDataArray);

        } catch (NoDataSexException e) {
            e.getMessage();
        } catch (ExtraDataException e) {
            e.getMessage();
        } catch (BadPhoneDataException e) {
            e.getMessage();
        } catch (Exception e) {
            // TODO: ловим ошибки данных
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
            "Пол - латинские буквы f или m\n> "
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
