package lesson1;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Program {

    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    public static void main(String[] args) {
        // System.out.printf("Длина массива: %d\n", task1(new int[] {1, 5, 6}));
        task2();
        //task3();
        //task4();
        // task5();
    }

    /**
     Реализуйте метод, принимающий в качестве аргумента целочисленный массив.
     Если длина массива меньше некоторого заданного минимума, метод возвращает
     -1, в качестве кода ошибки, иначе - длину массива.
     */

    /**
     *
     * @param array
     * @return длина массива (-1 - длина равна 0; -2 - array == null)
     */
    static int task1(int[] array){
        if (array == null){
            return -2;
        }
        if (array.length == 0){
            return -1;
        }
        return array.length;
    }

    /**
     Реализуйте метод, принимающий в качестве аргумента целочисленный массив и некоторое значение.
     Метод ищет в массиве заданное значение и возвращает его индекс. При этом, например:
     1. если длина массива меньше некоторого заданного минимума, метод возвращает -1, в качестве кода ошибки.
     2. если искомый элемент не найден, метод вернет -2 в качестве кода ошибки.
     3. если вместо массива пришел null, метод вернет -3
     4. придумайте свои варианты исключительных ситуаций и верните соответствующие коды ошибок.
     Напишите метод, в котором реализуйте взаимодействие с пользователем.
     То есть, этот метод запросит искомое число у пользователя, вызовет первый,
     обработает возвращенное значение и покажет читаемый результат пользователю.
     Например, если вернулся -2, пользователю выведется сообщение: “Искомый элемент не найден”.
     */
    static void task2(){
        while (true){
            System.out.print("Укажите значение для поиска: ");
            int searchValue = Integer.parseInt(scanner.nextLine());
            int[] array = new int[random.nextInt(5) + 1];
            if (random.nextInt(3) == 0){
                array = null;
            }
            if (array != null){
                for (int i = 0; i < array.length; i++){
                    array[i] = random.nextInt(10);
                    System.out.printf("%d\t", array[i]);
                }
                System.out.println();
            }
            int codeResult = processArray(array, searchValue);
            switch (codeResult){
                case -1: System.out.println("Длина массива менее 3 символов");
                case -2: System.out.println("Элемент не найден");
                case -3: System.out.println("Массив некорректно проинициализирован");
                default: {
                    System.out.println("Массив успешно обработан. Завершение работы приложения.");
                    System.out.printf("Элемент найден по индексу: %d\n", codeResult);
                    return;
                }
            }
        }
    }


    static int processArray(int[] array, int searchValue){
        if (array == null)
            return -3; // Массив некорректно проинициализирован
        if (array.length < 3){
            return -1; // Длина массива менее 3 символов
        }
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));
        int searchResult = Arrays.binarySearch(array, searchValue);
        if (searchResult < 0)
            return -2; // Элемент не найден
        return searchResult;
    }

    /**
     * Реализуйте метод, принимающий в качестве аргумента целочисленный двумерный массив.
     *     Необходимо посчитать и вернуть сумму элементов этого массива.
     *     При этом накладываем на метод 2 ограничения: метод может работать только с квадратными массивами
     *     (кол-во строк = кол-ву столбцов), и в каждой ячейке может лежать только значение 0 или 1.
     *     Если нарушается одно из условий, метод должен бросить RuntimeException с сообщением об ошибке.
     */

    static void task3(){
        for (int i = 0; i < 5; i++){
            processArray(generateArray());
        }
    }

    static void processArray(int[][] array){
        for (int i = 0; i < array.length; i++){
            if (array[i].length != array.length)
                throw new RuntimeException("Некорректная размерность массива.");
        }
        int sum = 0;
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < array.length; j++){
                if (array[i][j] != 0 && array[i][j] != 1)
                    throw new RuntimeException(
                            String.format("Некорректное значение элемента по индексу [%d][%d] = %d", i, j, array[i][j])
                    );
                else{
                    sum += array[i][j];
                }
            }
        }
        System.out.printf("Сумма значений элементов массива: %d\n", sum);
    }

    static int[][] generateArray(){
        int[][] array = new int[random.nextInt(6 - 4) + 4][5];
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < array[i].length; j++){
                array[i][j] = random.nextInt(2);
            }
        }
        if (random.nextInt(3) == 0){
            array[random.nextInt(array.length)][random.nextInt(array.length)] = 2;
        }
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < array[i].length; j++){
                System.out.printf("%d\t", array[i][j]);
            }
            System.out.println();
        }
        return array;
    }


    static void task4(){
        for (int i = 0; i < 5; i++){
            int errCode = processArrayV2(generateArray());
            switch (errCode){
                case -1: System.out.println("Некорректная размерность массива.");
                case -2: System.out.println("Некорректное значение элемента");
                default: {
                    System.out.printf("Сумма значений элементов массива: %d\n", errCode);
                }
            }
        }
    }

    static int processArrayV2(int[][] array){
        for (int i = 0; i < array.length; i++){
            if (array[i].length != array.length)
                return -1;
                //throw new RuntimeException("Некорректная размерность массива.");
        }
        int sum = 0;
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < array.length; j++){
                if (array[i][j] != 0 && array[i][j] != 1)
                    return -2;
                    //throw new RuntimeException(
                    //        String.format("Некорректное значение элемента по индексу [%d][%d]", i, j)
                    //);
                else{
                    sum += array[i][j];
                }
            }
        }
        //System.out.printf("Сумма значений элементов массива: %d\n", sum);
        return sum;
    }

    /**
     Реализуйте метод, принимающий в качестве аргументов два целочисленных массива,
     и возвращающий новый массив, каждый элемент которого равен сумме элементов двух
     входящих массивов в той же ячейке.
     Если длины массивов не равны, необходимо как-то оповестить пользователя.
     */

    static void task5(){

        // try
        // catch
        // finally

        try
        {
            int[] res = getSumArray(new int[] {1, -2, 3, 4}, null);
            System.out.println(Arrays.toString(res));
        }
        catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
        catch (CustomArraySizeException e){
            System.out.println(e.getMessage());
            System.out.printf("Длина первого массива: %d\nДлина второго массива: %d\n",
                    e.getLength1(), e.getLength2());
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
        }

    }

    /**
     * Посчитать сумму двух массивов
     * @param array1 Первый массив
     * @param array2 Второй массив
     * @return Сумма массивов
     * @throws NullPointerException Оба массива должны существовать
     * @throws CustomArraySizeException Кол-во элементов массива должно быть одинаковым
     */
    static int[] getSumArray(int[] array1, int[] array2) throws NullPointerException, CustomArraySizeException{
        if (array1 == null || array2 == null)
            throw new NullPointerException("Оба массива должны существовать.");
        if (array1.length != array2.length)
            throw new CustomArraySizeException("Кол-во элементов массива должно быть одинаковым.", array1.length, array2.length);
        int[] res = new int[array1.length];
        for (int i = 0; i < array1.length; i++)
            res[i] = array1[i] + array2[i];
        if (random.nextInt(2) == 0)
        {
            int i = 1;
            i = i / 0;
        }
        return res;
    }
















}
