package factory.storage;

import factory.components.Car;

public class CarStorage extends Storage<Car> {

    public CarStorage(int storageSize) {
        super(storageSize, Car.class);
    }

}