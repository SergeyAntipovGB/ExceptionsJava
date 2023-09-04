package lesson3;

import java.util.Random;

public class Task3 {

    public static void main(String[] args) {

        Random random = new Random();
        try
        {
            Animal cat1 = new Cat("Персик");
            Animal cat2 = new Cat("Барсик");

            Animal[] animals = new Animal[]{cat1, cat2};

            for (int i = 0; i < animals.length; i++) {
                for (int j = 0; j < 10; j++) {
                    int k = random.nextInt(2);
                    try {
                        switch (k) {
                            case 0: {
                                animals[i].swim(j * 10);
                                System.out.printf("Животное проплыло %d метров.\n", j * 10);
                            }
                            case 1: {
                                animals[i].run(j * 10);
                                System.out.printf("Животное пробежало %d метров.\n", j * 10);
                            }
                        }
                    } catch (AnimalSwimException e) {
                        System.out.printf("Ошибка при попытке %s проплыть %d метров. (%s)\n", e.getName(), e.getDistance(), e.getMessage());
                    } catch (AnimalRunException e) {
                        System.out.printf("Ошибка при попытке %s пробежать %d метров. (%s)\n", e.getName(), e.getDistance(), e.getMessage());
                    }
                }
            }
        }
        catch (AnimalNameException e){
            System.out.println(e.getMessage());
        }
    }

}

abstract class Animal{

    protected String name;

    public String getName() {
        return name;
    }

    public Animal(String name) throws AnimalNameException {

        if (name == null || name.length() < 4)
            throw new AnimalNameException("Имя животного указано некорректно.", name);

        this.name = name;
    }

    abstract void swim(int distance) throws AnimalSwimException;

    abstract void run(int distance) throws AnimalRunException;

}

class Cat extends Animal{


    public Cat(String name) throws AnimalNameException {
        super(name);
    }

    @Override
    void swim(int distance) throws AnimalSwimException {
        throw new AnimalSwimException("Кот не умеет плавать.", name, distance);
    }

    @Override
    void run(int distance) throws AnimalRunException {
        if (distance > 50)
            throw new AnimalRunException("Кот не умеет далеко бегать.", name, distance);

        //TODO: Выполняем дальнейшие инструкции нашего метода ...
    }

}

class AnimalNameException extends Exception{

    private String name;

    public String getName() {
        return name;
    }

    public AnimalNameException(String message, String name) {
        super(message);
        this.name = name;
    }
}

abstract class AnimalActionException extends AnimalNameException{

    private int distance;

    public int getDistance() {
        return distance;
    }

    public AnimalActionException(String message, String name, int distance) {
        super(message, name);
        this.distance = distance;
    }

}

class AnimalRunException extends AnimalActionException{

    public AnimalRunException(String message, String name, int distance) {
        super(message, name, distance);
    }
}

class AnimalSwimException extends AnimalActionException{

    public AnimalSwimException(String message, String name, int distance) {
        super(message, name, distance);
    }
}
