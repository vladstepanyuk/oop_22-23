package factory.storage;

import factory.Controller;
import factory.products.Car;
import factory.workers.WorkShop;

public class CarStorage extends Storage<Car> {

    public CarStorage(int storageSize) {
        super(storageSize, Car.class);
    }

}