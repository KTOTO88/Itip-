import java.util.HashMap;

// Класс для представления автомобиля
class Car {
    private String brand;
    private String model;
    private int year;

    public Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }
}

// Класс, представляющий хэш-таблицу автомобилей
class CarHashTable {
    private HashMap<String, Car> carMap;

    public CarHashTable() {
        carMap = new HashMap<String, Car>();
    }

    // Вставка автомобиля в хэш-таблицу
    public void insertCar(String licensePlate, Car car) {
        carMap.put(licensePlate, car);
    }

    // Поиск автомобиля по номерному знаку
    public Car findCar(String licensePlate) {
        return carMap.get(licensePlate);
    }

    // Удаление автомобиля по номерному знаку
    public void deleteCar(String licensePlate) {
        carMap.remove(licensePlate);
    }
}

public class Main {
    public static void main(String[] args) {
        // Создание объекта хэш-таблицы
        CarHashTable carHashTable = new CarHashTable();

        // Создание объектов автомобилей
        Car car1 = new Car("BMW", "M8", 2020);
        Car car2 = new Car("Mercedes-Benz", "GT63", 2019);
        Car car3 = new Car("Audi", "R8", 2018);

        // Вставка автомобилей в хэш-таблицу
        carHashTable.insertCar("A123BC", car1);
        carHashTable.insertCar("B456CD", car2);
        carHashTable.insertCar("C789DE", car3);

        // Поиск автомобиля по номерному знаку
        String licensePlate = "B456CD";
        Car foundCar = carHashTable.findCar(licensePlate);
        if (foundCar != null) {
            System.out.println("Найден автомобиль с номерным знаком " + licensePlate +
                    ": марка - " + foundCar.getBrand() +
                    ", модель - " + foundCar.getModel() +
                    ", год выпуска - " + foundCar.getYear());
        } else {
            System.out.println("Автомобиль с номерным знаком " + licensePlate + " не найден");
        }

        // Удаление автомобиля по номерному знаку
        String licensePlateToDelete = "C789DE";
        carHashTable.deleteCar(licensePlateToDelete);
        System.out.println("Удален автомобиль с номерным знаком " + licensePlateToDelete);

        // Проверка удаления автомобиля
        foundCar = carHashTable.findCar(licensePlateToDelete);
        if (foundCar != null) {
            System.out.println("Автомобиль с номерным знаком " + licensePlateToDelete + " найден");
        } else {
            System.out.println("Автомобиль с номерным знаком " + licensePlateToDelete + " не найден");
        }
    }
}