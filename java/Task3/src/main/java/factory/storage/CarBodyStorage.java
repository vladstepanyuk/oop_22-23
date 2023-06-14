package factory.storage;

import factory.products.CarBody;

public class CarBodyStorage extends Storage<CarBody> {
    public CarBodyStorage(int storageSize) {
        super(storageSize, CarBody.class);
    }
}
