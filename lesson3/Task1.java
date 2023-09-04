package lesson3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Task1 {

    /**
     *     1.  Создайте класс исключения, который будет выбрасываться при делении на
     *         0. Исключение должно отображать понятное для пользователя сообщение
     *         об ошибке.
     *     2.  Создайте класс исключений, которое будет возникать при обращении к
     *         пустому элементу в массиве ссылочного типа. Исключение должно
     *         отображать понятное для пользователя сообщение об ошибке
     *     3.  Создайте класс исключения, которое будет возникать при попытке открыть
     *         несуществующий файл. Исключение должно отображать понятное для
     *         пользователя сообщение об ошибке.
     *
     *     4.* Продемонстрировать работу каждого исключения.
     */

    public static void main(String[] args) {

        try {
            task1();
        }
        catch (DivisionByZeroException e){
            System.out.println(e.getMessage());
        }

        try
        {
            task2(new String[] {"AAA", "BBB", null, "DDD"}, 3);
        }
        catch (NullPointerException | ArrayIndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }
        catch (NullElementArrayException e){
            System.out.printf("%s\nИндекс элемента: %d\n", e.getMessage(), e.getIndex());
        }

        try {
            task3("C:/test/task1.java");
        }
        catch (MyFileNotFoundException e){
            System.out.printf("%s\nНаименование файла: %s\n", e.getMessage(), e.getName());
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }

    }

    static void task1(){
        try
        {
            int a = 10;
            a = a / 0;
        }
        catch (ArithmeticException e){
            throw new DivisionByZeroException("Ошибка деления числа на 0.");
        }
    }

    /**
     *
     * @param array
     * @param index
     * @throws NullPointerException
     * @throws NullElementArrayException
     * @throws ArrayIndexOutOfBoundsException
     */
    static void task2(String[] array, int index) throws NullPointerException, NullElementArrayException, ArrayIndexOutOfBoundsException {
        if (array == null){
            throw new NullPointerException("Массив должен существовать.");
        }
        if (array[index] == null){
            throw new NullElementArrayException("Элемент массива не проинициализирован", index);
        }
        System.out.printf("Значение элемента массива: %s\n", array[index]);
    }

    static void task3(String fileName) throws MyFileNotFoundException, IOException {

        try (FileReader fileReader = new FileReader(fileName))
        {
            // TODO: Логика чтения данных ...
            char[] buf = new char[256];
            int count;

            while ( (count = fileReader.read(buf)) > 0){
                if (count < buf.length)
                    buf = Arrays.copyOf(buf, count);
                for (char c: buf) {
                    System.out.print(c);
                }
            }
        }
        catch (FileNotFoundException e){
            throw new MyFileNotFoundException("Файл не найден.", fileName);
        }
    }

}

class DivisionByZeroException extends RuntimeException{

    public DivisionByZeroException(String message) {
        super(message);
    }
}

class NullElementArrayException extends RuntimeException{

    private final int index;

    public int getIndex() {
        return index;
    }

    public NullElementArrayException(String message, int index) {
        super(message);
        this.index = index;
    }
}

class MyFileNotFoundException extends Exception{

    private final String name;

    public String getName() {
        return name;
    }

    public MyFileNotFoundException(String message, String name) {
        super(message);
        this.name = name;
    }
}