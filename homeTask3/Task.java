package homeTask3;

import java.util.Scanner;

public class Task {
    
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        try {
            String userDataString = inputUserDataString();
            String[] userDataArray = {};
            int count = 0;
            for (String item : userDataString.split(" "))
                userDataArray[count++] = item;
        } catch (Exception e) {
            // TODO: handle exception
        }
        int exceptionCode = userDataArrayLength(userDataArray);
        switch (exceptionCode) {
            case -1:
                throw new NotEnoughDataException("Введено меньше данных, чем необходимо!", exceptionCode);
                break;
            case 1:
                throw new ExtraDataException("Введено больше данных, чем необходимо!", exceptionCode);
                break;
            default:
                break;
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
    public static int userDataArrayLength(String[] array) {
        if (array.length < 6) return -1;
        if (array.length > 6) return 1;
        return 0;
    }
}
