package homeTasks2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Task1 {

    public static void main(String[] args) {

        boolean flag;
        float number = 0;
        do {
            try {
                flag = false;
                System.out.print("Введите вещественное число > ");
                number = new Scanner(System.in).nextFloat();
            } catch (InputMismatchException e) {
                System.out.println("Введены данные не соответствующие типу float!");
                flag = true;
            } catch (Exception e) {
                System.out.println(e.getClass().getName());
                flag = true;
            }
        }while (flag);
        System.out.println("Вы ввели число " + number);
    }
}
