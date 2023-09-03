package ru.geekbrains.lesson2;

import java.util.Scanner;

public class Program {

    /**
     Посмотрите на этот код (main). Все ли с ним хорошо? Если нет, то скорректируйте его и
     обоснуйте свое решение.
     */
    public static void main(String[] args) {

        // try
        // catch
        // finally

        // try{}catch{}
        // try{}catch{}finally{}
        // try{}finally{}

        Scanner scanner = new Scanner(System.in);
        int[] arr = new int[10];
        System.out.println("Укажите индекс элемента массива, в который хотите записать значение 1: ");
        try {
            int index = new Scanner(System.in).nextInt();
            arr[index] = 1;
        } catch (Exception e ){
            System.out.println("Указан индекс за пределами массива.");
        }
    }



}
