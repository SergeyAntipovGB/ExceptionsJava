package lesson3;

import java.io.IOException;

public class Task2 {

    /**
     Создайте класс Счетчик, у которого есть метод add(), увеличивающий
     значение внутренней int переменной на 1. Сделайте так, чтобы с объектом
     такого типа можно было работать в блоке try-with-resources. Подумайте, что
     должно происходить при закрытии этого ресурса? Напишите метод для
     проверки, закрыт ли ресурс. При попытке вызвать add() у закрытого
     ресурса, должен выброситься IOException.
     */

    public static void main(String[] args) {

        try ( Counter counter1 = new Counter(100)){

            counter1.add();
            counter1.close();
            counter1.add();
            System.out.printf("Текущее значение счетчика: %d\n", counter1.getCounter());
        }
        catch (CloseCounterException e){
            System.out.printf("%s\n%s\n%s\n", e.getMessage(), e.getClass(), e.getClass().getSimpleName());
        }

    }

}

class Counter implements AutoCloseable{

    private int counter;
    private boolean isOpen;

    public int getCounter() {
        return counter;
    }

    public int add() throws CloseCounterException {
        if (!isOpen)
            throw new CloseCounterException("Счетчик закрыт.");
        return ++counter;
    }

    public Counter() {
        isOpen = true;
    }

    public Counter(int counter) {
        this.counter = counter;
        isOpen = true;
    }

    public void closeCounter(){
        isOpen = false;
    }

    @Override
    public void close() {
        closeCounter();
    }
}

class CloseCounterException extends IOException{
    public CloseCounterException(String message) {
        super(message);
    }
}
