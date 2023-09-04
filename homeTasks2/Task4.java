package homeTasks2;

import java.util.Scanner;

public class Task4 {

    public static void main(String[] args) {

        System.out.print("Введите непустую строку > ");
        Scanner scanner = new Scanner(System.in);   
        String string = scanner.nextLine();
        if (string.isEmpty()) {
            scanner.close();
            throw new RuntimeException("Пустую строку вводить нельзя!");
        }
        scanner.close();
        System.out.printf("введена строка: <%s>\n", string);
    }
}
